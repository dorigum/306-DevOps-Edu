package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.SpringAiService;

@RestController
public class SpringAiController {

    private final SpringAiService openAiService;
    
    public SpringAiController(SpringAiService openAiService) {
    	this.openAiService = openAiService;
    }

    @GetMapping("/chat/springai")
    public String chat(@RequestParam("q") String q) {
        return openAiService.chat(q);
    }
}