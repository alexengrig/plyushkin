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

package io.github.alexengrig.plyushkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>The application entry point class.</p>
 *
 * @author Grig Alex
 * @version 0.1.0
 * @since 0.1.0
 */
@SpringBootApplication
public class PlyushkinApplication {
    /**
     * <p>The application entry point method.</p>
     *
     * @param args application arguments
     * @version 0.1.0
     * @since 0.1.0
     */
    public static void main(String[] args) {
        SpringApplication.run(PlyushkinApplication.class, args);
    }
}