package com.jadecross.hiring.service;

import com.jadecross.hiring.client.HiringAdvisorClient;
import com.jadecross.hiring.client.JobClient;
import com.jadecross.hiring.dto.JobApplicationSubmittedEvent;
import com.jadecross.hiring.mapper.EntityDtoMapper;
import com.jadecross.hiring.repoistory.JobApplicationRepository;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class JobApplicationEvaluationListener {

    private final JobClient jobClient;
    private final HiringAdvisorClient advisorClient;
    private final JobApplicationRepository repository;

    public JobApplicationEvaluationListener(JobClient jobClient, HiringAdvisorClient advisorClient, JobApplicationRepository repository) {
        this.jobClient = jobClient;
        this.advisorClient = advisorClient;
        this.repository = repository;
    }

    @Async
    @TransactionalEventListener
    public void handle(JobApplicationSubmittedEvent event) {
        // event.applicationId()는 등록된 이력서 id
        System.out.println("event.applicationId()는 등록된 이력서 id = " + event.applicationId());

        // 아래 문장 실행 결과에는 jobApplication, 평가, 평가 이유 X
        var jobApplication = this.repository.findById(event.applicationId()).orElseThrow();

        // 채용 공고 id를 가지고 AI쪽으로 이동 -> 채용 공고의 상세 정보를 가져온다. -> 채용 공고의 상세 정보에는 어떤
        var jobDetails = this.jobClient.getJobDetails(jobApplication.getJobId());

        // 이력서 + 채용 공고 상세 정보를 AI에 전달하려는 필요한 정보만 추출해서 하나의 객체 반환
        var evaluationRequest = EntityDtoMapper.toJobApplicationEvaluationRequest(jobApplication, jobDetails);

        // 평가를 요청하면 그 결과가 점수, 이유 반환
        var evaluationResponse = this.advisorClient.evaluate(evaluationRequest);

        //
        jobApplication.setMatchScore(evaluationResponse.matchScore());
        jobApplication.setMatchReasoning(evaluationResponse.matchReasoning());

        this.repository.save(jobApplication); // 수정
    }
}