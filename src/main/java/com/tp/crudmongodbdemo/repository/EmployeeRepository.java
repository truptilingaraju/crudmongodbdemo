package com.tp.crudmongodbdemo.repository;


import com.tp.crudmongodbdemo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findByEmpName(String name);

    //to ignore provide the fields with value as 0 for any field(string/number)
    //to get particular fields only,then provide the fields with value as 1 for any field(string/number)
    //$options:'i' is used to make case-insensitive.

    @Query(value = "{'empName' :{ '$regex':?0 , '$options':'i'}}",fields="{'salary':0}") //here fields attribute is to ignore the values of that specified field.
    List<Employee> findByEmpNameByQuery(String name);

    @Query("{empName : { $eq : ?0}}")
    List<Employee> findByEmpNameByQueryOperator(String name);

    //find list of employees with salary greater than passed value($gt =>greaterthan)
    @Query("{salary : { $gt : ?0}}")
    List<Employee> findEmployeesWithSalaryGreaterThan(long salary);

    //find list of employees with salary less than passed value($lt =>less than)
    @Query("{salary : { $lt :?0}}")
    List<Employee> findEmployeesWithSalaryLessThan(long salary);

    //find list of employees with salary between value1 and value2
    @Query("{ $and : [ {salary : { $gt : ?0}} , { salary : { $lt :?1} } ]}")
    List<Employee> findEmployeesWithSalaryBetween(long salary1, long salary2);

}
