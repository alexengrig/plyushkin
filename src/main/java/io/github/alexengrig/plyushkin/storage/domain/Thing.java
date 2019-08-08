package io.github.alexengrig.plyushkin.storage.domain;

/**
 * <p>The interface describes a thing.</p>
 *
 * @param <T> the content type
 * @author Grig Alex
 * @version 0.1.0
 * @since 0.1.0
 */
public interface Thing<T> {
    /**
     * <p>Returns the thing content.</p>
     *
     * @return the content of {@link T}
     * @since 0.1.0
     */
    T getContent();
}
