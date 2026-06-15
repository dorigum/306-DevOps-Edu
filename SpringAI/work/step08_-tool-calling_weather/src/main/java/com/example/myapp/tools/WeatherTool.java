package com.example.myapp.tools;

import java.util.List;
import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherTool {

    @Value("${weather.api.key}")
    private String weatherApiKey;
    
    @Value("${weather.api.base-url}")
    private String weatherUrl;
    
    private final RestTemplate restTemplate = new RestTemplate();

    @Tool(
        name = "getWeather",
        description = "도시 이름을 기반으로 현재 날씨 정보를 조회합니다"
    )
    public WeatherResponse getWeather(
        @ToolParam(description = "날씨를 조회할 도시 이름 (예: Seoul, Tokyo)")
        String city
    ) {
        System.out.println("getWeather tool Spring AI에 의해 호출되었습니다.");
        String url = String.format("%s?units=metric&appid=%s&q=%s", weatherUrl, weatherApiKey,city
        );

        Map<String, Object> data = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> weatherList =
                (List<Map<String, Object>>) data.get("weather");
        String weather = (String) weatherList.get(0).get("description");

        Map<String, Object> main =
                (Map<String, Object>) data.get("main");
        double temperature = ((Number) main.get("temp")).doubleValue();

        return new WeatherResponse(city, weather, temperature);
    }
    
    public record WeatherResponse(String city, String weather, double temperature) {}
}
