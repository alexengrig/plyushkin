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

package io.github.alexengrig.plyushkin.storage.controller;

import io.github.alexengrig.plyushkin.storage.domain.Pack;
import io.github.alexengrig.plyushkin.storage.domain.SimpleThing;
import io.github.alexengrig.plyushkin.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

import static io.github.alexengrig.plyushkin.storage.constant.UrlConstants.SLASH;
import static io.github.alexengrig.plyushkin.storage.constant.UrlConstants.Storage.*;

@RestController
@RequestMapping(API_V1_STORAGE)
@RequiredArgsConstructor
public class StorageController {
    private final StorageService<MultipartFile> fileStorageService;

    @PostMapping(value = FILE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestParam MultipartFile file) {
        Pack<MultipartFile> filePack = fileStorageService.store(SimpleThing.of(file));
        URI fileUrl = URI.create(API_V1_STORAGE_FILE + SLASH + filePack.getSerialCode());
        return ResponseEntity.created(fileUrl).build();
    }

    @GetMapping(value = FILE_BY_ID, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> get(@PathVariable(FILE_ID) String serialCode) {
        Pack<MultipartFile> filePack = fileStorageService.get(serialCode);
        MultipartFile file = filePack.getContent();
        return ResponseEntity.ok(file);
    }
}
