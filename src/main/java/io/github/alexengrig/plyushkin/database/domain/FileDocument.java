package io.github.alexengrig.plyushkin.database.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class FileDocument {
    @Id
    private String id;
    private String name;
}
