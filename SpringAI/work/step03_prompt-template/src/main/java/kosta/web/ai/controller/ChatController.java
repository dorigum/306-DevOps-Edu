package kosta.web.ai.controller;

import kosta.web.ai.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    String chat(String subject, String tone, String message) {
        return chatService.chat(subject, tone, message);
    }
}