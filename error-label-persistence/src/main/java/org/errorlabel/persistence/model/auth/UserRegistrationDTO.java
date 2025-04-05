package org.errorlabel.persistence.model.auth;

public record UserRegistrationDTO(String surname, String name, String email, String password) {
}
