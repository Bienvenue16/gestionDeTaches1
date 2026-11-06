
package com.example.gestionDeTaches1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gestionDeTaches1.DTO.TaskRequestDto;
import com.example.gestionDeTaches1.DTO.TaskResponseDto;
import com.example.gestionDeTaches1.DTO.TaskUpdateRequestDto;
import com.example.gestionDeTaches1.Exception.ResourceNotFoundException;
import com.example.gestionDeTaches1.Mapper.TaskMapper;
import com.example.gestionDeTaches1.Model.Board;
import com.example.gestionDeTaches1.Model.Task;
import com.example.gestionDeTaches1.Model.TaskStatus;
import com.example.gestionDeTaches1.Repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final BoardService boardService;
    private final TaskMapper taskMapper;

    // CREATE
    public TaskResponseDto createTask(Long boardId, TaskRequestDto requestDTO) {

        Board board = boardService.findBoardEntityById(boardId);

        Task task = taskMapper.toEntity(requestDTO);
        task.setBoard(board);

        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponseDTO(savedTask);
    }

    // READ : toutes les tâches d'un tableau
    public List<TaskResponseDto> getTasksByBoard(Long boardId) {

        boardService.findBoardEntityById(boardId);

        return taskRepository.findByBoardId(boardId)
                .stream()
                .map(taskMapper::toResponseDTO)
                .toList();
    }

    // READ : une tâche
    public TaskResponseDto getTaskById(Long boardId, Long taskId) {

        Task task = findTaskEntityByIdAndBoardId(boardId, taskId);

        return taskMapper.toResponseDTO(task);
    }

    // UPDATE : modifier une tâche
    public TaskResponseDto updateTask(
            Long boardId,
            Long taskId,
            TaskUpdateRequestDto requestDTO) {

        Task task = findTaskEntityByIdAndBoardId(boardId, taskId);

        taskMapper.updateEntityFromDTO(task, requestDTO);

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toResponseDTO(updatedTask);
    }

    // READ : filtrer les tâches par statut
    public List<TaskResponseDto> getTasksByStatus(
            Long boardId, TaskStatus status) {

        boardService.findBoardEntityById(boardId);

        return taskRepository.findByBoardIdAndStatus(boardId, status)
                .stream()
                .map(taskMapper::toResponseDTO)
                .toList();
    }

    // UPDATE : modifier uniquement le statut
    public TaskResponseDto updateTaskStatus(
            Long boardId, Long taskId, TaskStatus newStatus) {

        Task task = findTaskEntityByIdAndBoardId(boardId, taskId);

        task.setStatus(newStatus);

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toResponseDTO(updatedTask);
    }

    // DELETE
    public void deleteTask(Long boardId, Long taskId) {

        Task task = findTaskEntityByIdAndBoardId(boardId, taskId);

        taskRepository.delete(task);
    }

    // Récupérer une tâche par son identifiant
    public Task findTaskEntityById(Long id) {

        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tâche introuvable avec id=" + id));
    }

    // Vérifier qu'une tâche appartient bien au tableau demandé
    private Task findTaskEntityByIdAndBoardId(Long boardId, Long taskId) {

        boardService.findBoardEntityById(boardId);

        Task task = findTaskEntityById(taskId);

        if (!task.getBoard().getId().equals(boardId)) {
            throw new ResourceNotFoundException(
                    "Cette tâche n'appartient pas au tableau avec id=" + boardId);
        }

        return task;
    }
}
