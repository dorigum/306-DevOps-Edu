package kosta.web.ai.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

import java.util.Optional;

// CallAdvisor는 일반적인 동기 호출용 Advisor
public class MyLoggingAdvisor implements CallAdvisor {
    private static final Logger log = LoggerFactory.getLogger(MyLoggingAdvisor.class);

//    @Override
//    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
//
//
//		// 전처리에서는 로깅처리
//        log.info("ChatClientRequest: {}", chatClientRequest);
//        var chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
//
//        // 후처리에서는 Advisor Parma을 이용하여 로깅 제어
//        log.info("ChatClientResponse: {}", chatClientResponse);
//
//        return chatClientResponse;
//    }

    // ----------------------------------------------------------------------
    // Shared Context 사용하기
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        // 공유 컨텍스트에서 데이터 조회하기
        // var logEnabled = chatClientRequest.context().get("logEnabled");
        var logEnabled = Optional.ofNullable(chatClientRequest.context().get("logEnabled"))
                .map(Object::toString)
                .map(Boolean::parseBoolean)
                .orElse(false);

        // 전처리에서는 로깅처리
        log.info("ChatClientRequest: {}", chatClientRequest);
        var chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        // 후처리에서는 Advisor Parma을 이용하여 로깅 제어
        if (logEnabled) {
            log.info("ChatClientResponse: {}", chatClientResponse);
        }

        return chatClientResponse;
    }

    // Advisor는 자신이 갖고 있는 Order 번호를 기준으로 오름차순으로 실행됩니다.
    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}