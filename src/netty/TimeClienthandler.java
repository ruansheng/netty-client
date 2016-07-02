package netty;

import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;

import entity.Proto;
import entity.Sync;
import entity.Login;
import entity.Message;
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
		System.out.println("ttt");
		/*
		Message msg = new Message();
		msg.setId("123456789");
		msg.set_a("send");
		msg.setText("hello");
		msg.setTo("9527");
		msg.setFr("2536");
		msg.setType(1);
		
		String cmd = JSON.toJSONString(msg).toString();
		*/
		
		/*
		Login login = new Login();
		login.setAction("auth");
		login.setUid("9527");
		String cmd = JSON.toJSONString(login).toString();
		
		byte[] req = (cmd + System.getProperty("line.separator")).getBytes();
		ByteBuf bmsg = Unpooled.buffer(req.length);
		bmsg.writeBytes(req);
		ctx.writeAndFlush(bmsg);
		*/
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String)msg;
	    System.out.println("as:" + body);
	    
	    /*
		Proto proto = JSON.parseObject(body, Proto.class);
		if(proto.getAction().equals("msg-psh")) {
			Sync sync = new Sync();
			sync.setAction("msg-sync");
			sync.setFr("9527");
			String cmd = JSON.toJSONString(sync).toString();
			
			byte[] req = (cmd + System.getProperty("line.separator")).getBytes();
			ByteBuf bmsg = Unpooled.buffer(req.length);
			bmsg.writeBytes(req);
			ctx.writeAndFlush(bmsg);
		}*/
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.warning("Unexceptd exception from downstream :" + cause.getMessage());
		ctx.close();
	}
	
}
