package kosta.web.ai.config;

import kosta.web.ai.tools.GmailTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolConfig {
    @Bean
    ToolCallbackProvider gmailTools(GmailTool gmailTool) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(gmailTool)
                .build();
    }
}
