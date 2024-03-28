package uz.pdp.eufloria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.entity.Image;
import uz.pdp.eufloria.service.ImageService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + ImageController.BASE_URL)
@RequiredArgsConstructor
public class ImageController {
    public static final String BASE_URL = "/image";
    private final ImageService imageService;

    @PostMapping
    public ApiResponse<Image> upload(@RequestPart MultipartFile file) throws IOException {
        return ApiResponse.body(imageService.uploadImage(file));
    }

    @GetMapping("/{imageId}")
    public byte[] retrieve(@PathVariable UUID imageId) {
        return imageService.retrieve(imageId);
    }
}
