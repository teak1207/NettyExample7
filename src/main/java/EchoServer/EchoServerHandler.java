package EchoServer;

import utils.DBConnectUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import vo.ProductVO;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    // ChannelHandler를 여러 채널 간에 안전하게 공유할 수 있음을 나타냄

    DBConnectUtils dbConnect = new DBConnectUtils();
    ProductVO productVO = new ProductVO();
    // VO에 값을 담아서 사용 --> DAO 에서 디비로 와리가리
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received : " + in.toString(CharsetUtil.UTF_8));

//        System.out.println((char) in.getByte(0));
//        System.out.println((char) in.getByte(1));
//        System.out.println((char) in.getByte(2));
//        System.out.println((char) in.getByte(3));
//        System.out.println((char) in.getByte(4));

        if(in.hasArray()){

            byte[] array = in.array();
            System.out.println(array.toString());
        }


        // in 값을 VO 에 집어넣기

        // VO의 값 확인하기

        // VO를 DAO로 전달

//        String str = in.toString(0, 10, CharsetUtil.UTF_8);
//        System.out.println(str);
        //ctx.write(in); // 받은 메시지를 발신자에게로 Echo 시킨다.
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====종료합니다====");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // 대기중인 메시지를 원격피어로 플러시하고 채널을 닫음
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace(); // 예외 스택 추적을 출력
        ctx.close();    // 채널 닫기
    }
}