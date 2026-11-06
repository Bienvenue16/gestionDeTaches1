package com.example.gestionDeTaches1.Model;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// @Builder 
@Entity
@Table(name = "users") // "user" est un mot reserve dans certaines BD (ex. Postgres) -> on utilise "users"
@Getter
@Setter
@NoArgsConstructor   // requis par JPA/Hibernate (constructeur sans argument)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column (nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>(); 
}
