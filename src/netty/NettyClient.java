package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

	private Channel channel;
	
	/**
	 * 连接 server
	 * @param port
	 * @param host
	 * @throws Exception
	 */
	public void connect(int port, String host) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelChild());
			ChannelFuture f = b.connect(host, port).sync();
			channel = f.channel();
		}catch (InterruptedException e) {
		    e.printStackTrace();
		    group.shutdownGracefully();
		}
	}
	
	/**
	 * 发送消息
	 * @param msg
	 */
	public void sendMessage(String msg) {
		System.out.println("4");
		try {
	        if (channel != null && channel.isOpen()) {
	        	byte[] req = (msg + System.getProperty("line.separator")).getBytes();
	    		ByteBuf bmsg = Unpooled.buffer(req.length);
	    		bmsg.writeBytes(req);
	        	this.channel.writeAndFlush(bmsg).sync();
			} else {
                throw new Exception("channel is null | closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
	}
	
}
