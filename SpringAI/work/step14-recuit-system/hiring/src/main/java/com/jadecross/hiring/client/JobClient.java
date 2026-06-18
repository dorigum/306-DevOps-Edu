package com.jadecross.hiring.client;

import com.jadecross.hiring.dto.JobDetails;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

public class JobClient {

    private static final String JOB_BY_ID_URI = "/api/jobs/{id}";
    private static final String JOBS_BY_IDS_URI = "/api/jobs?ids={ids}";
    private final RestClient restClient;

    public JobClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public JobDetails getJobDetails(Integer id) {
        return this.restClient.get()
                .uri(JOB_BY_ID_URI, id)
                .retrieve()
                .body(JobDetails.class);
    }

    public List<JobDetails> getJobsDetails(List<Integer> ids) {
        // 1. [1, 2] -> "1,2" 변환
        String idsParam = ids.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        return this.restClient.get()
                .uri(JOBS_BY_IDS_URI, idsParam)
                .retrieve()
                .body(new ParameterizedTypeReference<List<JobDetails>>() {
                });

//        return this.restClient.get()
//                .uri(JOBS_BY_IDS_URI, ids)
//                .retrieve()
//                .body(new ParameterizedTypeReference<List<JobDetails>>() {
//                });
    }
}