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

package io.github.alexengrig.plyushkin.converter;

import io.github.alexengrig.plyushkin.domain.File;
import io.github.alexengrig.plyushkin.domain.FileEntity;
import org.springframework.stereotype.Component;

@Component
public class FileMapper implements Mapper<File, FileEntity> {
    @Override
    public FileEntity map(File file) {
        return FileEntity.builder()
                .id(file.getId())
                .name(file.getName())
                .path(file.getPath())
                .build();
    }

    @Override
    public File unmap(FileEntity file) {
        return File.builder()
                .id(file.getId())
                .name(file.getName())
                .path(file.getPath())
                .build();
    }
}
