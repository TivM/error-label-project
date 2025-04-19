package org.errorlabel.projects.service.impl;

import lombok.RequiredArgsConstructor;
import org.errorlabel.projects.exception.ResourceNotFoundException;
import org.errorlabel.projects.service.ProjectService;
import org.errorlabel.persistence.entity.Project;
import org.errorlabel.persistence.entity.User;
import org.errorlabel.persistence.repository.auth.UserRepository;
import org.errorlabel.persistence.repository.projects.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    @Override
    public Project add(String projectName, Long userId) {
        Project project = Project.builder()
                .projectName(projectName)
                .createdAt(LocalDateTime.now())
                .build();

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with id = " + userId + "not found")
        );

        user.addProject(project);

        return projectRepository.save(project);
    }

    @Override
    public Project getById(Long projectId){
        return projectRepository.findById(projectId).orElseThrow(
                () -> new ResourceNotFoundException("User with id = " + projectId + "not found")
        );
    }
}
