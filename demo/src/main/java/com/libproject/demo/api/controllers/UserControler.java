package com.libproject.demo.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libproject.demo.domain.dto.LoginDto;
import com.libproject.demo.domain.dto.UserDto;
import com.libproject.demo.service.SecurityService;
import com.libproject.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/user/")
@RequiredArgsConstructor
public class UserControler {
    private final UserService userService;
    private final SecurityService securityService;

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Huy", HttpStatus.BAD_REQUEST);
        }
        try {
            securityService.login(dto.getLogin(), dto.getPassword());
            return new ResponseEntity<>("Login success", HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Bad Creds", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("new")
    public ResponseEntity<?> newUser(@RequestBody UserDto user) {
        System.out.println("new User");
        userService.createUser(user);
        System.out.println("registered new user: " + user.getFirstName() + " " + user.getLastName());
        securityService.login(user.getEmail(), user.getPassword());
        System.out.println("logged in new user: " + user.getFirstName() + " " + user.getLastName());
        // securityService.login(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
