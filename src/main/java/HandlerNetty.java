import io.netty.buffer.Unpooled;
import io.netty.buffer.ByteBuf;
import java.nio.charset.Charset;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class HandlerNetty extends ChannelInboundHandlerAdapter {
    @Override
    public void channelReadComplete(ChannelHandlerContext chc) throws Exception {
        super.channelReadComplete(chc);
    }

    @Override
    public void channelRead(ChannelHandlerContext chc, Object ob) throws Exception {
        ByteBuf bb = (ByteBuf) ob;
        String msg = bb.toString(Charset.defaultCharset());
        System.out.println("Received text : " + msg);
        if(msg.equalsIgnoreCase("Hii I am Client\r\n")){
            chc.writeAndFlush(Unpooled.wrappedBuffer("Hello I am Server\r\n".getBytes()));
        }
        if(msg.equalsIgnoreCase("Hello I am Server\r\n")){
            chc.writeAndFlush(Unpooled.wrappedBuffer("Thankyou for the reply\r\n".getBytes()));
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext chc) throws Exception {
        super.channelActive(chc);
    }
}