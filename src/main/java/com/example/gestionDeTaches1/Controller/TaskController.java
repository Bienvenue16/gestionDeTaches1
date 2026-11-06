
package com.example.gestionDeTaches1.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestionDeTaches1.DTO.TaskRequestDto;
import com.example.gestionDeTaches1.DTO.TaskResponseDto;
import com.example.gestionDeTaches1.DTO.TaskUpdateRequestDto;
import com.example.gestionDeTaches1.Model.TaskStatus;
import com.example.gestionDeTaches1.Service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // CREATE
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @PathVariable Long boardId,
            @RequestBody TaskRequestDto requestDTO) {

        TaskResponseDto created = taskService.createTask(boardId, requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ : toutes les tâches d'un tableau
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getTasks(
            @PathVariable Long boardId) {

        return ResponseEntity.ok(taskService.getTasksByBoard(boardId));
    }

    // READ : une tâche par son identifiant
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> getTaskById(
            @PathVariable Long boardId,
            @PathVariable Long taskId) {

        return ResponseEntity.ok(
                taskService.getTaskById(boardId, taskId));
    }

    // UPDATE
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable Long boardId,
            @PathVariable Long taskId,
            @RequestBody TaskUpdateRequestDto requestDTO) {

        return ResponseEntity.ok(
                taskService.updateTask(boardId, taskId, requestDTO));
    }

    // READ : filtrer les tâches par statut
    @GetMapping("/status")
    public ResponseEntity<List<TaskResponseDto>> getTasksByStatus(
            @PathVariable Long boardId,
            @RequestParam TaskStatus status) {

        return ResponseEntity.ok(
                taskService.getTasksByStatus(boardId, status));
    }

    // UPDATE : modifier uniquement le statut
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(
            @PathVariable Long boardId,
            @PathVariable Long taskId,
            @RequestBody TaskStatus newStatus) {

        return ResponseEntity.ok(
                taskService.updateTaskStatus(boardId, taskId, newStatus));
    }

    // DELETE
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long boardId,
            @PathVariable Long taskId) {

        taskService.deleteTask(boardId, taskId);

        return ResponseEntity.noContent().build();
    }
}
