package com.libproject.demo.domain.dto;
import org.springframework.stereotype.Component;


import com.libproject.demo.domain.models.Genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BookDtoAuthor {
    private long id;
    private String name;
    private String bookPath;
    private String description;
    private Genre genre;
    private long authorId;

    
}

 