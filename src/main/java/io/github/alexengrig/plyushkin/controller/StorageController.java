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

package io.github.alexengrig.plyushkin.controller;

import io.github.alexengrig.plyushkin.domain.MultipartFileStorageRequest;
import io.github.alexengrig.plyushkin.domain.StorageResponse;
import io.github.alexengrig.plyushkin.service.StorageService;
import io.github.alexengrig.plyushkin.service.StorageUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

import static io.github.alexengrig.plyushkin.constant.UrlConstants.Storage.*;

@RestController
@RequestMapping(API_V1_STORAGE)
@RequiredArgsConstructor
public class StorageController {
    private final StorageService<MultipartFile> fileStorageService;
    private final StorageUrlService storageUrlService;

    @PostMapping(value = FILE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestParam MultipartFile file) {
        MultipartFileStorageRequest request = MultipartFileStorageRequest.of(file);
        StorageResponse<MultipartFile> response = fileStorageService.store(request);
        Long fileId = response.getContentId();
        URI fileUrl = storageUrlService.getFileByIdUrl(fileId);
        return ResponseEntity.created(fileUrl).build();
    }

    @GetMapping(value = FILE_BY_ID, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> get(@PathVariable(FILE_ID) Long fileId) {
        MultipartFileStorageRequest request = MultipartFileStorageRequest.of(fileId);
        StorageResponse<MultipartFile> response = fileStorageService.get(request);
        MultipartFile file = response.getContent();
        return ResponseEntity.ok(file);
    }
}
