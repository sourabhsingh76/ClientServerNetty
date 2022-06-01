import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;

public class ServerNetty {
    int port;

    @Getter
    Channel channel;

    @Getter
    ChannelFuture channelFuture;

    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    public ServerNetty(int port){
        this.port = port;
    }

    public void connectLoop() throws Exception{
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HandlerNetty());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();


            this.channel = f.channel();
            this.channelFuture = f;
        }
        catch(Exception e) {
            System.out.println("Exception in server thread");
        }finally {

        }
    }
    public void shutdown(){
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
