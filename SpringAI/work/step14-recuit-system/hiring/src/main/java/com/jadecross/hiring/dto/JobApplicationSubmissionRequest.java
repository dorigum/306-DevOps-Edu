package com.jadecross.hiring.dto;

public record JobApplicationSubmissionRequest(Integer jobId,
                                              Integer candidateId,
                                              String resume) {
}
