package com.example.gestionDeTaches1.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "L'adresse email doit avoir un format valide")
    private String mail;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String Password;
}
