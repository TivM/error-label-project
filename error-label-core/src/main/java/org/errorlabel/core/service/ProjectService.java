package org.errorlabel.core.service;

import org.errorlabel.persistence.entity.Project;

import java.time.LocalDateTime;

public interface ProjectService {

    Long add(String projectName, Long userId);

    Project getById(Long id);
}
