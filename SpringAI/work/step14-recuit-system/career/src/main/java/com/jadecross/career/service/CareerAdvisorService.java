package com.jadecross.career.service;

import com.jadecross.career.client.CandidateClient;
import com.jadecross.career.client.CareerAdvisorClient;
import com.jadecross.career.client.JobClient;
import com.jadecross.career.dto.JobEvaluationResult;
import com.jadecross.career.dto.JobsComparisonResult;
import com.jadecross.career.dto.TailoredResume;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CareerAdvisorService {

    private final JobClient jobClient;
    private final CandidateClient candidateClient;
    private final CareerAdvisorClient advisorClient;

    public CareerAdvisorService(JobClient jobClient, CandidateClient candidateClient, CareerAdvisorClient advisorClient) {
        this.jobClient = jobClient;
        this.candidateClient = candidateClient;
        this.advisorClient = advisorClient;
    }

    public List<JobEvaluationResult> findJobs(Integer candidateId) {
        // 1. 후보자의 상세 정보를 가져온다.
        var candidate = this.candidateClient.getCandidateDetails(candidateId);

        // 2. 후보자의 스킬셋을 기반으로 관련된 일자리들을 검색한다.
        var jobs = this.jobClient.searchBySkills(candidate.skills()); // ex) [Java, Spring Boot, Hibernate, AWS]

        // 3. 각 일자리와 후보자의 매칭 점수를 계산한다.
        return this.advisorClient.evaluateJobs(candidate, jobs)
                                 .stream()
                                 .sorted(Comparator.comparingInt(JobEvaluationResult::matchScore).reversed())
                                 .toList();
    }


    public JobsComparisonResult compareJobs(Integer candidateId, List<Integer> jobIds) {
        //1.후보자 상세 정보를 가져온다.
        var candidate = this.candidateClient.getCandidateDetails(candidateId);
        //2.여러 일자리의 상세 정보를 가져온다. - 결과  List<JobDetails>
        var jobs = this.jobClient.getJobsDetails(jobIds);

        //3.후보자정보와 후보자가 선택한 여러 일자리를 비교
        return this.advisorClient.compareJobs(candidate, jobs);
    }

    public TailoredResume generateResume(Integer candidateId, Integer jobId) {
        // 1. 후보자 상세 정보를 가져온다.
        var candidate = this.candidateClient.getCandidateDetails(candidateId);

        // 2. jobId에 해당하는 일자리 상세 정보를 가져온다.
        var job = this.jobClient.getJobDetails(jobId);

        // 3. 후보자 정보와 일자리 정보를 기반으로 맞춤형 이력서를 생성한다.
        return this.advisorClient.generateResume(candidate, job);
    }












}
