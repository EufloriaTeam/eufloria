package uz.pdp.eufloria.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.entity.Image;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.repository.ImageRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final Storage storage;

    public Image uploadImage(@RequestPart(name = "file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        BlobId blobId = BlobId.of(AppConstants.BUCKET_NAME, Objects.requireNonNull(originalFilename));
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getBytes());

        Image image = new Image(
                UUID.randomUUID(),
                originalFilename.substring(0, originalFilename.indexOf(".")),
                originalFilename,
                file.getContentType(),
                file.getSize());

        image = imageRepository.save(image);
        return image;
    }

    public byte[] retrieve(UUID imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow();
        BlobId blobId = BlobId.of(AppConstants.BUCKET_NAME, image.getOriginalName());
        Blob blob = storage.get(blobId);
        return blob.getContent();
    }

    public List<Image> getAll() {
        return imageRepository.findAll();
    }
}
