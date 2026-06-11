package kosta.web.ai.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {
    @Autowired
    private ChatModel chatModel;

    @PostConstruct
    public void init() {
        System.out.println("ChatController initialize with chatModel: " + chatModel);
    }

    // 동기 방식
//    @GetMapping("/chat")
//    public String chat(String message) {
//        return chatModel.call(message);
//    }

    // Prompt 클래스 사용하기
//    @GetMapping("/chat")
//    public String chat(String message) {
//        SystemMessage systemMessage = SystemMessage.builder()
//                .text("당신은 Java 언어 및 JVM 아키첵처 전문가입니다.")
//                .build();
//
//        UserMessage userMessage = UserMessage.builder()
//                .text(message)
//                .build();
//
//        Prompt prompt = Prompt.builder()
//                .messages(systemMessage, userMessage)
//                .build();
//
//        return chatModel
//                .call(prompt)
//                .getResult()
//                .getOutput()
//                .getText();
//    }

    // ------------------------------------------------------------------------------
    // ChatOptions
    @GetMapping("/chat")
    public String chat(String message) {
        SystemMessage systemMessage = SystemMessage.builder()
                .text("당신은 Java 언어 및 JVM 아키첵처 전문가입니다.")
                .build();

        UserMessage userMessage = UserMessage.builder()
                .text(message)
                .build();

        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0.3)
                .maxTokens(1000)
                .build();

        Prompt prompt = Prompt.builder()
                .messages(systemMessage, userMessage)
                .chatOptions(chatOptions)
                .build();

        return chatModel
                .call(prompt)
                .getResult()
                .getOutput()
                .getText();
    }

    // 비동기 방식
    @GetMapping("/chat-async")
    public Flux<String> async(String message) {

        return chatModel.stream(message);
    }
}