package com.libproject.demo.service;

import java.io.IOException;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.libproject.demo.domain.dto.BookDto;

import com.libproject.demo.domain.models.Author;
import com.libproject.demo.domain.models.Book;
import com.libproject.demo.domain.models.Genre;
import com.libproject.demo.repository.AuthorRepository;
import com.libproject.demo.repository.BookRepository;
import com.libproject.demo.utils.FileUpoadsUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    
    private final String BOOKS_PATH = "books/";
    private final String IMAGE_BOOKS_PATH = "image/";
    public List<BookDto> getAll(){
        List<BookDto> bookDto = bookRepository.findAll().stream().map(book->BookDto.convert(book)).toList();
        return bookDto;
    }

    
     public Book getBookById(long id){
        Book book = bookRepository.findById(id).orElseThrow();
        
        return book;
    }

    public BookDto getBookByName(String name){
        BookDto bookDto = BookDto.convert(bookRepository.findByName(name));
        return bookDto;
    }



    public List<BookDto> getBookByGenre(Genre genre){
        List<BookDto> bookDto = bookRepository.findByGenre(genre).stream().map(book->BookDto.convert(book)).toList();
        return bookDto;
    }

    public Book createBook(String name, MultipartFile bookFile,MultipartFile imageBookPath, String description, Genre genre, long author_id ) throws IOException{
        if(author_id != 0){
            Author author = authorRepository.findById(author_id).orElseThrow();
            Book book = Book.builder().name(name).bookPath(BOOKS_PATH + bookFile.getOriginalFilename()).imagePath(IMAGE_BOOKS_PATH + imageBookPath.getOriginalFilename()).description(description).genre(genre).author(author).build();
             return bookRepository.save(book);
        }
        else{
            Book book = Book.builder().name(name).bookPath(BOOKS_PATH + bookFile.getOriginalFilename()).imagePath(IMAGE_BOOKS_PATH + imageBookPath.getOriginalFilename()).description(description).genre(genre).build();
             return bookRepository.save(book);
        }
       
    } 

    public Book updateBook(long id, String name, MultipartFile bookFile,MultipartFile imageBookPath, String description, Genre genre, long author_id ){

        
        Author author = authorRepository.findById(author_id).orElseThrow();
        Book book = getBookById(id);

        FileUpoadsUtil.saveFile(bookFile, BOOKS_PATH);
        FileUpoadsUtil.saveFile(imageBookPath, IMAGE_BOOKS_PATH);

        book.setName(name);
        book.setBookPath(BOOKS_PATH + bookFile.getOriginalFilename());
        book.setImagePath(IMAGE_BOOKS_PATH + imageBookPath.getOriginalFilename());
        book.setDescription(description);
        book.setGenre(genre);
        book.setAuthor(author);
        return bookRepository.save(book);

    }
    
    public void deleteBook(long id){
        Book book = getBookById(id);
        bookRepository.delete(book);
    }


}
