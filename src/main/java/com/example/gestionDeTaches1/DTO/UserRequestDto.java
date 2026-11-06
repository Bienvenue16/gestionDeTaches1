
package com.example.gestionDeTaches1.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 3, max = 100, message = "Le nom doit contenir entre 3 et 100 caractères")
    private String nom;

    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "L'adresse email doit avoir un format valide")
    private String mail;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
}
