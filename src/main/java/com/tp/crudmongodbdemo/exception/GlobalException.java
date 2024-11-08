package com.tp.crudmongodbdemo.exception;

import com.tp.crudmongodbdemo.model.ResponseStructure;
import com.tp.crudmongodbdemo.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseStructure> catchNoSuchElementException(NoSuchElementException exception) {
        return ResponseUtil.getBadRequestResponse(exception.getMessage());
    }
}
