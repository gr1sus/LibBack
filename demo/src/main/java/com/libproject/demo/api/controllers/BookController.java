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

import com.libproject.demo.domain.dto.BookDto;
import com.libproject.demo.domain.models.Book;
import com.libproject.demo.domain.models.Genre;
import com.libproject.demo.service.BookService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/book/")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @PostMapping("new")
    public ResponseEntity<?> newBook(@RequestBody BookDto book){
        System.out.println("new Book");
        bookService.createBook(book);
        System.out.println("registered new book: " + book.getName());
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("allBoks")
    public List<Book> getAll(){
        return bookService.getAll();
    }
    
    @GetMapping("{bookId}")
    public ResponseEntity<?> getBookDetails(@PathVariable long bookId){
        return new ResponseEntity<>(BookDto.convert(bookService.getBookById(bookId)), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("byGenre")
    public List<Book> getBookByGenre(@RequestParam Genre genre){
        return bookService.getBookByGenre(genre);
    }

    @GetMapping("byUrl")
    public Book getBookByUrl(@PathVariable String url){
        return bookService.getBookByUrl(url);
    }

    @GetMapping("byName")
    public Book getBookByName(@PathVariable String name){
        return bookService.getBookByName(name);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<?> deleteBookById(@PathVariable long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
