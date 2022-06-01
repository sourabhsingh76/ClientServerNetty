import io.netty.channel.ChannelFuture;
import io.netty.buffer.Unpooled;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            ServerFlowNetty.OpenServerPort(15000).channel().closeFuture().sync();
        } else {
            try {
                ClientNetty cn = new ClientNetty(15000);
                ChannelFuture cf = cn.connectLoop();
                Thread.sleep(4000);
                if (cf.isSuccess()) {
                    cf.channel().writeAndFlush(Unpooled.wrappedBuffer("Hii I am Client\r\n".getBytes()));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("First start the server!");
            } finally{

            }
        }
    }
}