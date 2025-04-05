package org.errorlabel.auth.service;

import lombok.RequiredArgsConstructor;
import org.errorlabel.auth.exception.AuthenticationException;
import org.errorlabel.auth.exception.UserDuplicateException;
import org.errorlabel.persistence.repository.auth.RolesRepository;
import org.errorlabel.persistence.repository.auth.UserRepository;
import org.errorlabel.persistence.entity.User;
import org.errorlabel.persistence.entity.roles.UserRole;
import org.errorlabel.persistence.model.auth.JwtTokenDTO;
import org.errorlabel.persistence.model.auth.UserLogInDTO;
import org.errorlabel.persistence.model.auth.UserRegistrationDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtTokenDTO login(UserLogInDTO request) throws AuthenticationException {
        User user = userRepository.findUserByEmail(request.email()).orElseThrow();
        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new AuthenticationException("Invalid email or password");
        List<String> roles = userRepository.findUserRolesNames(user.getId());

        user.setRoles(roles.stream().map(name -> {
            UserRole role = new UserRole();
            role.setName(name);
            return role;
        }).collect(Collectors.toSet()));

        String jwt = jwtService.generateToken(user);
        return new JwtTokenDTO(jwt, user.getId());
    }

    public JwtTokenDTO register(UserRegistrationDTO request) throws UserDuplicateException {
        if (userRepository.findUserByEmail(request.email()).isPresent())
            throw new UserDuplicateException("User with such email is already presented");

        Optional<UserRole> userRole = rolesRepository.findUserRoleByName("user");
        if (userRole.isEmpty())
            throw new RuntimeException();

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole.get());
        User user = User.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(roles)
                .build();
        userRepository.saveAndFlush(user);

        String jwt = jwtService.generateToken(user);
        return new JwtTokenDTO(jwt, user.getId());
    }
}
