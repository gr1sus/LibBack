package com.libproject.demo.domain.dto;
import com.libproject.demo.domain.models.Book;
import com.libproject.demo.domain.models.Genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long id;
    private String name;
    private String bookUrl;
    private String description;
    private Genre genre;

    public static BookDto convert (Book book){
        return new BookDto(book.getId(), book.getName(),book.getBookUrl(),book.getDescription(), book.getGenre());
    }
}