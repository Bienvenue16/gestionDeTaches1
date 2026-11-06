package com.example.gestionDeTaches1.Exception;


/**
 * Exception levee lorsqu'une entite est nulle
 * lors d'une operation de conversion en DTO.
 */
public class NullEntityException extends RuntimeException {

    public NullEntityException(String message) {
        super(message);
    }
}