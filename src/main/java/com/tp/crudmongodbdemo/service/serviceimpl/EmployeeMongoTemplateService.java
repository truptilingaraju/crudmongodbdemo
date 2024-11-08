package com.tp.crudmongodbdemo.service.serviceimpl;

import com.tp.crudmongodbdemo.dto.Employeedto;
import com.tp.crudmongodbdemo.model.Employee;
import com.tp.crudmongodbdemo.service.EmployeeService;
import com.tp.crudmongodbdemo.util.EmployeeSalaryAggregationResult;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeMongoTemplateService implements EmployeeService {

    private final MongoTemplate mongoTemplate;
    private final ModelMapper mapper;


    @Override
    public Employee  createEmployee(Employeedto employeedto) {

        Employee emp= mapper.map(employeedto, Employee.class);
        return mongoTemplate.save(emp);
    }

    @Override
    public List<Employee> getEmployee() {
        return mongoTemplate.findAll(Employee.class);
    }

    @Override
    public Employee getEmployeeById(String id) {

        // 1st way to find by id using query object and findone method.
//        Query query=new Query();
//        query.addCriteria(Criteria.where("id").is(id));
//
//        Employee employee=mongoTemplate.findOne(query,Employee.class);

        //2nd way we can use findById method.
        Employee employee= mongoTemplate.findById(id,Employee.class);
        return employee;

    }

    @Override
    public String deleteEmployee(String id) {
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(id));

        mongoTemplate.remove(query,Employee.class);
        return "Employee Deleted Successfully";
    }

    @Override
    public String updateEmployee(Employeedto empdto) {
        Employee emp= mapper.map(empdto, Employee.class);
        mongoTemplate.save(emp);
        return "employee updated successfully";
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {

        Query query=new Query();
        query.addCriteria(Criteria.where("empName").is(name));

        return  mongoTemplate.find(query,Employee.class);
    }

    @Override
    public List<Employee> getEmployeeBySalaryGreaterThan(long salary) {
        Query query=new Query();
        query.addCriteria(Criteria.where("salary").gt(salary));
        return  mongoTemplate.find(query,Employee.class);
    }

    @Override
    public List<Employee> getEmployeeBySalaryLessThan(long salary) {
        Query query=new Query();
        query.addCriteria(Criteria.where("salary").lt(salary));
        return  mongoTemplate.find(query,Employee.class);
    }

    @Override
    public List<Employee> getEmployeeBySalaryBetween(long salary1, long salary2) {
        Query query=new Query();
        query.addCriteria(Criteria.where("salary").gte(salary1).lte(salary2));
        return  mongoTemplate.find(query,Employee.class);
    }

    @Override
    public List<EmployeeSalaryAggregationResult> gettotalSalaryOfEmployeesMatchedByNameAndGroupedByLocation(String name, String location) {
        return null;
    }
}
