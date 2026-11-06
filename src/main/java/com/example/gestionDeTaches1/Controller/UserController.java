
package com.example.gestionDeTaches1.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestionDeTaches1.DTO.UserRequestDto;
import com.example.gestionDeTaches1.DTO.UserResponseDto;
import com.example.gestionDeTaches1.Service.UserService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CREATE
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @Valid @RequestBody UserRequestDto requestDto) {

        UserResponseDto created = userService.createUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    // READ BY EMAIL
    @GetMapping("/by-email")
    public ResponseEntity<UserResponseDto> getUserByEmail(
            @RequestParam String email) {

        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto requestDto) {

        UserResponseDto updated = userService.updateUser(id, requestDto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
