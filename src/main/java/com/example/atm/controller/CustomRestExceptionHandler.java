package com.example.atm.controller;

import com.example.atm.exception.DataNotSaved;
import com.example.atm.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler(value = DataNotSaved.class)
    ResponseEntity<ErrorModel> handleValidateError(DataNotSaved validateException) {

        ErrorModel errorModel = new ErrorModel();
        errorModel.setMessage("Data not saved");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorModel);
    }

}
