package com.libproject.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.libproject.demo.domain.dto.AuthorDto;
import com.libproject.demo.domain.dto.BookDto;
import com.libproject.demo.domain.dto.BookDtoAuthor;
import com.libproject.demo.domain.models.Author;
import com.libproject.demo.domain.models.Book;
import com.libproject.demo.domain.models.Genre;
import com.libproject.demo.repository.AuthorRepository;
import com.libproject.demo.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<BookDto> getAll(){
        List<BookDto> bookDto = bookRepository.findAll().stream().map(book->BookDto.convert(book)).toList();
        return bookDto;
    }

    public Book getBookById(long id){
        Optional <Book> book = bookRepository.findById(id);
        if (book.isEmpty()){
            System.out.println("you must kill yourself piece of shit");
        }
        return book.get();
    }

    public BookDto getBookByName(String name){
        BookDto bookDto = BookDto.convert(bookRepository.findByName(name));
        return bookDto;
    }

    public BookDto getBookByUrl(String book_url){
        BookDto bookDto =BookDto.convert(bookRepository.findByBookUrl(book_url)); 
        return bookDto;
    }

    public List<BookDto> getBookByGenre(Genre genre){
        List<BookDto> bookDto = bookRepository.findByGenre(genre).stream().map(book->BookDto.convert(book)).toList();
        return bookDto;
    }

    public Book createBook(BookDtoAuthor bookDtoAuthor){
        Author author = authorRepository.findById(bookDtoAuthor.getAuthorId()).get();
        Book book = Book.builder()
                .name(bookDtoAuthor.getName())
                .bookUrl(bookDtoAuthor.getBookUrl())
                .description(bookDtoAuthor.getDescription())
                .genre(bookDtoAuthor.getGenre())
                .author(author)
                .build();
        return bookRepository.save(book);
    } 
    
    public void deleteBook(long id){
        Book book = getBookById(id);
        bookRepository.delete(book);
    }


}
