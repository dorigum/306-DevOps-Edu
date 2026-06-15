package com.example.myapp.record;

import java.util.List;

public record ChatRequest(String model, List<Message> messages) {}