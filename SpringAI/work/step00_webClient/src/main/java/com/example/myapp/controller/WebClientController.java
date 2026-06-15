package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.WebClientService;

import reactor.core.publisher.Mono;

@RestController
public class WebClientController {

    private final WebClientService openAiService;
    
    public WebClientController(WebClientService openAiService) {
        this.openAiService = openAiService;
    }
    
    @GetMapping("/chat/webclient")
    public Mono<String> chat(@RequestParam("q") String q) {
        return openAiService.getChatCompletion(q);
    }
}