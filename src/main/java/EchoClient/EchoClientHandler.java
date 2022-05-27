package EchoClient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Date;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    //byte[] initDataArr = new byte[32]; // 초기화 데이터 담을 Byte [32] 선언
    byte[] initDataArr = {'a', 'b', 'c', 'd', 'e'}; // 초기화 데이터 담을 Byte [32] 선언
    String productName = "productName"; // 제품명을 담을 변수
    String productId = "productId"; // 제품 고유 아이디를 담을 변수
    String serialNumber = "serialNumber"; // 제품 S/N 을 담을 변수
    String phoneNumber = "01045818355";
    int size = 10;
    Date d = new Date();
    String regdate = d.toString();
    //initDataArr = {'a', 'b', 'c', 'd', 'e'};

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("채널 활성화 시 메시지 전송합니다", CharsetUtil.UTF_8)); // 채널 활성화 시 메시지 전송

        ctx.writeAndFlush(Unpooled.copiedBuffer(initDataArr));
        ctx.writeAndFlush(Unpooled.copiedBuffer(" " + productName + ' ', CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.copiedBuffer(productId + ' ', CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.copiedBuffer(serialNumber + ' ', CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.copiedBuffer(phoneNumber + ' ', CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.copiedBuffer(regdate + ' ', CharsetUtil.UTF_8));

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