package com.libproject.demo.domain.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "image_path",nullable = false)
    private String imagePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = " user_books", 
        joinColumns = { @JoinColumn(name="user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
    List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (books == null){
            books = new ArrayList<>();
        }
        books.add(book);
    }


}
