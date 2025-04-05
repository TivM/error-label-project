package org.errorlabel.persistence.model.core;

import java.time.LocalDateTime;

public record GetProjectResponseDTO(Long projectId, String projectName, LocalDateTime createAt) {
}
