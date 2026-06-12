package kosta.web.ai.config;

import kosta.web.ai.advisor.MyLoggingAdvisor;
import kosta.web.ai.advisor.ReReadingAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {
    @Value("classpath:/prompts/system.st")
    private Resource resource;

//    @Bean
//    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
//        return chatClientBuilder
//                .defaultSystem(resource)
//                .defaultAdvisors(new MyLoggingAdvisor(), new ReReadingAdvisor(200)) // 전역으로 사전, 사후 처리
//                .build();
//    }

    // ----------------------------------------------------------------------
    // 대화를 기억시키기 위해서 ChatMemory, MessageChatMemoryAdvisor 사용
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        // MessageWindow
        ChatMemory chatMemory = MessageWindowChatMemory
                .builder()
                .maxMessages(10)
                .build();

        // 내장된 Advisor
        MessageChatMemoryAdvisor chatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(chatMemory)
                .build();

        return chatClientBuilder
                .defaultSystem(resource)
                .defaultAdvisors(chatMemoryAdvisor)
                .build();
    }
}