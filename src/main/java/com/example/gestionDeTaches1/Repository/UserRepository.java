package com.example.gestionDeTaches1.Repository;
import com.example.gestionDeTaches1.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository pour l'entite User.
 *
 * En etendant JpaRepository<User, Long>, on recoit GRATUITEMENT (sans
 * ecrire une seule ligne de SQL) : save(), findById(), findAll(),
 * deleteById(), existsById(), etc. C'est le coeur de la "magie" Spring
 * Data JPA a bien montrer aux apprenants : Spring genere l'implementation
 * de cette interface au demarrage de l'application.
 *
 * findByEmail() est une "query method derivee" : Spring Data lit le nom
 * de la methode et genere automatiquement la requete SQL correspondante
 * (ici : SELECT * FROM users WHERE email = ?).
 */
@Repository 
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

