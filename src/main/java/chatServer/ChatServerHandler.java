package chatServer;

import io.netty.channel.Channel;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

@Sharable
public class ChatServerHandler extends ChannelInboundHandlerAdapter {
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 채널 입출력 준비 완료 사용자가 들어왔을때.
        System.out.println("==== Channel Active  <<READY>> ====");
        Channel incoming = ctx.channel();
        for (Channel channel : channelGroup) {
            channel.write("[SERVER] - " + incoming.remoteAddress() + "has Joined!:) \n");
        }
        channelGroup.add(incoming);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 사용자가 나갔을때 기존 사용자에게 알림.
        System.out.println("==== Channel InActive <<EXIT>> ====");
        Channel incoming = ctx.channel();
        for (Channel channel : channelGroup) {
            channel.write("[SERVER] - " + incoming.remoteAddress() + "has Lefted! :(\n");
        }
        channelGroup.remove(incoming);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String message = null;
        message = (String) msg;
        System.out.println("channelRead Of [SERVER]" + message);
        Channel imcoming = ctx.channel();

        for (Channel channel : channelGroup) {
            // 메세제지 전달
            if (channel != imcoming) {
                channel.writeAndFlush("[" + imcoming.remoteAddress() + "]" + message + "\n");
            }
        }
        if ("Bye".equals(message.toLowerCase())) {
            ctx.close();
        }
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
