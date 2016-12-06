package org.suliga.trantor.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TrantorWebSocketController extends TextWebSocketHandler  {
	private static final Logger logger = LoggerFactory.getLogger(TrantorWebSocketController.class);
	private static MyThread myThread;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		logger.info("Received WebSocket msg: " + msg);
		if (msg.equalsIgnoreCase("start")) {
			if (myThread == null) {
				myThread = new MyThread(session);
				myThread.start();
			}
		}
		else if (msg.equalsIgnoreCase("stop")) {
			if (myThread != null) {
				myThread.setStop();
				myThread = null;
			}
		}
	}
	
	class MyThread extends Thread {
		private boolean stop;
		private WebSocketSession session;
		private int count;
		public MyThread(WebSocketSession session) {
			this.session = session;
		}
		public void setStop() {
			stop = true;
		}
		public void run() {
			try {
				while (!stop) {
					session.sendMessage(new TextMessage(Integer.toString(++count)));
					Thread.sleep(1000);
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

