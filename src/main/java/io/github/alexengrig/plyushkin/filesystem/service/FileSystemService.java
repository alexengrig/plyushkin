package io.github.alexengrig.plyushkin.filesystem.service;

import io.github.alexengrig.plyushkin.filesystem.domain.FileRequest;
import io.github.alexengrig.plyushkin.filesystem.domain.FileResponse;

import java.io.IOException;

public interface FileSystemService<T> {
    FileResponse<T> save(FileRequest request) throws IOException;
}
