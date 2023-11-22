package com.libproject.demo.domain.models;
import java.util.ArrayList;
import java.util.List;

import com.libproject.demo.domain.dto.AuthorDto;
import com.libproject.demo.domain.dto.BookDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "citizenship")
    private String citizenship;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;   

    public static Author convert (AuthorDto authorDto){
        List<Book> books = new ArrayList<Book>();
        return new Author (authorDto.getId(), authorDto.getName(), authorDto.getCitizenship(),books);
    }
}
