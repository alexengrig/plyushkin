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

package io.github.alexengrig.plyushkin.repository;

import io.github.alexengrig.plyushkin.domain.FileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SimpleFileRepository implements FileRepository {
    private final JpaFileRepository jpaFileRepository;

    @Override
    public FileEntity save(FileEntity file) {
        return jpaFileRepository.save(file);
    }

    @Override
    public Optional<FileEntity> findById(Long fileId) {
        return jpaFileRepository.findById(fileId);
    }
}
