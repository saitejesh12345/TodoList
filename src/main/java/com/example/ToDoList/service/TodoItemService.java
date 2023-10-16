package com.example.ToDoList.service;

import com.example.ToDoList.response.TodoItemRequest;
import com.example.ToDoList.response.TodoItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoItemService {
    TodoItemResponse createTodoItem(TodoItemRequest todoItemRequest);

    TodoItemResponse getTodoItemById(Long id);
    List<TodoItemResponse> getAllTodoItems();


    TodoItemResponse updateTodoItem(Long id, TodoItemRequest todoItemRequest);
    void deleteTodoItem(Long id);
}
