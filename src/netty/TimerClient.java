package netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;

import entity.Login;
import entity.Message;

public class TimerClient {
	
	public static void main(String[] args) throws Exception {
		int port = 8080;
		NettyClient nc = new NettyClient();
		nc.connect(port, "127.0.0.1");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String uid = null;
        System.out.println("Enter your uid:");
        uid = br.readLine();
        if(uid.equals("")) {
        	return ;
        }
        
        // 登录
        Login login = new Login();
		login.setAction("auth");
		login.setUid(uid);
		String cmd = JSON.toJSONString(login).toString();
		nc.sendMessage(cmd);
		
		int i = 0;
		Boolean quit = false;
		while(!quit) {
			System.out.println("Enter msg to uid:");
			String to = br.readLine();
			Message msg = new Message();
			msg.setId("123456789");
			msg.setAction("msg");
			msg.setText("hello:" + String.valueOf(i));
			msg.setTo(to);
			msg.setFr(uid);
			msg.setType(1);
			
			String tcmd = JSON.toJSONString(msg).toString();
			nc.sendMessage(tcmd);
			i++;
		}
	}

}
