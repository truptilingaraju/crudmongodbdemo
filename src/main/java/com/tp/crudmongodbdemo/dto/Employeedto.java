package com.tp.crudmongodbdemo.dto;

import com.tp.crudmongodbdemo.model.Address;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employeedto {

    @NotBlank(message="Employee Name should not be blank")
    @Size(max = 30,min = 4, message="name should be min 4 and max 30")
//    @Pattern(regexp = "[a-zA-Z0-9_]{4,30}$")
    private String empName;
    private String location;

//    @NotNull
//    @Min(value = 20000, message = "min salary should be 20k")
//    @Max(value = 2000000, message = "max salary should be 20 lakhs")
    private long salary;
    private Address address;

    //@Indexed(unique = true) //we can able to make that particular field unique
}
