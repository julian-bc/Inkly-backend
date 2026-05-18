package top.inkly.user_service.domain.ports.output.cloudinary;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryConnectorPort {
    String uploadFile(MultipartFile file);
    void deleteFile(String fileUrl);
    String extractPublicIdFromUrl(String url);
}
