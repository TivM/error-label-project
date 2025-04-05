package org.errorlabel.auth.controller;


import lombok.RequiredArgsConstructor;
import org.errorlabel.auth.exception.AuthenticationException;
import org.errorlabel.auth.exception.UserDuplicateException;
import org.errorlabel.auth.service.AuthService;
import org.errorlabel.persistence.model.auth.JwtTokenDTO;
import org.errorlabel.persistence.model.auth.UserLogInDTO;
import org.errorlabel.persistence.model.auth.UserRegistrationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> login(@RequestBody UserLogInDTO request) throws AuthenticationException {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtTokenDTO> register(@RequestBody UserRegistrationDTO request) throws UserDuplicateException {
        return ResponseEntity.ok(authService.register(request));
    }
}
