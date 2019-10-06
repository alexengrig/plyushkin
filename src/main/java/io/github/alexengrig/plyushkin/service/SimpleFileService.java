/*
 * Copyright 2019 Alexengrig Dev.
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

import io.github.alexengrig.plyushkin.configuration.StorageConfiguration;
import io.github.alexengrig.plyushkin.domain.File;
import io.github.alexengrig.plyushkin.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleFileService implements FileService {
    private final StorageConfiguration configuration;
    private final FileRepository repository;

    @Override
    public Optional<File> getById(Long fileId) {
        return repository.findById(fileId);
    }

    @Override
    public File save(MultipartFile file) throws IOException {
        String rootPath = configuration.getPath();
        String originalFilename = file.getOriginalFilename();
        String filename = originalFilename + UUID.randomUUID();
        Path path = Paths.get(rootPath, filename);
        file.transferTo(path);
        return repository.save(File.builder()
                .name(originalFilename)
                .path(path.toString())
                .build());
    }
}
