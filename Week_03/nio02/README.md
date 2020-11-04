# netty-gateway

- NettyServerApplication netty服务器端启动类

- NettyHttpClient为netty client启动类，自动调用netty server服务

- HttpRequestFilter为服务器接收消息拦截器，为消息添加http header nio

- OkhttpOutboundHandler实际的代理转发类，使用okhttp
   

```