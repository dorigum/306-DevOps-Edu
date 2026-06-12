package kosta.web.ai.advisor;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.AdvisorChain;
import org.springframework.ai.chat.client.advisor.api.BaseAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;

import java.util.Map;

/*
 * BaseAdvisor는 CallAdvisor를 구현하기 쉽게 만들어 놓은 추상 클래스
 * 사전, 사후 메소드가 분리되어 있음
 */
public class ReReadingAdvisor implements BaseAdvisor {
    private final String re2AdviseTemplate = """
            {re2_input_query}
            다시 한번 질문을 읽어보세요: {re2_input_query}
            """;
    private int order = 0;

    public ReReadingAdvisor() {
    }

    public ReReadingAdvisor(int order) {
        this.order = order;
    }

    // 요청에서 사용자 입력 프롬프트를 Re2 적용된 프롬프트로 바꾼다.
    @Override
    public ChatClientRequest before(ChatClientRequest chatClientRequest,
                                    AdvisorChain advisorChain) {
        // 1. 추출(Extract)
        var originUserMessage = chatClientRequest.prompt()
                .getUserMessage()
                .getText();

        System.out.println("originUserMessage: " + originUserMessage);

        // 2. 증강(Augment): rereading
        String augmentedUserText = PromptTemplate.builder()
            .template(this.re2AdviseTemplate)
            .variables(Map.of("re2_input_query", originUserMessage))
            .build()
            .render();

        System.out.println("augmentedUserText: " + augmentedUserText);

        // 3. 교체(Mutate)
        return chatClientRequest.mutate()
                .prompt(chatClientRequest
                        .prompt()
                        .augmentUserMessage(augmentedUserText))
                .build();
    }

    @Override
    public ChatClientResponse after(ChatClientResponse chatClientResponse,
                                    AdvisorChain advisorChain) {
        return chatClientResponse;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    public ReReadingAdvisor withOrder(int order) {
        this.order = order;
        return this;
    }
}