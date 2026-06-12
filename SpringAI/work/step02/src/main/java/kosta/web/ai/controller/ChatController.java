package kosta.web.ai.controller;

import kosta.web.ai.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;
//    private final ChatClient chatClient;
//
//    @PostConstruct
//    public void init() {
//        System.out.println("ChatController initialize with chatModel: " + chatClient);
//    }
//
//    public ChatController(ChatClient.Builder builder) {
//        this.chatClient = builder.build();
//    }

    private ChatService charService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(String message) {
        return chatService.chat(message);
    }
}