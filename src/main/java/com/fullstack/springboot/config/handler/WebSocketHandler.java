package com.fullstack.springboot.config.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
	
	private final ObjectMapper mapper;
	
	private static final ConcurrentHashMap<String, WebSocketSession> clientSession = new ConcurrentHashMap<>();

	//웹소켓 연결 성공적 완료
	@Override 
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("웹소켓 연결 성공 " + session.getId());
        clientSession.put(session.getId(), session);
        
        String connectedMessage = "웹소켓 연결 성공적";
        session.sendMessage(new TextMessage(connectedMessage));  
    }

	
	@Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("메시지" + message.getPayload());

        clientSession.forEach((key, value) -> {
            try {
                if (!key.equals(session.getId())) {
                    value.sendMessage(message); 
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
    }
	
	
	@Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        clientSession.remove(session.getId());
        System.out.println("웹소켓 연결 종료 " + session.getId());
    }
}
