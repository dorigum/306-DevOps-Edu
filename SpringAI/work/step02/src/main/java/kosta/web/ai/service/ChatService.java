package kosta.web.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message) {
        return chatClient.prompt() // Prompt 객체 생성
                .user(message) // 사용자 메세지
                .call() // LLM 호출
                .content(); // LLM 응답
    }
}