package com.libproject.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libproject.demo.domain.models.Book;
import com.libproject.demo.domain.models.Genre;

public interface BookRepository extends JpaRepository<Book,Long>{

    Object getBookById(long id);

    Book findByName(String name);

    Book findByBookUrl(String book_url);

    List<Book> findByGenre(Genre genre);
    


} 
