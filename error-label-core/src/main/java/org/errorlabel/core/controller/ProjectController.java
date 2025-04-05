package org.errorlabel.core.controller;

import lombok.RequiredArgsConstructor;
import org.errorlabel.core.service.ProjectService;
import org.errorlabel.persistence.entity.Project;
import org.errorlabel.persistence.model.core.AddProjectRequestDTO;
import org.errorlabel.persistence.model.core.AddProjectResponseDTO;
import org.errorlabel.persistence.model.core.GetProjectResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects/create")
    public ResponseEntity<AddProjectResponseDTO> createProject(@RequestBody AddProjectRequestDTO request) {
        return ResponseEntity.ok(
                new AddProjectResponseDTO(projectService.add(request.name(), request.userId()))
        );
    }

    @PostMapping("/projects/{id}")
    public ResponseEntity<GetProjectResponseDTO> getProjectById(@PathVariable Long id) {
        Project project = projectService.getById(id);
        return ResponseEntity.ok(
                new GetProjectResponseDTO(project.getId(), project.getProjectName(), project.getCreatedAt())
        );
    }
}
