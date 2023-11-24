package com.libproject.demo.domain.models;
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
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;
    
    @Column(name = "name",nullable = false)
    private String name;

    
    @Column(name = "book_path",nullable = false)
    private String bookPath;

    @Column(name = "image_path",nullable = false)
    private String imagePath;

    @Column(name = "description")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "genre",nullable = false)
    private Genre genre;
    
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private Author author;
}
