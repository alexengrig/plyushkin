package io.github.alexengrig.plyushkin.filesystem.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor(staticName = "of")
public final class MultipartFileResponse implements FileResponse<MultipartFile> {
    private final MultipartFile file;

    @Override
    public MultipartFile getSource() {
        return file;
    }
}
