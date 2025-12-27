package com.example.VotingProject.in.voter.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice //indicates that this class provides global exception handling for all controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchEmpExistsException.class) //handles NoSuchEmpExistsException exceptions thrown by methods in this controller //service layer
    // @ResponseStatus(HttpStatus.NOT_FOUND) //sets the HTTP status code to 404 Not Found
    // @ResponseBody //indicates that the return value of the method will be the response body
    //better way of coding than the above commented lines is below
    public ResponseEntity<ErrorResponse> handNoSuchEmpExistsException(NoSuchEmpExistsException ex){ //method to handle NoSuchEmpExistsException
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()); //create a new ErrorResponse object
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); //return the ErrorResponse object
    }

    @ExceptionHandler(value = EmpAlreadyExistsException.class) //handles EmpAlreadyExistsException exceptions thrown by methods in this controller //service layer
    @ResponseStatus(HttpStatus.CONFLICT) //sets the HTTP status code to 409 Conflict
    @ResponseBody //indicates that the return value of the method will be the response body
    public ErrorResponse handleEmpAlreadyExistsException(EmpAlreadyExistsException ex){ //method to handle EmpAlreadyExistsException
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage()); //create a new ErrorResponse object
        return error; //return the ErrorResponse object
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) //handles MethodArgumentNotValidException exceptions thrown by methods in this controller //service layer
    public ResponseEntity<Map<String, String>> HandelValidationException(MethodArgumentNotValidException ex){ //method to handle MethodArgumentNotValidException
        Map<String, String> errors = new HashMap<>(); //create a new HashMap to store the field errors
        BindingResult bindingResult = ex.getBindingResult(); //get the BindingResult object from the exception
        List<FieldError> errorList = bindingResult.getFieldErrors(); //get the list of field errors
        for (FieldError error : errorList) {                        //iterate through the list of field errors
            errors.put(error.getField(), error.getDefaultMessage()); //add the field name and error message to the HashMap
        } 
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); //return the ErrorResponse object
    }

    @ExceptionHandler(value = Exception.class) //handles NoSuchEmpExistsException exceptions thrown by methods in this controller //service layer
    public ResponseEntity<ErrorResponse> handleException(Exception ex){ //method to handle NoSuchEmpExistsException
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()); //create a new ErrorResponse object
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); //return the ErrorResponse object
    }
}
