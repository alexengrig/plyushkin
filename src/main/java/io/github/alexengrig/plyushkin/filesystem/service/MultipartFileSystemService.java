package io.github.alexengrig.plyushkin.filesystem.service;

import io.github.alexengrig.plyushkin.filesystem.domain.FileRequest;
import io.github.alexengrig.plyushkin.filesystem.domain.FileResponse;
import io.github.alexengrig.plyushkin.filesystem.domain.MultipartFileResponse;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class MultipartFileSystemService implements FileSystemService<MultipartFile> {
    @Override
    public FileResponse<MultipartFile> save(FileRequest request) throws IOException {
        try (InputStream inputStream = request.getInputStream()) {
            Path target = Paths.get("/tmp/plyushkin/multipart/" + UUID.randomUUID().toString());
            Files.copy(inputStream, target);
            return MultipartFileResponse.of(new MultipartFile() {
                @Override
                public String getName() {
                    return getOriginalFilename();
                }

                @Override
                public String getOriginalFilename() {
                    return target.getFileName().toString();
                }

                @Override
                public String getContentType() {
                    return MediaType.MULTIPART_FORM_DATA_VALUE;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @SneakyThrows
                @Override
                public long getSize() {
                    return Files.size(target);
                }

                @Override
                public byte[] getBytes() throws IOException {
                    return Files.readAllBytes(target);
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return Files.newInputStream(target);
                }

                @Override
                public void transferTo(File dest) throws IOException, IllegalStateException {
                    Files.copy(target, dest.toPath());
                }
            });
        }
    }
}
