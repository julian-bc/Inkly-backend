package top.inkly.user_service.infrastructure.output.cloudinary.adapter;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.inkly.user_service.domain.ports.output.cloudinary.CloudinaryConnectorPort;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CloudinaryAdapter implements CloudinaryConnectorPort {
    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "resource_type", "auto"
            ));
            return uploadResult.get("secure_url").toString();
        } catch (IOException ex) {
            throw new RuntimeException("Error al subir archivo a Cloudinary", ex);
        }
    }

    @Override
    public void deleteFile(String fileUrl) {
        try {
            String publicId = extractPublicIdFromUrl(fileUrl);
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException ex) {
            throw new RuntimeException("Error al eliminar archivo de Cloudinary", ex);
        }
    }

    @Override
    public String extractPublicIdFromUrl(String url) {
        String parts[] = url.split("/upload/");
        String pathAfterUpload = parts[1];

        if (pathAfterUpload.split("/")[0].matches("v\\d+")) {
            pathAfterUpload = pathAfterUpload.substring(pathAfterUpload.indexOf("/") + 1);
        }

        return pathAfterUpload.substring(0, pathAfterUpload.lastIndexOf("."));
    }
}
