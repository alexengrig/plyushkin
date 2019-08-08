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

import org.springframework.stereotype.Service;

import java.net.URI;

import static io.github.alexengrig.plyushkin.constant.UrlConstants.SLASH;
import static io.github.alexengrig.plyushkin.constant.UrlConstants.Storage.API_V1_STORAGE_FILE;

@Service
public class SimpleStorageUrlService implements StorageUrlService {
    @Override
    public URI getFileByIdUrl(Long contentId) {
        return URI.create(API_V1_STORAGE_FILE + SLASH + contentId);
    }
}
