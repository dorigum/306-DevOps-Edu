package com.example.myapp.controller;

import java.util.List;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.ChatService;

import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatService chatService;
    private final ChatMemory chatMemory;

    public ChatController(ChatService chatService, ChatMemory chatMemory) {
        this.chatService = chatService;
        this.chatMemory = chatMemory;
    }

    @GetMapping(value = "/chat")
    public String memory(@RequestParam("id") String id, @RequestParam("q") String q) {
        return chatService.chatMemory(q, id);
    }
    
    @GetMapping(value = "/chat/stream", produces = "text/event-stream")
    public Flux<ServerSentEvent<String>> memoryStream(
            @RequestParam("id") String id,
            @RequestParam("q") String q) {
        return chatService.chatMemoryStream(q, id)
                .map(chunk -> ServerSentEvent.builder(chunk).build());
    }
    
    @GetMapping("/chat/history")
    public List<ChatMsgDto> history(@RequestParam("id") String conversationId) {
        return chatMemory.get(conversationId).stream()
                .map(m -> new ChatMsgDto(m.getMessageType().name(), m.getText()))
                .toList();
    }
    
    public record ChatMsgDto(String role, String content) {}
}