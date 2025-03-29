package org.errorlabel.auth.exception;

import lombok.RequiredArgsConstructor;

public class AuthenticationException extends Exception {

    public AuthenticationException(String message){
        super(message);
    }
}
