package com.example.ToDoList.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemRequest {
    private Long id;
    private String title;
    private boolean completed;
}
