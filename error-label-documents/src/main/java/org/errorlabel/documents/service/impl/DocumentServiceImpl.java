package org.errorlabel.documents.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.errorlabel.documents.exception.ResourceNotFoundException;
import org.errorlabel.documents.service.DocumentService;
import org.errorlabel.persistence.entity.DocumentMetadata;
import org.errorlabel.persistence.entity.Project;
import org.errorlabel.persistence.model.document.SlideDTO;
import org.errorlabel.persistence.repository.documents.DocumentRepository;
import org.errorlabel.persistence.repository.projects.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private static final String UPLOAD_DIR = "uploads/";

    private final DocumentRepository documentRepository;
    private final ProjectRepository projectRepository;

    @Override
    @SneakyThrows
    public DocumentMetadata upload(MultipartFile file, Long projectId){
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + filename);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new ResourceNotFoundException("Project with id = " + projectId + "not found")
        );

        DocumentMetadata document = DocumentMetadata.builder()
                .documentName(file.getOriginalFilename())
                .documentPath(path.toString())
                .build();

        project.addDocument(document);

        return documentRepository.save(document);
    }

    public byte[] getFileContent(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    @Override
    @SneakyThrows
    public List<SlideDTO> viewPresentation(Long id){
        DocumentMetadata document = documentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Document with id = " + id + "not found")
        );
        FileInputStream inputStream = new FileInputStream(document.getDocumentPath());
        XMLSlideShow ppt = new XMLSlideShow(inputStream);

        List<SlideDTO> slides = ppt.getSlides().stream().map(slide -> {
            StringBuilder text = new StringBuilder();
            for (XSLFShape shape : slide.getShapes()) {
                if (shape instanceof XSLFTextShape) {
                    text.append(((XSLFTextShape) shape).getText()).append("\n");
                }
            }
            return new SlideDTO(slide.getSlideNumber(), slide.getTitle(), text.toString());
        }).collect(Collectors.toList());

        ppt.close();
        inputStream.close();

        return slides;
    }
}
