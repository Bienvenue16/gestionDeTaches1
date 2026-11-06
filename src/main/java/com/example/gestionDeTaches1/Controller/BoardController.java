
package com.example.gestionDeTaches1.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestionDeTaches1.DTO.BoardRequestDto;
import com.example.gestionDeTaches1.DTO.BoardResponseDto;
import com.example.gestionDeTaches1.Service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // Créer un tableau
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(
            @RequestParam Long ownerId,
            @RequestBody BoardRequestDto requestDTO) {

        BoardResponseDto created = boardService.createBoard(ownerId, requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Lister les tableaux d'un utilisateur
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getBoards(
            @RequestParam Long ownerId) {

        return ResponseEntity.ok(boardService.getBoardsByOwner(ownerId));
    }

    // Récupérer un tableau par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(
            @PathVariable Long id) {

        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    // Modifier un tableau
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequestDto requestDTO) {

        BoardResponseDto updated = boardService.updateBoard(id, requestDTO);

        return ResponseEntity.ok(updated);
    }

    // Supprimer un tableau
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {

        boardService.deleteBoard(id);

        return ResponseEntity.noContent().build();
    }
}
