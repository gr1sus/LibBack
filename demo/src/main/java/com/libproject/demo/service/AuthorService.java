package com.libproject.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.libproject.demo.domain.dto.AuthorDto;
import com.libproject.demo.domain.models.Author;

import com.libproject.demo.repository.AuthorRepository;
import com.libproject.demo.utils.FileUpoadsUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final String IMAGE_PATH = "image/";
    
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
    
    public Author createAuthor(String name,String citizenship,MultipartFile imageAuthorPath){
        Author author = Author.builder().name(name).citizenship(citizenship).imagePath(IMAGE_PATH + imageAuthorPath.getOriginalFilename()).build();
        return authorRepository.save(author);
        
    }

    public Author updateAuthor(long id,String name,String citizenship,MultipartFile imageAuthorPath){
        Author author = authorRepository.findById(id).orElseThrow();

        FileUpoadsUtil.saveFile(imageAuthorPath, IMAGE_PATH);

        author.setName(name);
        author.setCitizenship(citizenship);
        author.setImagePath(IMAGE_PATH + imageAuthorPath.getOriginalFilename());
        
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

