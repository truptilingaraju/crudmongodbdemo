package com.tp.crudmongodbdemo.util;

import com.tp.crudmongodbdemo.model.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private static ResponseStructure initResponseStructure(){
        return new ResponseStructure();
    }

    public static ResponseEntity<ResponseStructure> getCreatedResponse(Object response){
        ResponseStructure responseStructure = initResponseStructure();
        responseStructure.setHttpStatus(HttpStatus.CREATED);
        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        responseStructure.setResponse(response);
        return ResponseEntity.status(responseStructure.getStatusCode()).body(responseStructure);
    }

    public static ResponseEntity<ResponseStructure> getOkResponse(Object response){
        ResponseStructure responseStructure = initResponseStructure();
        responseStructure.setHttpStatus(HttpStatus.OK);
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setResponse(response);
        return ResponseEntity.status(responseStructure.getStatusCode()).body(responseStructure);
    }

    public static ResponseEntity<ResponseStructure> getBadRequestResponse(String message){
        ResponseStructure responseStructure = initResponseStructure();
        responseStructure.setHttpStatus(HttpStatus.BAD_REQUEST);
        responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
        responseStructure.setResponse(message);
        return ResponseEntity.status(responseStructure.getStatusCode()).body(responseStructure);
    }


}
