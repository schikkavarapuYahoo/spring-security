package com.siddu.springsecurity.configuration.security;

import lombok.Getter;

public enum ApplicationUserPermissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    @Getter
    private final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

}
