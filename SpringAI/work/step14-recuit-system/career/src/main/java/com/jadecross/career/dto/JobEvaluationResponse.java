package com.jadecross.career.dto;

import java.util.List;

public record JobEvaluationResponse(
        List<JobEvaluationResult> results
) {
}