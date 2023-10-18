package com.example.ToDoList.controller;


import com.example.ToDoList.payloads.CustomAPIResponse;
import com.example.ToDoList.response.TodoItemRequest;
import com.example.ToDoList.response.TodoItemResponse;
import com.example.ToDoList.service.TodoItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo-items")
@Api(tags = "Todo Items") //@Api: Annotate your controller class with this annotation to provide a description for the API.
public class TodoItemController {

    @Autowired
    private  TodoItemService todoItemService;



    //@ApiParam: Annotate method parameters with this annotation to provide additional information about the parameters.
    //@ApiResponse: Annotate your API methods with this annotation to provide information about the possible responses.

    @PostMapping
    @ApiOperation("Create a new todo item") //@ApiOperation: Annotate your API methods with this annotation to provide a description for each endpoint.
    public ResponseEntity<TodoItemResponse> createTodoItem(
            @ApiParam(value = "Todo item details", required = true) @RequestBody TodoItemRequest todoItemRequest) {
        TodoItemResponse createdTodoItem = todoItemService.createTodoItem(todoItemRequest);
         return new ResponseEntity(new CustomAPIResponse("Task Added Succesful",true), HttpStatus.OK);
        //return new ResponseEntity<>(createdTodoItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a todo item by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the todo item"),
            @ApiResponse(code = 404, message = "Todo item not found")
    })
    public ResponseEntity<TodoItemResponse> getTodoItemById(
            @ApiParam(value = "ID of the todo item", required = true) @PathVariable Long id) {
        TodoItemResponse todoItemResponse = todoItemService.getTodoItemById(id);
        if (todoItemResponse != null) {
            return new ResponseEntity<>(todoItemResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    @GetMapping
//    @ApiOperation("Get all todo items")
//    public ResponseEntity<List<TodoItemResponse>> getAllTodoItems() {
//        List<TodoItemResponse> todoItems = todoItemService.getAllTodoItems();
//        List<TodoItemResponse> todoItemResponses = new ArrayList<>();
//        for (TodoItemResponse todoItem : todoItems) {
//            TodoItemResponse todoItemResponse = objectMapper.convertValue(todoItem, TodoItemResponse.class);
//            todoItemResponses.add(todoItemResponse);
//        }
//        return new ResponseEntity<>(todoItemResponses, HttpStatus.OK);
//    }

    @GetMapping
    @ApiOperation("Get all todo items")
    public ResponseEntity<List<TodoItemResponse>> getAllTodoItems() {
        List<TodoItemResponse> todoItems = todoItemService.getAllTodoItems();
//        List<TodoItemResponse> todoItemResponses = new ArrayList<>();
//        for (TodoItemResponse todoItem : todoItems) {
//            TodoItemResponse todoItemResponse = objectMapper.convertValue(todoItem, TodoItemResponse.class);
//            todoItemResponses.add(todoItemResponse);
//        }
        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a todo item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the todo item"),
            @ApiResponse(code = 404, message = "Todo item not found")
    })
    public ResponseEntity<TodoItemResponse> updateTodoItem(
            @ApiParam(value = "ID of the todo item", required = true) @PathVariable Long id,
            @ApiParam(value = "Updated todo item details", required = true) @RequestBody TodoItemRequest todoItemRequest) {
        TodoItemResponse updatedTodoItem = todoItemService.updateTodoItem(id, todoItemRequest);
        if (updatedTodoItem != null) {
          //  return new ResponseEntity<>(updatedTodoItem, HttpStatus.OK);
            return new ResponseEntity(new CustomAPIResponse("Task Updated Succesful",true), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a todo item")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the todo item"),
            @ApiResponse(code = 404, message = "Todo item not found")
    })
    public ResponseEntity<Void> deleteTodoItem(
            @ApiParam(value = "ID of the todo item", required = true) @PathVariable Long id) {
        todoItemService.deleteTodoItem(id);
     //   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity(new CustomAPIResponse("Task Deleted Succesfuylly",true), HttpStatus.OK );
    }

}
