
package com.example.gestionDeTaches1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gestionDeTaches1.DTO.BoardRequestDto;
import com.example.gestionDeTaches1.DTO.BoardResponseDto;
import com.example.gestionDeTaches1.Exception.ResourceNotFoundException;
import com.example.gestionDeTaches1.Mapper.BoardMapper;
import com.example.gestionDeTaches1.Model.Board;
import com.example.gestionDeTaches1.Model.User;
import com.example.gestionDeTaches1.Repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final BoardMapper boardMapper;

    // CREATE
    public BoardResponseDto createBoard(Long ownerId, BoardRequestDto requestDTO) {

        Long targetOwnerId = (ownerId != null) ? ownerId : requestDTO.getOwnerId();

        if (targetOwnerId == null) {
            throw new IllegalArgumentException(
                    "L'identifiant du proprietaire (ownerId) est obligatoire");
        }

        User owner = userService.getUserEntityById(targetOwnerId);

        Board board = boardMapper.toEntity(requestDTO);
        board.setOwner(owner);

        Board savedBoard = boardRepository.save(board);

        return boardMapper.toResponseDTO(savedBoard);
    }

    // READ : tous les tableaux d'un utilisateur
    public List<BoardResponseDto> getBoardsByOwner(Long ownerId) {

        userService.getUserEntityById(ownerId);

        return boardRepository.findByOwnerId(ownerId)
                .stream()
                .map(boardMapper::toResponseDTO)
                .toList();
    }

    // READ : un tableau par son identifiant
    public BoardResponseDto getBoardById(Long id) {

        Board board = findBoardEntityById(id);

        return boardMapper.toResponseDTO(board);
    }

    // UPDATE
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDTO) {

        Board existingBoard = findBoardEntityById(id);

        existingBoard.setTitle(requestDTO.getTitle());

        Board updatedBoard = boardRepository.save(existingBoard);

        return boardMapper.toResponseDTO(updatedBoard);
    }

    // DELETE
    public void deleteBoard(Long id) {

        Board board = findBoardEntityById(id);

        boardRepository.delete(board);
    }

    // Méthode utilitaire
    public Board findBoardEntityById(Long id) {

        return boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tableau introuvable avec id=" + id));
    }
}
