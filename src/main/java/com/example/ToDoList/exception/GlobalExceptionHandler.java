//package com.example.ToDoList.exception;
//
//import com.example.ToDoList.payloads.CustomAPIResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class GlobalExceptionHandler {
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<CustomAPIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
//        String message = ex.getMessage();
//        CustomAPIResponse apiResponse = new CustomAPIResponse(message,false);
//        return new ResponseEntity<CustomAPIResponse>(apiResponse, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
//
//        //if error at name along with name we need error message so Map of strings
//        //similarily at each field  each feild and error message in key value pairs
//        Map<String,String> resp=new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error)->{
//            String fieldName= ((FieldError)error).getField();
//            String message = error.getDefaultMessage();
//            resp.put(fieldName,message);
//
//        });
//
//        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
//    }
//}
