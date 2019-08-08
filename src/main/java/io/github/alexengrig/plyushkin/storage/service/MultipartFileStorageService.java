package io.github.alexengrig.plyushkin.storage.service;

import io.github.alexengrig.plyushkin.database.domain.FileDocument;
import io.github.alexengrig.plyushkin.database.repository.FileDocumentRepository;
import io.github.alexengrig.plyushkin.filesystem.domain.FileRequest;
import io.github.alexengrig.plyushkin.filesystem.domain.FileResponse;
import io.github.alexengrig.plyushkin.filesystem.domain.MultipartFileRequest;
import io.github.alexengrig.plyushkin.filesystem.service.FileSystemService;
import io.github.alexengrig.plyushkin.storage.domain.Pack;
import io.github.alexengrig.plyushkin.storage.domain.SimplePack;
import io.github.alexengrig.plyushkin.storage.domain.Thing;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * The implementation of {@link io.github.alexengrig.plyushkin.storage.service.StorageService}
 * for {@link org.springframework.web.multipart.MultipartFile}.
 * </p>
 *
 * @author Grig Alex
 * @version 0.1.0
 * @see io.github.alexengrig.plyushkin.storage.service.StorageService
 * @see org.springframework.web.multipart.MultipartFile
 * @since 0.1.0
 */
@Service
@RequiredArgsConstructor
public class MultipartFileStorageService implements StorageService<MultipartFile> {
    private final FileSystemService<MultipartFile> fileSystemService;
    private final FileDocumentRepository fileDocumentRepository;

    /**
     * <p>
     * Store the thing with {@link org.springframework.web.multipart.MultipartFile} content type and
     * return the package of the thing.
     * </p>
     *
     * @param thing the item for storage
     * @return the {@link io.github.alexengrig.plyushkin.storage.domain.Pack} of the stored thing
     * @since 0.1.0
     */
    @SneakyThrows
    @Override
    public Pack<MultipartFile> store(Thing<MultipartFile> thing) {
        MultipartFile content = thing.getContent();
        FileRequest fileRequest = MultipartFileRequest.of(content);
        FileResponse<MultipartFile> fileResponse = fileSystemService.save(fileRequest);
        MultipartFile source = fileResponse.getSource();
        FileDocument fileDocument = FileDocument.builder().name(content.getOriginalFilename()).build();
        FileDocument savedFileDocument = fileDocumentRepository.save(fileDocument);
        String id = savedFileDocument.getId();
        return SimplePack.of(source, id);
    }

    @Override
    public Pack<MultipartFile> get(String serialCode) {
        return null;
    }
}
