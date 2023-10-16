package com.example.ToDoList.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemResponse {

    private Long id;
    private String title;
    private boolean completed;
}
