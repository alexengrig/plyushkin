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

package io.github.alexengrig.plyushkin.constant;

public interface UrlConstants {
    String SLASH = "/";
    String OPEN = "{";
    String CLOSE = "}";
    String API = "api";
    String V1 = "v1";
    String API_V1 = API + SLASH + V1;

    interface Storage {
        String STORAGE = "storage";
        String FILE = "file";
        String FILE_ID = "fileId";
        String FILE_BY_ID = FILE + SLASH + OPEN + FILE_ID + CLOSE;
        String API_V1_STORAGE = API_V1 + SLASH + STORAGE;
        String API_V1_STORAGE_FILE = API_V1_STORAGE + SLASH + FILE;
    }
}
