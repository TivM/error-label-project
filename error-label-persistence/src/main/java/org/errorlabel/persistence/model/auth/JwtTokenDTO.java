package org.errorlabel.persistence.model.auth;

public record JwtTokenDTO(String jwt, Long userId) {
}
