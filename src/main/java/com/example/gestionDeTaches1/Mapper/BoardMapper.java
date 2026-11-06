package com.example.gestionDeTaches1.Mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.gestionDeTaches1.DTO.BoardRequestDto;
import com.example.gestionDeTaches1.DTO.BoardResponseDto;
import com.example.gestionDeTaches1.Model.Board;

/**
 * Mapper pour transformer les objets Board en DTOs et inversement.
 */
@Component
public class BoardMapper {

    /**
     * Convertit un DTO de creation en entite Board.
     */
    public Board toEntity(BoardRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setCreatedAt(LocalDateTime.now());
        return board;
    }

    /**
     * Convertit une entite Board en DTO de reponse.
     */
    public BoardResponseDto toResponseDTO(Board entity) {
        if (entity == null) {
            return null;
        }
        return BoardResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .ownerId(entity.getOwner() != null ? entity.getOwner().getId() : null)
                .ownerName(entity.getOwner() != null ? entity.getOwner().getName() : null)
                .build();
    }
}
