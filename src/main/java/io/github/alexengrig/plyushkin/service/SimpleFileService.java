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
import io.github.alexengrig.plyushkin.converter.Mapper;
import io.github.alexengrig.plyushkin.domain.File;
import io.github.alexengrig.plyushkin.domain.FileEntity;
import io.github.alexengrig.plyushkin.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleFileService implements FileService {
    private final StorageConfiguration configuration;
    private final FileRepository repository;
    private final Mapper<File, FileEntity> mapper;

    @Override
    public Optional<File> getById(Long fileId) {
        return repository.findById(fileId).map(mapper::unmap);
    }

    @Override
    public File save(MultipartFile file) throws IOException {
        String rootPath = configuration.getPath();
        String originalFilename = file.getOriginalFilename();
        String filename = originalFilename + UUID.randomUUID();
        Path path = Paths.get(rootPath, filename);
        file.transferTo(path);
        File target = File.builder()
                .name(originalFilename)
                .path(path.toString())
                .build();
        return Optional.of(target)
                .map(mapper::map)
                .map(repository::save)
                .map(mapper::unmap)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Optional<byte[]> getRawById(Long fileId) throws IOException {
        Optional<File> fileOptional = repository.findById(fileId).map(mapper::unmap);
        if (fileOptional.isPresent()) {
            File file = fileOptional.get();
            Path path = Paths.get(file.getPath());
            InputStream inputStream = Files.newInputStream(path);
            byte[] target = new byte[inputStream.available()];
            inputStream.read(target);
            return Optional.of(target);
        }
        return Optional.empty();
    }
}
