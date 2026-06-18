package com.jadecross.candidate.service;

import com.jadecross.candidate.dto.CandidateDetails;
import com.jadecross.candidate.mapper.EntityDtoMapper;
import com.jadecross.candidate.repository.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateDetails getCandidateDetails(Integer id) {
        return this.candidateRepository.findById(id)
                .map(EntityDtoMapper::toCandidateDetails)
                .orElseThrow();
    }

}
