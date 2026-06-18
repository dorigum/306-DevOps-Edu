package com.jadecross.career.client;

import com.jadecross.career.dto.*;
import org.springframework.ai.chat.client.AdvisorParams;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import tools.jackson.databind.json.JsonMapper;
//import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.List;

public class CareerAdvisorClient {

    private static final String JOB = "job";
    private static final String JOBS = "jobs";
    private static final String CANDIDATE = "candidate";

    private final ChatClient chatClient;
    private final CareerAdvisorPrompts prompts;
    private final JsonMapper jsonMapper;

    public CareerAdvisorClient(ChatClient chatClient, CareerAdvisorPrompts prompts, JsonMapper jsonMapper) {
        this.chatClient = chatClient;
        this.prompts = prompts;
        this.jsonMapper = jsonMapper;
    }

    public List<JobEvaluationResult> evaluateJobs(CandidateDetails candidate, List<JobSummary> jobs) {
        JobEvaluationResponse response = this.chatClient.prompt()
                /**AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT은 LLM이 자유로운 텍스트가 아닌 JSON Schema
                 * 기반의 구조화된 응답을 생성하도록 하여, Spring AI가 결과를 Java 객체(.entity())로
                 * 안정적으로 변환할 수 있게 해주는 옵션*/
                .advisors(AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT)
                .system(this.prompts.evaluateJobs().system())
                .user(spec -> spec.text(this.prompts.evaluateJobs().user())
                        .param(CANDIDATE, this.toJsonString(candidate))
                        .param(JOBS, this.toJsonString(jobs)))
                .call()
                .entity(JobEvaluationResponse.class);

        return response.results();
    }

    public JobsComparisonResult compareJobs(CandidateDetails candidate, List<JobDetails> jobs) {
        return this.chatClient.prompt()
                              .advisors(AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT)//Spring AI에서 LLM(OpenAI, Anthropic 등)의 Native Structured Output 기능을 활성화하기 위한 Advisor 파라미터
                              .system(this.prompts.compareJobs().system())
                              .user(spec -> spec.text(this.prompts.compareJobs().user())
                                                .param(CANDIDATE, this.toJsonString(candidate))
                                                .param(JOBS, this.toJsonString(jobs)))
                              .call()
                              .entity(JobsComparisonResult.class);
    }

    public TailoredResume generateResume(CandidateDetails candidate, JobDetails job) {
        var resume = this.chatClient.prompt()
                              .system(this.prompts.generateResume().system())
                              .user(spec -> spec.text(this.prompts.generateResume().user())
                                                .param(CANDIDATE, this.toJsonString(candidate))
                                                .param(JOB, this.toJsonString(job)))
                              .call()
                              .content();

        return new TailoredResume(job.id(), candidate.id(), resume);
    }

    // Providing data as structured JSON ensures the model correctly maps attributes between the candidate and job requirements.
    private String toJsonString(Object object) {
        return this.jsonMapper.writeValueAsString(object);
    }

}
