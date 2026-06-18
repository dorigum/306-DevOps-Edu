package com.example.myapp.service;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import com.example.myapp.model.GenerateImageRequest;
import com.example.myapp.model.GeneratedImage;

@Service
public class ImageGenService {

    private final ImageModel imageModel;

    public ImageGenService(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public GeneratedImage generate(GenerateImageRequest req) {
        if (req.prompt() == null || req.prompt().isBlank()) {
            throw new IllegalArgumentException("prompt is required");
        }

        int width  = (req.width()  == null) ? 1024 : req.width();
        int height = (req.height() == null) ? 1024 : req.height();

        String quality = (req.quality() == null || req.quality().isBlank())
                ? "auto"
                : req.quality();

        OpenAiImageOptions options = OpenAiImageOptions.builder()
//                .model("gpt-image-1.5")
                .width(width)
                .height(height)
                .quality(quality)
                .N(1)
                .build();

        ImageResponse response =
                imageModel.call(new ImagePrompt(req.prompt(), options));

        var result = response.getResult();
        var output = result.getOutput();

        return new GeneratedImage(output.getB64Json());
    }
}
