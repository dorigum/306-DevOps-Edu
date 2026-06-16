package kosta.web.ai.controller;

import kosta.web.ai.service.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    String chat(String subject, String tone, String message, HttpSession httpSession) {
        String sessionId = httpSession.getId();
        String result = chatService.chat(subject, tone, message, sessionId);

        System.out.println("End...........");

        return result;
    }
}