package com.example.myapp.service;

import com.example.myapp.tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class ChatService {
	
    private final ChatClient chatClient;
   private final WeatherTool weatherTool;
    public ChatService(ChatClient chatClient, WeatherTool weatherTool) {
       this.chatClient = chatClient;
        this.weatherTool = weatherTool;
    }

    public Flux<String> chatMemoryStream(String userInput, String userId) {
        return chatClient.prompt()
                .tools(weatherTool)
            .user(userInput)
            .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, userId))
            .stream()     // 스트리밍 모드로 호출
            .content()    // Flux<String> 반환
            .map(this::preserveSseLeadingSpace) // 공백 보정
            .concatWith(Flux.just("[[END]]")); // 스트리밍 종료 신호
    }
    
    private String preserveSseLeadingSpace(String chunk) {
        if (chunk == null) return "";
        return chunk.startsWith(" ") ? " " + chunk : chunk;
    }
}