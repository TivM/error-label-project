package org.errorlabel.documents.service;

import org.errorlabel.persistence.entity.DocumentMetadata;
import org.errorlabel.persistence.model.document.SlideDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    DocumentMetadata upload(MultipartFile file, Long projectId);

    List<SlideDTO> viewPresentation(Long id) throws IOException;
}
