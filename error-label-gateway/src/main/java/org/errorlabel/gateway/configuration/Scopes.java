package org.errorlabel.gateway.configuration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Scopes {
    ADMIN("SCOPE_admin"),
    USER("SCOPE_user");

    final String scopeName;
}
