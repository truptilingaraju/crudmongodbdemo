package com.tp.crudmongodbdemo.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseStructure {

    private HttpStatus httpStatus;
    private int statusCode;
    private Object response;


}
