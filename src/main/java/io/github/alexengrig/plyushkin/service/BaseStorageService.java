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

import io.github.alexengrig.plyushkin.domain.Pack;
import io.github.alexengrig.plyushkin.domain.Thing;

/**
 * <p>The base storage service.</p>
 *
 * @param <T> the thing type
 * @param <R> the pack type
 * @author Grig Alex
 * @version 0.1.0
 * @see io.github.alexengrig.plyushkin.service.StorageService
 * @see io.github.alexengrig.plyushkin.domain.Thing
 * @see io.github.alexengrig.plyushkin.domain.Pack
 * @since 0.1.0
 */
public abstract class BaseStorageService<T, R> implements StorageService<Thing<T>, Pack<R>> {
}
