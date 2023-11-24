package com.libproject.demo.service;

import org.springframework.stereotype.Service;

import com.libproject.demo.domain.models.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPermissionService {
    private final UserService userService;
    public boolean isAuthenticated(long userId, String authUserLogin) {
        if (userService.getUserById(userId) == null) {
            return false;
        }
        return userService.getUserById(userId).equals(userService.getUserByEmail(authUserLogin));
    }

    public boolean isAdmin(String authUserLogin) {
        if (userService.getUserByEmail(authUserLogin) == null) {
            return false;
        }
        return userService.getUserByEmail(authUserLogin).getRole() == Role.ADMIN;
    }
}
