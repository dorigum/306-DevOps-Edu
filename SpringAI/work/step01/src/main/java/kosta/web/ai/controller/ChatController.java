package kosta.web.ai.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.model.ChatModel;
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
//        ChatOptions chatOptions = ChatOptions.builder()
//                .temperature(0.3)
//                .maxTokens(1000)
//                .build();
//
//        Prompt prompt = Prompt.builder()
//                .messages(systemMessage, userMessage)
//                .chatOptions(chatOptions)
//                .build();
//
//        return chatModel
//                .call(prompt)
//                .getResult()
//                .getOutput()
//                .getText();
//    }

    // ------------------------------------------------------------------------------
    // ChatGenerationMetadata
//    @GetMapping("/chat")
//    public ChatResponse chat(String message) {
//        SystemMessage systemMessage = SystemMessage.builder().text("당신은 Java 언어 및 JVM 아키첵처 전문가입니다.").build();
//        UserMessage userMessage = UserMessage.builder().text(message).build();
//        ChatOptions chatOptions = ChatOptions.builder().temperature(0.3).maxTokens(1000).build();
//        Prompt prompt = Prompt.builder().messages(systemMessage, userMessage).chatOptions(chatOptions).build();
//        ChatResponse chatResponse = chatModel.call(prompt);
//
//        return chatResponse;
//    }

    // ------------------------------------------------------------------------------
    // Generation
//    @GetMapping("/chat")
//    public Generation chat(String message) {
//        SystemMessage systemMessage = SystemMessage.builder().text("당신은 Java 언어 및 JVM 아키첵처 전문가입니다.").build();
//        UserMessage userMessage = UserMessage.builder().text(message).build();
//        ChatOptions chatOptions = ChatOptions.builder().temperature(0.3).maxTokens(1000).build();
//        Prompt prompt = Prompt.builder().messages(systemMessage, userMessage).chatOptions(chatOptions).build();
//        ChatResponse chatResponse = chatModel.call(prompt);
//        Generation generation = chatResponse.getResult();
//
//        return generation;
//    }


    // AssistantMessage
//    @GetMapping("/chat")
//    public AssistantMessage chat(String message) {
//        SystemMessage systemMessage = SystemMessage.builder().text("당신은 Java 언어 및 JVM 아키첵처 전문가입니다.").build();
//        UserMessage userMessage = UserMessage.builder().text(message).build();
//        ChatOptions chatOptions = ChatOptions.builder().temperature(0.3).maxTokens(1000).build();
//        Prompt prompt = Prompt.builder().messages(systemMessage, userMessage).chatOptions(chatOptions).build();
//        ChatResponse chatResponse = chatModel.call(prompt);
//        AssistantMessage assistantMessage = chatResponse.getResult().getOutput();
//
//        return assistantMessage;
//    }

    // ------------------------------------------------------------------------------
    // Text
//    @GetMapping("/chat")
//    public String chat(String message) {
//        SystemMessage systemMessage = SystemMessage.builder().text("당신은 Java 언어 및 JVM 아키첵처 전문가입니다.").build();
//        UserMessage userMessage = UserMessage.builder().text(message).build();
//        ChatOptions chatOptions = ChatOptions.builder().temperature(0.3).maxTokens(1000).build();
//        Prompt prompt = Prompt.builder().messages(systemMessage, userMessage).chatOptions(chatOptions).build();
//        ChatResponse chatResponse = chatModel.call(prompt);
//        String string = chatResponse.getResult().getOutput().getText();
//
//        return string;
//    }

    // ------------------------------------------------------------------------------
    // ChatGenerationMetadata
//    @GetMapping("/chat")
//    public ChatResponseMetadata chat(String message) {
//        SystemMessage systemMessage = SystemMessage.builder().text("당신은 Java 언어 및 JVM 아키첵처 전문가입니다.").build();
//        UserMessage userMessage = UserMessage.builder().text(message).build();
//        ChatOptions chatOptions = ChatOptions.builder().temperature(0.3).maxTokens(1000).build();
//        Prompt prompt = Prompt.builder().messages(systemMessage, userMessage).chatOptions(chatOptions).build();
//        ChatResponse chatResponse = chatModel.call(prompt);
//        ChatResponseMetadata chatResponseMetadata = chatResponse.getMetadata();
//
//        return chatResponseMetadata;
//    }

    // ------------------------------------------------------------------------------
    // Usage
//    @GetMapping("/chat")
//    public String chat(String message) {
//        SystemMessage systemMessage = SystemMessage.builder().text("당신은 Java 언어 및 JVM 아키첵처 전문가입니다.").build();
//        UserMessage userMessage = UserMessage.builder().text(message).build();
//        ChatOptions chatOptions = ChatOptions.builder().temperature(0.3).maxTokens(1000).build();
//        Prompt prompt = Prompt.builder().messages(systemMessage, userMessage).chatOptions(chatOptions).build();
//        ChatResponse chatResponse = chatModel.call(prompt);
//
//        // 1. 답변 텍스트 추출
//        String content = chatResponse.getResult().getOutput().getText();
//
//        // 2. 토큰 사용량 확인 (비용 추적용)
//        Usage usage = chatResponse.getMetadata().getUsage();
//
//        System.out.printf("질문 토큰: %d, 답변 토큰: %d%n", usage.getPromptTokens(), usage.getCompletionTokens());
//
//        // 3. 종료 사유 확인 (길이 제한으로 잘렸는지 등)
//        String finishReason = chatResponse.getResult().getMetadata().getFinishReason();
//
//        System.out.println("종료 사유: " + finishReason);
//
//        return content;
//    }

    // 비동기 방식
    @GetMapping("/chat-async")
    public Flux<String> async(String message) {

        return chatModel.stream(message);
    }
}