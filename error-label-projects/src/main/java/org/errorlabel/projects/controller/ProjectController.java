package org.errorlabel.projects.controller;

import lombok.RequiredArgsConstructor;
import org.errorlabel.projects.service.ProjectService;
import org.errorlabel.persistence.entity.Project;
import org.errorlabel.persistence.model.project.AddProjectRequestDTO;
import org.errorlabel.persistence.model.project.AddProjectResponseDTO;
import org.errorlabel.persistence.model.project.GetProjectResponseDTO;
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
                new AddProjectResponseDTO(projectService.add(request.name(), request.userId()).getId())
        );
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<GetProjectResponseDTO> getProjectById(@PathVariable Long id) {
        Project project = projectService.getById(id);
        return ResponseEntity.ok(
                new GetProjectResponseDTO(project.getId(), project.getProjectName(), project.getCreatedAt())
        );
    }
}
