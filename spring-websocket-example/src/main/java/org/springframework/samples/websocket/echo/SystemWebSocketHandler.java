package org.springframework.samples.websocket.echo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandler implements WebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);
    private static final List<WebSocketSession> users = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(
			WebSocketSession session) throws Exception {
		logger.debug("connect to the websocket success......");  
        System.out.println("connect to the websocket success......");  
        session.sendMessage(new TextMessage("Server:connected OK!"));  
        users.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> wsm) throws Exception {
		logger.debug("websocket handle message......");
		
        TextMessage returnMessage = new TextMessage(wsm.getPayload().toString());  
        session.sendMessage(returnMessage); 
        //sendMessageToUsers(wss, wsm); 
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable ex) throws Exception {
		if (session.isOpen()) {  
			session.close();  
        }  
        users.remove(session);  
        logger.debug("websocket connection closed happen error......");  
        System.out.println("websocket connection closed happen error......");  
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus cs) throws Exception {
		System.out.println("websocket connection closed......");  
        users.remove(session);  
        logger.debug("websocket connection closed......");

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.isOpen()) {
				try {
					user.sendMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void sendMessageToUser(String username, TextMessage message) {
		for (WebSocketSession user : users) {
			try {
				user.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			/*
			 * if (user.getAttributes().get("username").equals(username)) { try
			 * { if (user.isOpen()) { user.sendMessage(message); } } catch
			 * (IOException e) { e.printStackTrace(); } break; }
			 */
		}
	}
}
