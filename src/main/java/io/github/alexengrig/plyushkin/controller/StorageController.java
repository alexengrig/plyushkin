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

import io.github.alexengrig.plyushkin.domain.Thing;
import io.github.alexengrig.plyushkin.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;

    @PostMapping
    public Mono<ResponseEntity<Thing>> save(@RequestBody Thing thing) {
        return storageService.store(thing)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{thingId}")
    public Mono<ResponseEntity<Thing>> get(@PathVariable String thingId) {
        return storageService.get(thingId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{thingId}")
    public Mono<ResponseEntity<Thing>> update(@PathVariable String thingId, @RequestBody Thing thing) {
        return storageService.update(thingId, thing)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{thingId}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String thingId) {
        return storageService.remove(thingId)
                .map(ignored -> ResponseEntity.status(HttpStatus.OK).<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
