package com.example.myapp.model;

public record GenerateImageRequest(
        String prompt,
        Integer width,
        Integer height,
        String quality,
        Integer n
) {}
