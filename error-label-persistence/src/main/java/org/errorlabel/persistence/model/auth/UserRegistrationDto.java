package org.errorlabel.persistence.model.auth;

public record UserRegistrationDto(String surname, String name, String email, String password) {
}
