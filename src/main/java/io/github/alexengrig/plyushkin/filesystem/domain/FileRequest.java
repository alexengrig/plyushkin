package io.github.alexengrig.plyushkin.filesystem.domain;

import java.io.IOException;
import java.io.InputStream;

public interface FileRequest {
    InputStream getInputStream() throws IOException;
}
