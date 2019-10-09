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

package io.github.alexengrig.plyushkin.controller;

import io.github.alexengrig.plyushkin.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileController {
    private final FileService service;

    @GetMapping("/{fileId}")
    public ResponseEntity<?> getById(@PathVariable Long fileId) {
        return service.getById(fileId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping
    public ResponseEntity<?> save(MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.save(file));
    }

    @GetMapping(value = "/{fileId}/raw")
    public @ResponseBody
    ResponseEntity<byte[]> getRaw(@PathVariable Long fileId) throws IOException {
        return service.getRawById(fileId)
                .map(ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)::body)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
