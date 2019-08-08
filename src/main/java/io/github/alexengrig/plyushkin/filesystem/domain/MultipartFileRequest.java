package io.github.alexengrig.plyushkin.filesystem.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor(staticName = "of")
public final class MultipartFileRequest implements FileRequest {
    private final MultipartFile file;

    @Override
    public InputStream getInputStream() throws IOException {
        return file.getInputStream();
    }
}
