package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AiServiceListOutputConverter {
  // ##### 필드 #####
  private ChatClient chatClient;

  // ##### 생성자 #####
  public AiServiceListOutputConverter(ChatClient.Builder chatClientBuilder) {
    chatClient = chatClientBuilder.build();
  }
  
  /**
   * 흐름을파악하기 위해서 저수준(단계별로 일일이 해주는것) 으로 해보고 고수준(entity()해서 자동으로)으로 테스트 해본다.
   * */
  // ##### 메소드 -LowLevel(저수준) 단계별로  #####
  public List<String> listOutputConverterLowLevel(String city) {
    // 구조화된 출력 변환기 생성
    ListOutputConverter converter = new ListOutputConverter();
    // 프롬프트 템플릿 생성
    PromptTemplate promptTemplate = PromptTemplate.builder()
        .template("{city}에서 유명한 호텔 목록 5개를 출력하세요. {format}")
        .build();
    // 프롬프트 생성
    Prompt prompt = promptTemplate.create(
        Map.of("city", city, "format", converter.getFormat()));//getFormat()문자열을 가지고 있는 
     log.info(converter.getFormat());// LLM에게 어떤씩으로 만들어줘....
    // LLM의 쉼표로 구분된 텍스트 출력 얻기
    String commaSeparatedString = chatClient.prompt(prompt)
        .call()
        .content();
    // List<String>으로 변환
    List<String> hotelList = converter.convert(commaSeparatedString);
    return hotelList;
  }
  
  
  //고수준으로 사용하는것
  public List<String> listOutputConverterHighLevel(String city) {
    List<String> hotelList = 
    		chatClient.prompt()
	        .user("%s에서 유명한 호텔 목록 5개를 출력하세요.".formatted(city))
	        .call()
	        .entity(new ListOutputConverter()); //내부적으로 call하기전에 자동으로 getFormat() 해주고  convert해준다
    return hotelList;
  }
}
