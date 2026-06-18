package com.example.myapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.model.GenerateImageRequest;
import com.example.myapp.model.GeneratedImage;
import com.example.myapp.service.ImageGenService;

@RestController
@RequestMapping("/api/images")
public class ImageGenController {

    private final ImageGenService imageGenservice;

    public ImageGenController(ImageGenService imageGenservice) {
        this.imageGenservice = imageGenservice;
    }

    @PostMapping(
            value = "/generate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public GeneratedImage generate(@RequestBody GenerateImageRequest req) {
        return imageGenservice.generate(req); 
    }
}
