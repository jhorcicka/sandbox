package nl.hi.kuba.server;

import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    private static final int HTTP_PORT = 9999;

    public void run() throws Exception {

        // Create the multithreaded event loops for the server
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // A helper class that simplifies server configuration
            ServerBootstrap httpBootstrap = new ServerBootstrap();

            // Configure the server
            httpBootstrap.group(bossGroup, workerGroup)
                         .channel(NioServerSocketChannel.class)
                         .childHandler(new ServerInitializer()) // <-- Our handler created here
                         .option(ChannelOption.SO_BACKLOG, 128)
                         .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture httpChannel = httpBootstrap.bind(HTTP_PORT).sync();

            // Wait until server socket is closed
            httpChannel.channel().closeFuture().sync();
        }
        finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new AppServer().run();
    }
}
