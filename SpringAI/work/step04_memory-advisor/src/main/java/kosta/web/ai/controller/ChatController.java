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

//    @GetMapping("/chat")
//    String chat(String subject, String tone, String message) {
//        return chatService.chat(subject, tone, message);
//    }

    // 대화 기억 시키기
    @GetMapping("/chat")
    String chat(String subject, String tone, String message, HttpSession session) {
        System.out.println("session.getId() = " + session.getId());

        return chatService.chat(subject, tone, message, session.getId());
    }
}