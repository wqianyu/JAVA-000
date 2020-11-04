package io.github.kimmking.gateway.outbound.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.buffer.UnpooledUnsafeDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class NettyHttpClientOutboundHandler  extends ChannelInboundHandlerAdapter {
    
    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        //ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));//不加会导致httpResponse，header跟body分开发送
        /*if(msg instanceof DefaultLastHttpContent) {
        } else if(msg instanceof DefaultHttpResponse) {
            HttpResponse response = (DefaultHttpResponse) msg;
            String data = response.toString();
            System.out.println(data);
        }*/
        FullHttpResponse response = (FullHttpResponse) msg;
        //一定要编码字符，不然会乱码
        System.out.println(response.content().toString(CharsetUtil.UTF_8));
        System.out.println(response.headers());
        super.channelRead(ctx, msg);
    }
}