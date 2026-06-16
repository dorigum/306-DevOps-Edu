package kosta.web.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeTools {
    @Tool(description = "요청한 도시의 현재 날짜와 시간 정보를 제공합니다.")
    public String getCurrentDateTime(@ToolParam(description = "TimeZone (예: Asia/Seoul)") String zonedId) {
        System.out.println("getCurrentDateTime 메소드 LLM을 통해 Spring AI가 호출하였습니다.");

        ZoneId zoneId = ZoneId.of(zonedId);
        ZonedDateTime now = ZonedDateTime.now(zoneId);

        String date = now.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        return "현재 날짜: %s, 현재 시간: %s".formatted(date, time);
    }
}