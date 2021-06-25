package bookshare.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void save(MultipartFile file);

    Resource load(String filename);

    void deleteFile(String filename);
}
