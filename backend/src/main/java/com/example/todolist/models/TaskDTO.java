package com.example.todolist.models;

import lombok.Data;

@Data
public class TaskDTO {
    private String taskName;
    private String taskDescription;

    private boolean completion;
}
