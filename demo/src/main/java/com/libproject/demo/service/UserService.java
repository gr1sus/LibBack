package com.libproject.demo.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;

import com.libproject.demo.domain.dto.UserDto;
import com.libproject.demo.domain.models.User;
import com.libproject.demo.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



//    public User findByName(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByName(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return user;
//    }

    public List<User> getAll(){
        return userRepository.findAll();
    }    

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid user id" + id));
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        return user.get();
    }

    public User createUser(UserDto userDTO) {
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(userDTO.getRole())
                .build();
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        User user = getUserById(id);
        //там стоит cascade all, по идее должен удалять все
        userRepository.delete(user);
    }

    // public void updateUser(long id, UserDto userDTO) {
    //     User user = getUserById(id);
    //    
    //     user.setFirstName(userDTO.getFirstName());
    //     user.setLastName(userDTO.getLastName());
    //     if (userDTO.getPassword() != null && userDTO.getPassword().length() > 0) {
    //         user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    //     }
    //
    //     if (userDTO.getColor() != null && !userDTO.getColor().equals("")) {
    //         user.setColor(userDTO.getColor());
    //     }
    //
    //     try {
    //         userRepository.save(user);
    //     } catch (DataIntegrityViolationException e) {
    //         log.error("Login not unique: " + userDTO.getEmail());
    //     }
    // }

}