package org.errorlabel.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.errorlabel.core.exception.ResourceNotFoundException;
import org.errorlabel.core.service.ProjectService;
import org.errorlabel.persistence.entity.Project;
import org.errorlabel.persistence.entity.User;
import org.errorlabel.persistence.repository.auth.UserRepository;
import org.errorlabel.persistence.repository.core.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    @Override
    public Long add(String projectName, Long userId) {
        Project project = Project.builder()
                .projectName(projectName)
                .createdAt(LocalDateTime.now())
                .build();

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with id = " + userId + "not found")
        );

        project.setUser(user);

        return projectRepository.save(project).getId();
    }

    @Override
    public Project getById(Long projectId){
        return projectRepository.findById(projectId).orElseThrow(
                () -> new ResourceNotFoundException("User with id = " + projectId + "not found")
        );
    }
}
