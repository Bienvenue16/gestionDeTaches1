package com.example.gestionDeTaches1.Mapper;

import org.springframework.stereotype.Component;

import com.example.gestionDeTaches1.DTO.UserRequestDto;
import com.example.gestionDeTaches1.DTO.UserResponseDto;
import com.example.gestionDeTaches1.Exception.NullEntityException;
import com.example.gestionDeTaches1.Model.User;

/**
 * Mapper pour transformer les objets User en DTOs et inversement.
 * Composant Spring injectable grace a @Component.
 */
@Component
public class UserMapper {

    /**
     * Convertit un DTO de creation en entite User.
     */
    public User toEntity(UserRequestDto dto) {
        if (dto == null) {
            throw new NullEntityException(
             "Impossible de convertir un DTO null en entite User"
            );
        }
        User user = new User();
        user.setName(dto.getNom());
        user.setEmail(dto.getMail());
        return user;
    }

    /**
     * Convertit une entite User en DTO de reponse.
     */
    public UserResponseDto toResponseDTO(User entity) {
         if (entity == null) {
            throw new NullEntityException(
            "Impossible de convertir un utilisateur null en DTO"
            );
       }

        return UserResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
}
