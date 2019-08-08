package io.github.alexengrig.plyushkin.storage.domain;

import lombok.RequiredArgsConstructor;

/**
 * <p>The simple implementation of {@link io.github.alexengrig.plyushkin.storage.domain.Pack}.</p>
 *
 * @param <T> the content type
 * @author Grig Alex
 * @version 0.1.0
 * @see io.github.alexengrig.plyushkin.storage.domain.Pack
 * @since 0.1.0
 */
@RequiredArgsConstructor(staticName = "of")
public final class SimplePack<T> implements Pack<T> {
    /**
     * <p>The content value.</p>
     *
     * @since 0.1.0
     */
    private final T content;

    /**
     * <p>Returns the pack content.</p>
     *
     * @return the content of {@link T}
     * @since 0.1.0
     */
    @Override
    public T getContent() {
        return content;
    }
}
