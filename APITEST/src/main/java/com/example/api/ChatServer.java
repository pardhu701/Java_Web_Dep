package com.example.api;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatServer {

    private static final Map<String, Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        String query = session.getQueryString(); // user=john
        String userId = query.split("=")[1];

        clients.put(userId, session);

        System.out.println("User " + userId +
                " connected. Session = " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session senderSession) {




            try {
                if (!message.startsWith("TO:")) {
                    senderSession.getBasicRemote().sendText("Invalid message format");
                    return;
                }

                String[] parts = message.split(" ", 2);
                String toUser = parts[0].substring(3);  // after "TO:"
                String text = parts.length > 1 ? parts[1] : "";

                // Find senderId
                String senderId = clients.entrySet()
                        .stream()
                        .filter(e -> e.getValue().equals(senderSession))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("unknown");

                // Find receiver session
                Session receiverSession = clients.get(toUser);

                if (receiverSession != null && receiverSession.isOpen()) {
                    receiverSession.getBasicRemote().sendText(
                            "Private message from " + senderId + ": " + text
                    );
                } else {
                    senderSession.getBasicRemote().sendText(
                            "User " + toUser + " is not connected."
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @OnClose
    public void onClose(Session session) {
        clients.values().remove(session);
        System.out.println("Session disconnected: " + session.getId());
    }

    public static void broadcast(String message) {
        for (Session client : clients.values()) {
            try {
                client.getBasicRemote().sendText("Server: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
