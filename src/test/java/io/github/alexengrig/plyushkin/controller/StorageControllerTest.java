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
import io.github.alexengrig.plyushkin.repository.ThingRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class StorageControllerTest {
    @Autowired
    ThingRepository thingRepository;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void checkSaveThing() {
        Thing thing = Thing.builder().name("Some thing").build();
        webTestClient.post()
                .uri("/storage")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(thing), Thing.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Some thing");
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void checkGetThing() {
        Thing thing = thingRepository.save(Thing.builder().name("Some thing").build()).block();
        webTestClient.get()
                .uri("/storage/{thingId}", Collections.singletonMap("thingId", thing.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void checkUpdateThing() {
        Thing thing = thingRepository.save(Thing.builder().name("Some thing").build()).block();
        Thing newThing = Thing.builder().name("New thing").build();
        webTestClient.put()
                .uri("/storage/{thingId}", Collections.singletonMap("thingId", thing.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(newThing), Thing.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isEqualTo("New thing");
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void checkDeleteThing() {
        Thing thing = thingRepository.save(Thing.builder().name("Some thing").build()).block();
        webTestClient.delete()
                .uri("/storage/{thingId}", Collections.singletonMap("thingId", thing.getId()))
                .exchange()
                .expectStatus().isOk();
    }
}