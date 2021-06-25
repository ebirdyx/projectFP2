package bookshare.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileStorageService implements StorageService {

    private final Path root = Paths.get("uploads");

    public FileStorageService() {
        try {
            // We create the folder for uploads
            Files.createDirectory(root);
        } catch (FileAlreadyExistsException ignored) {
            // skip if the folder already exist
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            // Save the file to disk
            Files.copy(
                    file.getInputStream(),
                    this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (FileAlreadyExistsException e) {
            // skip if the file already exist
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            // Get absolute path of the filename
            Path file = root.resolve(filename);
            // Create a resource from the file
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                // return the resource if exist and readable
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                file.toFile().delete();
            } else {
                throw new RuntimeException("Could not find the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error deleting file: " + e.getMessage());
        }
    }
}
