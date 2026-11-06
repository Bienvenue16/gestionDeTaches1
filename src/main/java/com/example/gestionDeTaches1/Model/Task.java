package com.example.gestionDeTaches1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks") // "user" est un mot reserve dans certaines BD (ex. Postgres) -> on utilise "users"
@Getter
@Setter
@NoArgsConstructor   // requis par JPA/Hibernate (constructeur sans argument)
@AllArgsConstructor

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    /**
     * @Enumerated(STRING) : stocke le nom de l'enum ("A_FAIRE", "EN_COURS",
     * "TERMINE") en base plutot que son index numerique (0, 1, 2).
     *
     * C'est essentiel : si un jour on ajoute une valeur au milieu de l'enum,
     * stocker l'index numerique decalerait silencieusement le sens de toutes
     * les donnees existantes. Stocker le nom est lisible et stable.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.A_FAIRE; // RG5 : statut initial impose

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
}

