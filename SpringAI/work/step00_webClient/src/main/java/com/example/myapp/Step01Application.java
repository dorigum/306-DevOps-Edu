package com.example.myapp;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.myapp.record.ChatRequest;
import com.example.myapp.record.ChatResponse;
import com.example.myapp.record.Message;

@SpringBootApplication
public class Step01Application {

	public static void main(String[] args) {
        SpringApplication.run(Step01Application.class, args);
    }

//    @Bean
//    CommandLineRunner runner(@Qualifier("openAiWebClient") WebClient client) { 
//        return _ -> {//사용하지 않는변수 경고 나올때 _로 변경가능
//            System.out.println("OpenAI WebClient Bean Test");
//
//            String response = client.get()
//                    .uri("/models")
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block();
//
//            System.out.println("OpenAI Response: " + response);
//
//        };
//    }
	
	 @Bean 
    CommandLineRunner runner(@Qualifier("openAiWebClient") WebClient client) {
		 
		 
         
        return _ -> {
            ChatRequest request = new ChatRequest(
                "gpt-4o-mini",
                List.of(new Message("user", "지구의 나이는?"))
            );
            
            ChatResponse response = client.post()
                .uri("/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();
            String content = response.choices().get(0).message().content();
            System.out.println("=== OpenAI Response ===");
            System.out.println(content);
        };
        
       
    }

}
