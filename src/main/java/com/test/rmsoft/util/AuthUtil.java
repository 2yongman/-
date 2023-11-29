package com.test.rmsoft.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    public static String getCurrentMemberEmail() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
    }
}
