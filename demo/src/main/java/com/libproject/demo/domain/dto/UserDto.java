package com.libproject.demo.domain.dto;

import java.util.List;

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
    private List<BookDto> books;

    public static UserDto convert(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getImagePath(), user.getRole(),user.getBooks().stream().map(book->BookDto.convert(book)).toList());
    }

    public static List<UserDto> convert(List<User> users){
        return users.stream().map(user->UserDto.convert(user)).toList();
    }
}