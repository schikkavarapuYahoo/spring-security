package com.siddu.springsecurity.configuration.security;

import org.junit.jupiter.api.Test;

class ApplicationUserRoleTest {

    @Test
    public void test1() {
        for (ApplicationUserRole role : ApplicationUserRole.values() ){
            System.out.println(" =======> "+ role +" <======= ");
        }
    }

    @Test
    public void test2(){


        System.out.println(ApplicationUserPermissions.COURSE_READ.getPermission());
    }

}