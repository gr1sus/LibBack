package com.libproject.demo.api.controllers;

import org.springframework.http.HttpStatus;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.libproject.demo.domain.dto.LoginDto;
import com.libproject.demo.domain.dto.UserDto;
import com.libproject.demo.domain.models.Role;
import com.libproject.demo.domain.models.User;
import com.libproject.demo.service.SecurityService;
import com.libproject.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/user/")
@RequiredArgsConstructor
public class UserControler {
    private final UserService userService;
    private final SecurityService securityService;
    private static final String UPLOAD_DIR_IMAGE = "demo/public/imageUser/";


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
    public ResponseEntity<?> newUser(@RequestParam("name") String firstName,
                                    @RequestParam("name") String lastName,
                                    @RequestParam("name") String email,
                                    @RequestParam("name") String password,
                                    @RequestParam("imageFile") MultipartFile imageFile, 
                                    @RequestParam("role") Role role){
        System.out.println("new User");
        userService.createUser(firstName, lastName, email, password, imageFile, role);
        System.out.println("registered new user: " + firstName + " " + lastName);
        securityService.login(email, password);
        System.out.println("logged in new user: " + firstName + " " + lastName);
        // securityService.login(user.getEmail(), user.getPassword());

         if (imageFile.isEmpty()) {
            System.out.println("no file");
        }
        try{
            String fileName = imageFile.getOriginalFilename();
            Path filePath = Path.of(UPLOAD_DIR_IMAGE + fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

     @GetMapping("allUsers")
    public List<User> getAll () {
         return userService.getAll();
     }


    @GetMapping("{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable long userId) {  
     return new ResponseEntity<>(UserDto.convert(userService.getUserById(userId)), new HttpHeaders(), HttpStatus.OK);  
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId){
        userService.deleteUser(userId);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
