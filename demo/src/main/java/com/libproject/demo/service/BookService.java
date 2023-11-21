package com.libproject.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.libproject.demo.domain.dto.BookDto;
import com.libproject.demo.domain.models.Book;
import com.libproject.demo.domain.models.Genre;
import com.libproject.demo.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getBookById(long id){
        Optional <Book> book = bookRepository.findById(id);
        if (book.isEmpty()){
            System.out.println("you must kill yourself piece of shit");
        }
        return book.get();
    }

    public Book getBookByName(String name){
        return bookRepository.findByName(name);
    }

    public Book getBookByUrl(String book_url){
        return bookRepository.findByBookUrl(book_url);
    }

    public List<Book> getBookByGenre(Genre genre){
        return bookRepository.findByGenre(genre);
    }

    public Book createBook(BookDto bookDto){
        Book book = Book.builder()
                .name(bookDto.getName())
                .bookUrl(bookDto.getBookUrl())
                .description(bookDto.getDescription())
                .genre(bookDto.getGenre())
                .build();
        return bookRepository.save(book);
    } 

    public void deleteBook(long id){
        Book book = getBookById(id);
        bookRepository.delete(book);
    }


}
