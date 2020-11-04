package io.github.kimmking.gateway.outbound.netty4;

import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

public class NettyHttpClientOutboundHandler  extends ChannelInboundHandlerAdapter {
    
    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if(msg instanceof DefaultLastHttpContent) {
            DefaultHttpContent response = (DefaultLastHttpContent) msg;
            String data = new String(response.content().array());
            System.out.println(data);
        } else if(msg instanceof DefaultHttpResponse) {
            HttpResponse response = (DefaultHttpResponse) msg;
            String data = response.toString();
            System.out.println(data);
        }
        super.channelRead(ctx, msg);
    }
}