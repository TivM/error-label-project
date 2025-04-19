package org.errorlabel.persistence.repository.projects;

import org.errorlabel.persistence.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
