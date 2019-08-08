/*
 * Copyright 2019 Plyushkin contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.alexengrig.plyushkin.domain;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileStorageRequest implements StorageRequest<MultipartFile> {
    @Getter
    private MultipartFile file;
    @Getter
    private Long fileId;

    private MultipartFileStorageRequest(MultipartFile file) {
        this.file = file;
    }

    private MultipartFileStorageRequest(Long fileId) {
        this.fileId = fileId;
    }

    public static MultipartFileStorageRequest of(MultipartFile file) {
        return new MultipartFileStorageRequest(file);
    }

    public static MultipartFileStorageRequest of(Long fileId) {
        return new MultipartFileStorageRequest(fileId);
    }

    @Override
    public MultipartFile getContent() {
        return file;
    }
}
