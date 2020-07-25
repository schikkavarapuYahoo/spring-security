package com.siddu.springsecurity.configuration.security;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPermissions.COURSE_READ, ApplicationUserPermissions.COURSE_WRITE, ApplicationUserPermissions.STUDENT_READ, ApplicationUserPermissions.STUDENT_WRITE));

    @Getter
    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRole(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }
}
