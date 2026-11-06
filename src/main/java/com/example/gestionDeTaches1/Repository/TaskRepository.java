package com.example.gestionDeTaches1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionDeTaches1.Model.Task;
import com.example.gestionDeTaches1.Model.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * SELECT * FROM tasks WHERE board_id = ?
     * Genere automatiquement par Spring Data a partir du nom de la methode.
     */
    List<Task> findByBoardId(Long boardId);
    List<Task> findByBoardIdAndStatus(Long boardId, TaskStatus status);//Elle permettra de récupérer uniquement les tâches d'un tableau ayant un statut précis.
    Optional<Task> findByIdAndBoardId(Long taskId, Long boardId);
}