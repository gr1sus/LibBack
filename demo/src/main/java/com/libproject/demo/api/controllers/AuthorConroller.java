package com.libproject.demo.api.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libproject.demo.domain.dto.AuthorDto;

import com.libproject.demo.domain.models.Author;
import com.libproject.demo.service.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/author/")
@RequiredArgsConstructor
public class AuthorConroller {
    private final AuthorService authorService;

    @PostMapping("new")
    public ResponseEntity<?> newAuthor(@RequestBody AuthorDto author){
        System.out.println("new Author");
        authorService.createAuthor(author);
        System.out.println("registered new author: " + author.getName());
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("all")
    public List<Author> getAll(){
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