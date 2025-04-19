package org.errorlabel.projects.service;

import org.errorlabel.persistence.entity.Project;

public interface ProjectService {

    Project add(String projectName, Long userId);

    Project getById(Long id);
}
