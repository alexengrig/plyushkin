package io.github.alexengrig.plyushkin.storage.service;

import io.github.alexengrig.plyushkin.storage.domain.Pack;
import io.github.alexengrig.plyushkin.storage.domain.Thing;

/**
 * <p>The interface describes a storage service.</p>
 *
 * @param <T> the content type
 * @author Grig Alex
 * @version 0.1.0
 * @see io.github.alexengrig.plyushkin.storage.domain.Thing
 * @see io.github.alexengrig.plyushkin.storage.domain.Pack
 * @since 0.1.0
 */
public interface StorageService<T> {
    /**
     * <p>Stores the thing and returns the package of the thing.</p>
     *
     * @param thing the item for storage
     * @return the {@link io.github.alexengrig.plyushkin.storage.domain.Pack} of the stored thing
     * @since 0.1.0
     */
    Pack<T> store(Thing<T> thing);

    /**
     * <p>Returns the package of the stored thing by the package serial code.</p>
     *
     * @param serialCode the pack serial code
     * @return the {@link io.github.alexengrig.plyushkin.storage.domain.Pack} of the stored thing
     * @since 0.1.0
     */
    Pack<T> get(String serialCode);
}
