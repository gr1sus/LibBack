package com.libproject.demo.api.controllers;

import java.io.IOException;
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

import com.libproject.demo.domain.dto.BookDto;

import com.libproject.demo.domain.models.Genre;
import com.libproject.demo.service.BookService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/book/")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private static final String UPLOAD_DIR_BOOKS = "demo/public/books/";
    private static final String UPLOAD_DIR_IMAGE_BOOKS = "demo/public/image/";

    @PostMapping("new")
    public ResponseEntity<?> newBook(@RequestParam("name") String name,
                                     @RequestParam("bookFile") MultipartFile bookFile,
                                     @RequestParam("imageBookFile") MultipartFile imageBookFile,
                                     @RequestParam("description") String description,
                                     @RequestParam("genre") Genre genre,
                                     @RequestParam("author_id") long author_id) throws IOException  {
        System.out.println("new Book");
        bookService.createBook(name,bookFile,imageBookFile,description,genre,author_id);
        System.out.println("registered new book: " + name);
        if (bookFile.isEmpty()) {
            System.out.println("no file");
        }
        try{
            String fileName = bookFile.getOriginalFilename();
            Path filePath = Path.of(UPLOAD_DIR_BOOKS + fileName);
            Files.copy(bookFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(Exception e){
            System.out.println(e);
        }

        if (imageBookFile.isEmpty()) {
            System.out.println("no file");
        }
        try{
            String fileName = imageBookFile.getOriginalFilename();
            Path filePath = Path.of(UPLOAD_DIR_IMAGE_BOOKS + fileName);
            Files.copy(imageBookFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("allBoks")
    public List<BookDto> getAll(){
        return bookService.getAll();
    }
    
    @GetMapping("{bookId}")
    public ResponseEntity<?> getBookDetails(@PathVariable long bookId){
        return new ResponseEntity<>(BookDto.convert(bookService.getBookById(bookId)), new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("byGenre")
    public List<BookDto> getBookByGenre(@RequestParam Genre genre){
        return bookService.getBookByGenre(genre);
    }

    

    @GetMapping("byName")
    public BookDto getBookByName(@RequestParam String name){
        return bookService.getBookByName(name);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<?> deleteBookById(@PathVariable long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
