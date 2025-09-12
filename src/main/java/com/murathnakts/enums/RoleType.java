package com.murathnakts.enums;

import lombok.Getter;

@Getter
public enum RoleType {
    PARENT("1","PARENT"),
    TEACHER("2","TEACHER"),
    ADMIN("3","ADMIN");

    private final String id;
    private final String role;

    RoleType(String id, String role) {
        this.id = id;
        this.role = role;
    }
}
