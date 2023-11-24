package com.libproject.demo.service;


import lombok.RequiredArgsConstructor;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.libproject.demo.domain.dto.AuthorDto;
import com.libproject.demo.domain.dto.UserDto;
import com.libproject.demo.domain.models.Role;
import com.libproject.demo.domain.models.User;
import com.libproject.demo.repository.UserRepository;
import com.libproject.demo.utils.FileUpoadsUtil;

import java.util.List;
import java.util.Optional;



import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BookService bookService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String IMAGE_USER_PATH = "image/";


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

    public User createUser(String firstName, String lastName, String email, String password, MultipartFile imageUserPath, Role role ) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .imagePath(IMAGE_USER_PATH+imageUserPath.getOriginalFilename())
                .role(role)
                .build();
        return userRepository.save(user);
    }

    public User updateUser(long id, String firstName,String lastName,String email,String password,MultipartFile imageUserPath, Role role){
        User user = userRepository.findById(id).orElseThrow();
        FileUpoadsUtil.saveFile(imageUserPath, IMAGE_USER_PATH);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        if(email != null && !email.equals("")){
            user.setEmail(email);
        }
        if(password != null && password.length()>0){
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setImagePath(IMAGE_USER_PATH+imageUserPath.getOriginalFilename());
        user.setRole(role);
        return userRepository.save(user);
    }


    public void deleteUser(long id) {
        User user = getUserById(id);
        //там стоит cascade all, по идее должен удалять все
        userRepository.delete(user);
    }

    public User addBook(long user_id,long book_id){
        User user = getUserById(user_id);
        user.addBook(bookService.getBookById(book_id));
        return userRepository.save(user);
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