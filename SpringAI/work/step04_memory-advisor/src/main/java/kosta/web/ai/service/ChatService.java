package kosta.web.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

//    public String chat(String subject, String tone, String message) {
//        return chatClient.prompt()
//                .user(message)
//                // LLM 호출
//                .call()
//                // 응답 본문(String) 추출
//                .content();
//    }

    // -----------------------------------------------------------------------
    // 2. Shared Context Test - 요청할 때 값 설정
//    public String chat(String subject, String tone, String message) {
//        System.out.println("Service chat.......");
//
//        return chatClient.prompt()
//            .advisors(advisor -> advisor.param("logEnabled", true))
//            .user(message)
//            .call()
//            .content();
//    }

    /*
     * AI와 대화하는 메소드
     *
     * @param subject   대화 주제 (현재 사용하지 않음)
     * @param tone      답변 톤 (현재 사용하지 않음)
     * @param message   사용자가 입력한 질문
     * @param sessionId 대화방(세션) 식별자
     * @return AI 응답 결과
     */
    public String chat(String subject, String tone, String message, String sessionId) {
        return chatClient.prompt()
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, sessionId))
                .user(message)
                // LLM 호출
                .call()
                // 응답 본문(String) 추출
                .content();
    }
}