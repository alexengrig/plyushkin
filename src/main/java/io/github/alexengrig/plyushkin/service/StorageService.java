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

import java.util.function.Function;

/**
 * <p>Represents a service-function that accepts a thing and produces a result.</p>
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #store(Object)}.
 *
 * @param <T> the type of the input to the storage service
 * @param <R> the type of the result of the storage service
 * @author Grig Alex
 * @version 0.1.0
 * @see java.util.function.Function
 * @since 0.1.0
 */
@FunctionalInterface
public interface StorageService<T, R> extends Function<T, R> {
    /**
     * <p>The store method.</p>
     *
     * @param thing a thing
     * @return a result
     * @version 0.1.0
     * @since 0.1.0
     */
    R store(T thing);

    /**
     * <p>The store method.</p>
     *
     * @param thing a thing
     * @return a result
     * @version 0.1.0
     * @see java.util.function.Function#apply(Object)
     * @since 0.1.0
     */
    @Override
    default R apply(T thing) {
        return store(thing);
    }
}
