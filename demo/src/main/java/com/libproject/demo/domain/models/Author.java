package com.libproject.demo.domain.models;
import java.util.ArrayList;
import java.util.List;

import com.libproject.demo.domain.dto.AuthorDto;


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

    @Column(name = "image_path",nullable = false)
    private String imagePath;


    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;   

    public static Author convert (AuthorDto authorDto){
        List<Book> books = new ArrayList<Book>();
        return new Author (authorDto.getId(), authorDto.getName(), authorDto.getCitizenship(),authorDto.getImagePath(),books);
    }
}
