package com.tp.crudmongodbdemo.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "employee")
@Data
@Builder

public class Employee {

    @Id
    private String id;
//    @Field(name = "employee_name")
    private String empName;
    private String location;
    private long salary;

    //using DB ref also we can store the data, or we can directly use address object by sending through postman
    //without need of creating any new document for Address.
    @DBRef
    private Address address;


    public Employee() {
    }

    public Employee(String id, String empName, String location, long salary, Address address) {
        this.id = id;
        this.empName = empName;
        this.location = location;
        this.salary = salary;
        this.address = address;
    }
}
