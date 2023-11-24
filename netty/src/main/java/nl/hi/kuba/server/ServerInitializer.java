package nl.hi.kuba.server;

import io.netty.handler.codec.http.HttpObjectAggregator;

public class ServerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        pipeline.addLast(new ServerHandler());
    }

}