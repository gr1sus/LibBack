package com.libproject.demo.domain.dto;

import java.util.List;

import com.libproject.demo.domain.models.Author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private long id;
    private String name;
    private String citizenship;
    private List<BookDto> books;

    public static AuthorDto convert(Author author){
        return new AuthorDto(author.getId(), author.getName(), author.getCitizenship(), author.getBooks().stream().map(book->BookDto.convert(book)).toList());
    }
}
