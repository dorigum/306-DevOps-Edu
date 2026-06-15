package kosta.web.ai.config;

//import kosta.web.ai.tools.DateTimeTools;

import kosta.web.ai.tools.DateTimeTools;
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

    private final DateTimeTools dateTimeTools;

    public AppConfig(DateTimeTools dateTimeTools) {
        this.dateTimeTools = dateTimeTools;
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        ChatMemory chatMemory = MessageWindowChatMemory
                .builder()
                .maxMessages(10)
                .build();

        MessageChatMemoryAdvisor chatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(chatMemory)
                .build();

        return chatClientBuilder
                .defaultSystem(resource)
                .defaultAdvisors(chatMemoryAdvisor)
                .defaultTools(dateTimeTools)
                .build();
    }
}