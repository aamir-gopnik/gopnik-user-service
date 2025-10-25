package com.gopnik.userservice.appuser;

public enum AppUserRole {

    USER,
    ADMIN;

    public static AppUserRole fromString(String role) {
        try {
            // Convert to upper case to make the matching case-insensitive
            return AppUserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            // You can return a default value or rethrow an exception
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

}
