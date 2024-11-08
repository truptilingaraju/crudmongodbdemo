package com.tp.crudmongodbdemo.model;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Department {
    private String id;
    private String name;
    private Integer noOfEmployees;
}
