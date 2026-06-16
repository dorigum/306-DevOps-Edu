package kosta.web.ai.service;

//import kosta.web.ai.tools.FileStorageTools;
//import kosta.web.ai.tools.SearchTools;

import kosta.web.ai.tools.FileStorageTools;
import kosta.web.ai.tools.SearchTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    // 현재 날짜와 날씨 정보 tool calling
    public String chat(String subject, String tone, String message, String sessionId) {
        return chatClient.prompt()
                .tools(new SearchTools(), new FileStorageTools())
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, sessionId))
                .user(message)
                .call()
                .content();
    }
}