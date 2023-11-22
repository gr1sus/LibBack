package com.libproject.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.libproject.demo.domain.dto.AuthorDto;
import com.libproject.demo.domain.models.Author;

import com.libproject.demo.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {
    
    private final AuthorRepository authorRepository;

    public List<AuthorDto> getAll(){
        List<AuthorDto> authorDto = authorRepository.findAll().stream().map(author->AuthorDto.convert(author)).toList();
        return authorDto;
    }

    public AuthorDto getAuthorById(long id){
         Author author =  authorRepository.findById(id).orElseThrow();
         AuthorDto authorDto =  AuthorDto.convert(author);
        return authorDto;
    }
    
    public Author createAuthor(AuthorDto authorDto){
        Author author = Author.builder()
            .name(authorDto.getName())
            .citizenship(authorDto.getCitizenship())
            .build();
        return authorRepository.save(author);
    }
    
    public List<Author> getAuthorByCitizenship(String citizenship){
        return authorRepository.findByCitizenship(citizenship);
    }

    public void deleteAuthor(long id){
        Author author = authorRepository.findById(id).orElseThrow();
        authorRepository.delete(author);
    }
}

