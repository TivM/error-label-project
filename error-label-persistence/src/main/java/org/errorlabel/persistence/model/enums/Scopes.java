package org.errorlabel.persistence.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Scopes {
    ADMIN("SCOPE_admin"),
    USER("SCOPE_user");

    public final String scopeName;
}
