package com.example.ToDoList.service;

import com.example.ToDoList.model.TodoItem;
import com.example.ToDoList.repository.TodoItemRepository;
import com.example.ToDoList.response.TodoItemRequest;
import com.example.ToDoList.response.TodoItemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoItemServiceImpl implements  TodoItemService{

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public TodoItemResponse createTodoItem(TodoItemRequest todoItemRequest) {
        //This line declares a public method named createTodoItem that takes a TodoItemRequest object
        // as a parameter and returns a TodoItemResponse object.
        TodoItem todoItem = objectMapper.convertValue(todoItemRequest, TodoItem.class);
        //This line uses an objectMapper to convert the todoItemRequest object into a TodoItem object.
        // The convertValue method is responsible for this conversion.

        TodoItem createdTodoItem = todoItemRepository.save(todoItem);
        //This line saves the todoItem object into the todoItemRepository.
        // The save method is typically used to persist the object in a database
        return objectMapper.convertValue(createdTodoItem, TodoItemResponse.class);
        //This line converts the createdTodoItem object into a TodoItemResponse object
        // using the objectMapper.
        // The resulting TodoItemResponse object is then returned by the method.

        //Summary:
    //In summary, this method takes a TodoItemRequest object, converts it into a TodoItem object,
        // saves it in a repository, and finally converts the saved TodoItem object
        // into a TodoItemResponse object before returning it.
        // The objectMapper is used for the conversion between different object types.
    }



    @Override
    public TodoItemResponse getTodoItemById(Long id) {
        TodoItem todoItem = todoItemRepository.findById(id).orElse(null);
        if (todoItem != null) {
            return objectMapper.convertValue(todoItem, TodoItemResponse.class);
        }
        return null;
    }

//    @Override
//    public List<TodoItemResponse> getAllTodoItems() {
//        List<TodoItem> todoItems = todoItemRepository.findAll();
//        List<TodoItemResponse> todoItemResponses = new ArrayList<>();
//        for (TodoItem todoItem : todoItems) {
//            TodoItemResponse todoItemResponse = objectMapper.convertValue(todoItem, TodoItemResponse.class);
//            todoItemResponses.add(todoItemResponse);
//        }
//        return todoItemResponses;
//    }
@Override
public List<TodoItemResponse> getAllTodoItems() {
    List<TodoItem> todoItems = todoItemRepository.findAll();
    List<TodoItemResponse> todoItemResponses = new ArrayList<>();
    for (TodoItem todoItem : todoItems) {
        TodoItemResponse todoItemResponse = objectMapper.convertValue(todoItem, TodoItemResponse.class);
        todoItemResponses.add(todoItemResponse);
    }
    return todoItemResponses;
}

    @Override
    public TodoItemResponse updateTodoItem(Long id, TodoItemRequest todoItemRequest) {
        TodoItem existingTodoItem = todoItemRepository.findById(id).orElse(null);
        if (existingTodoItem != null) {
            existingTodoItem.setTitle(todoItemRequest.getTitle());
            existingTodoItem.setCompleted(todoItemRequest.isCompleted());
            TodoItem updatedTodoItem = todoItemRepository.save(existingTodoItem);
            return objectMapper.convertValue(updatedTodoItem, TodoItemResponse.class);
        }
        return null;
    }

    @Override
    public void deleteTodoItem(Long id) {
        todoItemRepository.deleteById(id);
    }
}
