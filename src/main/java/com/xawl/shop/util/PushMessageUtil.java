package com.xawl.shop.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PushMessageUtil {

	public static void start() {

		IMServer server = IMServer.getInstance();
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	public static void stop() {
		String cmd = "stop";
		Socket s = null;
		try {
			s = new Socket("localhost", 9900);
			s.setSoTimeout(1000 * 10);
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			out.write(cmd.getBytes());
			out.write(3);
			out.flush();
			out.close();
			StringBuffer sb = new StringBuffer();
			int ch = -1;
			while (true) {
				try {
					ch = in.read();
				} catch (Exception e) {
					ch = -1;
				}
				if (ch < 0) {
					break;
				}
				sb.append((char) ch);
			}
			System.out.println(sb.toString());
		} catch (java.net.ConnectException ce) {
			System.out.println("can not connect to server, is server up?");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static void send(String uid, boolean business, String status) {
		if (business) {
			// 商家启动 停用信息
			try {
				Pusher pusher = new Pusher("localhost", 9999, 1000 * 5);
				boolean result = pusher.push0x20Message(Util.md5Byte(uid),
						status.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				Pusher pusher = new Pusher("localhost", 9999, 1000 * 5);
				boolean result = pusher.push0x10Message(Util.md5Byte(uid + ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
