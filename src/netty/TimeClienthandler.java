package netty;

import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;

import connector.ConnecterCentext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClienthandler extends ChannelHandlerAdapter {
	
	private static final Logger logger = Logger.getLogger(TimeClienthandler.class.getName());
	
	public TimeClienthandler() {
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		ConnecterCentext conctx = new ConnecterCentext();
		conctx.setCid("ruansheng");
		conctx.setCom("jh");
		String cmd = JSON.toJSONString(conctx).toString();
	
		byte[] req = (cmd + System.getProperty("line.separator")).getBytes();
		ByteBuf msg = Unpooled.buffer(req.length);
	    msg.writeBytes(req);
		ctx.writeAndFlush(msg);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String)msg;
	    System.out.println(body);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.warning("Unexceptd exception from downstream :" + cause.getMessage());
		ctx.close();
	}
	
}
