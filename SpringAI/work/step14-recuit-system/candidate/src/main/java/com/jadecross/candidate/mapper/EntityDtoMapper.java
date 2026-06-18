package com.jadecross.candidate.mapper;

import com.jadecross.candidate.dto.CandidateDetails;
import com.jadecross.candidate.entity.Candidate;
import com.jadecross.candidate.entity.WorkExperience;

public class EntityDtoMapper {

    public static CandidateDetails toCandidateDetails(Candidate candidate){
        return new CandidateDetails(
                candidate.getId(),
                candidate.getName(),
                candidate.getEmail(),
                candidate.getPhone(),
                candidate.getLocation(),
                candidate.getExperienceInYears(),
                candidate.getSkills(),
                candidate.getWorkExperiences()
                        .stream()
                        .map(EntityDtoMapper::toWorkExperienceDto)
                        .toList()
        );
    }

    private static CandidateDetails.WorkExperience toWorkExperienceDto(WorkExperience workExperience){
        return new CandidateDetails.WorkExperience(
                workExperience.getCompanyName(),
                workExperience.getJobTitle(),
                workExperience.getStartDate(),
                workExperience.getEndDate(),
                workExperience.getDescription(),
                workExperience.getTechnologies()
        );
    }
}