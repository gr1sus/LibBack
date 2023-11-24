package com.libproject.demo.domain.dto;

import com.libproject.demo.domain.models.Role;
import com.libproject.demo.domain.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String imagePath;
    private Role role;
    
    public static UserDto convert(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getImagePath(), user.getRole());
    }
}