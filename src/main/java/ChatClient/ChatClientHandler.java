package ChatClient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.ssl.SslContext;

public class ChatClientHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext arg0, Object arg1) {
        System.out.println((String) arg1);
    }


    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
