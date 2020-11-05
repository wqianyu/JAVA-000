package io.github.kimmking.gateway.outbound.okhttp;

import com.google.common.collect.Lists;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.HttpEndpointRouterImpl;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.CharsetUtil;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandler {

    private String backendUrl;

    private ExecutorService proxyService;

    private HttpEndpointRouter httpEndpointRouter;

    private final static OkHttpClient client = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(Runtime.getRuntime().availableProcessors() * 2, 1L, TimeUnit.MINUTES))
            .connectTimeout(60, TimeUnit.SECONDS)      //设置连接超时
            .readTimeout(60, TimeUnit.SECONDS)         //设置读超时
            .writeTimeout(60, TimeUnit.SECONDS)        //设置写超时
            .retryOnConnectionFailure(true)            //是否自动重连
            .build();

    public OkhttpOutboundHandler(String backendUrl) {
        httpEndpointRouter = new HttpEndpointRouterImpl();
        String url = httpEndpointRouter.route(Lists.newArrayList(backendUrl.split(";")));
        this.backendUrl = url.endsWith("/")?url.substring(0,url.length()-1):url;
        System.out.println("url:"+this.backendUrl);
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
    }

    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx) {
        String url = this.backendUrl + fullHttpRequest.uri();
        proxyService.submit(()->{
            fetchGet(fullHttpRequest, ctx, url);
        });
    }

    private void fetchGet(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            handleResponse(fullRequest, ctx, response);
        } catch (Exception e) {
            exceptionCaught(ctx, e);
        }
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response okHttpResponse) throws Exception {
        FullHttpResponse response = null;
        try {
            byte[] body = okHttpResponse.body().bytes();
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(okHttpResponse.header("Content-Length")));
            response.headers().set("nio", fullRequest.headers().get("nio"));
            String data = response.content().toString(CharsetUtil.UTF_8);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
