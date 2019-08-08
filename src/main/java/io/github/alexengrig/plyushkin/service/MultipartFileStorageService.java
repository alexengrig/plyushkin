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

package io.github.alexengrig.plyushkin.service;

import io.github.alexengrig.plyushkin.domain.StorageRequest;
import io.github.alexengrig.plyushkin.domain.StorageResponse;
import io.github.alexengrig.plyushkin.domain.StoreFile;
import io.github.alexengrig.plyushkin.repository.StoreFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class MultipartFileStorageService implements StorageService<MultipartFile> {
    private final FileSystemService fileSystemService;
    private final StoreFileRepository storeFileRepository;

    @Override
    public StorageResponse<MultipartFile> store(StorageRequest<MultipartFile> request) {
        MultipartFile file = request.getContent();
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        Path path = fileSystemService.save(file);
        StoreFile storeFile = new StoreFile(path);
        StoreFile savedFile = storeFileRepository.save(storeFile);
        return null;
    }

    @Override
    public StorageResponse<MultipartFile> get(StorageRequest<MultipartFile> request) {
        return null;
    }
}
