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

package io.github.alexengrig.plyushkin.service;

import io.github.alexengrig.plyushkin.domain.Thing;
import io.github.alexengrig.plyushkin.repository.ThingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleStorageService implements StorageService {
    private final ThingRepository thingRepository;

    @Override
    public Mono<Thing> store(Thing thing) {
        return thingRepository.save(thing);
    }

    @Override
    public Mono<Thing> get(String thingId) {
        return thingRepository.findById(thingId);
    }

    @Override
    public Mono<Thing> update(String thingId, Thing thing) {
        return thingRepository.findById(thingId)
                .map(storedThing -> {
                    BeanUtils.copyProperties(thing, storedThing);
                    thingRepository.save(storedThing);
                    return storedThing;
                });
    }

    @Override
    public Mono<Void> remove(String thingId) {
        return thingRepository.deleteById(thingId);
    }
}
