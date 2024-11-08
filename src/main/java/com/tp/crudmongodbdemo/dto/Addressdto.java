package com.tp.crudmongodbdemo.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Addressdto {

    private String street;
    private String city;
    private String state;
    private String country;
    private String pincode;

}
