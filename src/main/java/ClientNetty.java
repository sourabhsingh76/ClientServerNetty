import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.bootstrap.Bootstrap;

public class ClientNetty {
    Channel ch;
    int portNumber;
    EventLoopGroup elg = new NioEventLoopGroup();

    public ClientNetty(int portNumber) {
        this.portNumber = portNumber;
    }

    public ChannelFuture connectLoop() throws Exception {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(elg);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast(new HandlerNetty());
                }
            });
            ChannelFuture cf2 = bootstrap.connect("127.0.0.1", this.portNumber).sync();
            this.ch = cf2.channel();

            return cf2;
        } finally {
        }
    }

    public void shutDown() {
        elg.shutdownGracefully();
    }
}
