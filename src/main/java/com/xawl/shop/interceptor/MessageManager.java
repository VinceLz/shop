package com.xawl.shop.interceptor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.ddpush.im.v1.node.IMServer;
import org.ddpush.im.v1.node.PushMessage;

import com.xawl.shop.util.PushMessageUtil;

public class MessageManager implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				PushMessageUtil.stop();
			}
		}).start();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				PushMessageUtil.stop();
				PushMessageUtil.start();
			}
		}).start();
	}
}
