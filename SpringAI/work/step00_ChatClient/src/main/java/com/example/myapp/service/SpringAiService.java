package com.example.myapp.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SpringAiService {

    private final ChatClient chatClient;

    public SpringAiService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String q) {
        return chatClient.prompt()
                .user(q)
                .call()
                .content();
    }
}