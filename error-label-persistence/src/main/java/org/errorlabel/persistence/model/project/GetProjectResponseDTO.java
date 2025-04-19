package org.errorlabel.persistence.model.project;

import java.time.LocalDateTime;

public record GetProjectResponseDTO(Long projectId, String projectName, LocalDateTime createAt) {
}
