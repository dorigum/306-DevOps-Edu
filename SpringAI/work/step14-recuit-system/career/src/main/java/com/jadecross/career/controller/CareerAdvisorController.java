package com.jadecross.career.controller;

import com.jadecross.career.dto.JobEvaluationResult;
import com.jadecross.career.dto.JobsComparisonResult;
import com.jadecross.career.dto.TailoredResume;
import com.jadecross.career.service.CareerAdvisorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/career-advisor")
public class CareerAdvisorController {

    private static final Logger log = LoggerFactory.getLogger(CareerAdvisorController.class);
    private final CareerAdvisorService careerAdvisorService;

    public CareerAdvisorController(CareerAdvisorService careerAdvisorService) {
        this.careerAdvisorService = careerAdvisorService;
    }

    /**
     * 예) http://localhost:8083/api/career-advisor/find-jobs?candidateId=1
     *
     * */
    @GetMapping("/find-jobs")
    public List<JobEvaluationResult> findJobs(@RequestParam Integer candidateId) {
        log.info("Finding jobs for candidateId: {}", candidateId);
        return this.careerAdvisorService.findJobs(candidateId);
    }

    /**
     * 후보자가 선택한 여러 개의 직업에 대해 비교 분석 결과를 반환하는 엔드포인트
        * 예) http://localhost:8083/api/career-advisor/compare-jobs?candidateId=1&jobIds=1,2,3
     * */
    @GetMapping("/compare-jobs")
    public JobsComparisonResult compareJobs(@RequestParam Integer candidateId, @RequestParam List<Integer> jobIds) {
        log.info("Comparing jobs for candidateId: {}, jobIds: {}", candidateId, jobIds);
        return this.careerAdvisorService.compareJobs(candidateId, jobIds);
    }

    @GetMapping("/generate-resume")
    public TailoredResume generateResume(@RequestParam Integer candidateId, @RequestParam Integer jobId) {
        log.info("Generating resume for candidateId: {}, jobId: {}", candidateId, jobId);
        return this.careerAdvisorService.generateResume(candidateId, jobId);
    }

}
