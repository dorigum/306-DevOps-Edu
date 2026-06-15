package com.example.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class WebClientService {

    private final WebClient openAiWebClient;
    
    public WebClientService(@Qualifier("openAiWebClient") WebClient openAiWebClient) {
        this.openAiWebClient = openAiWebClient;
    }

    public Mono<String> getChatCompletion(String userInput) {
        Map<String, Object> requestBody = Map.of(
            "model", "gpt-4o-mini",
            "messages", new Object[] {
                Map.of("role", "user", "content", userInput)
            }
        );

        return openAiWebClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    var message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                });
    }
}