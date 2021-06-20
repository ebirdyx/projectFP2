package io.kyorg.springsecuritydemo.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteFile(String filename);
}
