package EchoClient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    byte[] arr = {'1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',};
    String productName = "productName";
    String productId = "productId";
    String serialNumber = "serialNumber";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.copiedBuffer("채널 활성화 시 메시지 전송합니다", CharsetUtil.UTF_8)); // 채널 활성화 시 메시지 전송
        ctx.writeAndFlush(Unpooled.copiedBuffer(arr));
        ctx.writeAndFlush(Unpooled.copiedBuffer(productName + ' ', CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.copiedBuffer(productId + ' ', CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.copiedBuffer(serialNumber, CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Client receive : " + msg.toString(CharsetUtil.UTF_8));  // 수신한 메시지 로깅
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();    // 예외 시 오류를 로깅하고 채널 닫기
        ctx.close();
    }
}