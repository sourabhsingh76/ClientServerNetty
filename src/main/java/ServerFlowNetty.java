import io.netty.channel.ChannelFuture;

public class ServerFlowNetty {
    public static ChannelFuture OpenServerPort(int portNumber) {
        try {
            ServerNetty serverNetty = new ServerNetty(portNumber);
            serverNetty.connectLoop();
            return serverNetty.getChannelFuture();
        } catch (Exception e) {
        }
        return null;
    }
}
