package net.johnsonlau.netty.websocket.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyChannelInboundHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	log.info("MyChannelInboundHandler.channelActive: {}", this.hashCode());
        ctx.fireChannelActive();
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
    	log.info("MyChannelInboundHandler.channelRead0: {}", this.hashCode());
		if (msg instanceof TextWebSocketFrame) {
			TextWebSocketFrame textMsg = (TextWebSocketFrame) msg;
			log.info(textMsg.text());
			ctx.writeAndFlush(new TextWebSocketFrame("pong"));
		}
	}
}
