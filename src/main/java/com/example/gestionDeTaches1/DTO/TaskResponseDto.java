package com.example.gestionDeTaches1.DTO;

import com.example.gestionDeTaches1.Model.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO renvoye pour representer une tache.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDto {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Long boardId;
}
