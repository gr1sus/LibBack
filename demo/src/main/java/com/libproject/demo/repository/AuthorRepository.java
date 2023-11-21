package com.libproject.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libproject.demo.domain.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByCitizenship(String citizenship);
    
}
