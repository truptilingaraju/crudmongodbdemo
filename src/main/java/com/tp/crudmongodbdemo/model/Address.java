package com.tp.crudmongodbdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Address {
    private String id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
