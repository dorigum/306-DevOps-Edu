package com.example.myapp.controller;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.ChatService;

import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    
    @GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> memoryStream(
            @RequestParam("id") String id,
            @RequestParam("q") String q) {
        return chatService.chatMemoryStream(q, id)
                .map(chunk -> ServerSentEvent.builder(chunk).build());
    }
    
}