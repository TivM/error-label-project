package org.errorlabel.persistence.repository.documents;

import org.errorlabel.persistence.entity.DocumentMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentMetadata, Long> {
}
