package kosta.web.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.web.client.RestClient;

public class SearchTools {
    @Tool(description = "네이버 검색을 이용하여 최신 정보를 조회합니다.")
    public String searchWEB(@ToolParam(description = "검색할 최신 정보 쿼리") String query) {
        System.out.println("'네이버 검색'도구를 LLM 을 통해 Spring AI 가 호출하였습니다. ");

        RestClient restClient = RestClient.create();

        String re = restClient.get()
                .uri("https://openapi.naver.com/v1/search/blog?query=" + query)
                .header("X-Naver-Client-Id", "dQ4Nte_mJk4xKzssXKcp")
                .header("X-Naver-Client-Secret", "Y4Norutmk_")
                .retrieve().body(String.class);

        System.out.println("-------------------------------");
        System.out.println("네이버 검색 결과: " + re);
        System.out.println("-------------------------------");

        return re;
    }
}