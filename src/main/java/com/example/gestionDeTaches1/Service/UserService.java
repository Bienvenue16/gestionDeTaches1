
package com.example.gestionDeTaches1.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

import com.example.gestionDeTaches1.Model.User;
import com.example.gestionDeTaches1.Repository.UserRepository;
import com.example.gestionDeTaches1.DTO.UserRequestDto;
import com.example.gestionDeTaches1.DTO.UserResponseDto;
import com.example.gestionDeTaches1.Exception.DuplicateResourceException;
import com.example.gestionDeTaches1.Exception.ResourceNotFoundException;
import com.example.gestionDeTaches1.Mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // CREATE
    public UserResponseDto createUser(UserRequestDto requestDTO) {

        if (userRepository.existsByEmail(requestDTO.getMail())) {
            throw new DuplicateResourceException(
                "Un utilisateur avec cet email existe déjà : " + requestDTO.getMail()
            );
        }

        User user = userMapper.toEntity(requestDTO);
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDTO(savedUser);
    }

    // READ ALL
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
            .stream()
            .map(userMapper::toResponseDTO)
            .toList();
    }

    // READ BY ID : retourne un DTO
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Utilisateur introuvable avec id=" + id
            ));

        return userMapper.toResponseDTO(user);
    }

    // READ ENTITY BY ID : utilisé par les autres services
    public User getUserEntityById(Long id) {

        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Utilisateur introuvable avec id=" + id
            ));
    }

    // READ BY EMAIL
    public UserResponseDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Utilisateur introuvable avec email=" + email
            ));

        return userMapper.toResponseDTO(user);
    }

    // UPDATE
    public UserResponseDto updateUser(Long id, UserRequestDto requestDTO) {

        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Utilisateur introuvable avec id=" + id
            ));

        boolean emailHasChanged =
            !existingUser.getEmail().equals(requestDTO.getMail());

        if (emailHasChanged && userRepository.existsByEmail(requestDTO.getMail())) {
            throw new DuplicateResourceException(
                "Un utilisateur avec cet email existe déjà : " + requestDTO.getMail()
            );
        }

        existingUser.setName(requestDTO.getNom());
        existingUser.setEmail(requestDTO.getMail());
        existingUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        User updatedUser = userRepository.save(existingUser);

        return userMapper.toResponseDTO(updatedUser);
    }

    // DELETE
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "Utilisateur introuvable avec id=" + id
            );
        }

        userRepository.deleteById(id);
    }
}
