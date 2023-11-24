package com.libproject.demo.api.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.libproject.demo.domain.dto.AuthorDto;

import com.libproject.demo.domain.models.Author;
import com.libproject.demo.service.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/author/")
@RequiredArgsConstructor
public class AuthorConroller {


    private final AuthorService authorService;
    private static final String UPLOAD_DIR_IMAGE = "demo/public/image/";



    @PostMapping("new")
    public ResponseEntity<?> newAuthor(@RequestParam("name") String name,
                                       @RequestParam("citizenship") String citizenship,
                                       @RequestParam("imageFile") MultipartFile imageFile){
        System.out.println("new Author");
        authorService.createAuthor(name,citizenship, imageFile);
        System.out.println("registered new author: " + name);

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

    @GetMapping("all")
    public List<AuthorDto> getAll(){
        return authorService.getAll();
    }

    @GetMapping("{author_id}")
    public ResponseEntity<?> getAuthorDetails(@PathVariable long author_id){
        return new ResponseEntity<>((authorService.getAuthorById(author_id)), new HttpHeaders(), HttpStatus.OK);
    }
   
    @GetMapping("byCitizenship")
    public List<Author>  getAuthorByCitizenship(@RequestParam String citizenship){
        return authorService.getAuthorByCitizenship(citizenship);
    }
    
    @DeleteMapping("{author_id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable long author_id){
        authorService.deleteAuthor(author_id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
