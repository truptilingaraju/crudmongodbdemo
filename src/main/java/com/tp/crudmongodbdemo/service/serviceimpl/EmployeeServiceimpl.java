package com.tp.crudmongodbdemo.service.serviceimpl;

import com.tp.crudmongodbdemo.dao.Addressdao;
import com.tp.crudmongodbdemo.dto.Employeedto;
import com.tp.crudmongodbdemo.model.Address;
import com.tp.crudmongodbdemo.model.Employee;
import com.tp.crudmongodbdemo.repository.EmployeeRepository;
import com.tp.crudmongodbdemo.configuration.EmailSenderService;
import com.tp.crudmongodbdemo.service.EmployeeService;
import com.tp.crudmongodbdemo.util.EmployeeSalaryAggregationResult;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceimpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper mapper;

    private final Addressdao addressdao;

    private final EmailSenderService senderService;

    private final MongoTemplate mongoTemplate;



    public Employee createEmployee(Employeedto employeedto)
    {
        Employee savedEmp=null;
            //we can use builder or else we can use model mapper also as shown below

            //using builder:
//            Employee emp = Employee.builder().empName(employeedto.getEmpName())
//                    .salary(employeedto.getSalary())
//                    .location(employeedto.getLocation())
//                    .build();

        Address address= employeedto.getAddress();
        Address address1 = addressdao.createAddress(address);
    employeedto.setAddress(address1);
        //using model mapper:
            Employee emp= mapper.map(employeedto, Employee.class);
            //saving Address
            savedEmp = employeeRepository.save(emp);
            //sending email
        senderService.sendEmail("javeedjune7@gmail.com", "CRUDMONGODBDEMO" ,
				"This is Test Email from CRUDMONGODBDEMO and Employee "+savedEmp.getEmpName()+" created successfully");
        System.out.println("Email sent successfully");

        return savedEmp;
    }

    public List<Employee> getEmployee(){

        List<Employee> empList = new ArrayList<>();
        try{
            empList = employeeRepository.findAll();

        }
        catch (Exception e)
        {
            //error statement
        }
        return empList;
    }

    public String deleteEmployee(String id) {

        try{
            //we can directly use deleteById method as below
//           employeeRepository.deleteById(id);

            Optional<Employee>  optemp = employeeRepository.findById(id);
            if (optemp.isPresent())
            {
                Employee emp=optemp.get();
                employeeRepository.delete(emp);
            }
        }catch(Exception e){
           //error statement
        }
        return "Employee Deleted Successfully";
    }

    public String updateEmployee(Employeedto empdto) {
        try {
            // Using ModelMapper to map EmployeeDto to Employee
            Employee updtemp = mapper.map(empdto, Employee.class);

            // Logging the mapped object for debugging
            System.out.println("Mapped Employee Object: " + updtemp);

            // Saving the updated employee to the repository
            Employee updatedEmployee = employeeRepository.save(updtemp);

            // Logging the updated employee
            System.out.println("Updated Employee: " + updatedEmployee);

        } catch(Exception e) {
            // Log the error for debugging
            System.err.println("Error occurred while updating employee: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for detailed error analysis

            // You might want to throw the exception or handle it appropriately based on your application logic
            // throw new RuntimeException("Failed to update employee", e);
        }
        return "Employee Updated Successfully";
    }

    public List<Employee> getEmployeesByName(String name) {
        List<Employee> employees=null;
        try{
            //using repository method:
//            employees=employeeRepository.findByEmpName(name);

            //using query method:(index)
//            employees=employeeRepository.findByEmpNameByQuery(name);

            //using query operator:($eq)
            employees=employeeRepository.findByEmpNameByQueryOperator(name);

        }catch(Exception e){
            //error statement
        }
        System.out.println(employees);
        return employees;
    }


    public List<Employee> getEmployeeBySalaryGreaterThan(long salary) {

        List<Employee> employees = employeeRepository.findEmployeesWithSalaryGreaterThan(salary);
        return employees;
    }

    public List<Employee> getEmployeeBySalaryLessThan(long salary) {

        List<Employee> employees = employeeRepository.findEmployeesWithSalaryLessThan(salary);
        return employees;
    }

    public List<Employee> getEmployeeBySalaryBetween(long salary1, long salary2) {

        List<Employee> employees = employeeRepository.findEmployeesWithSalaryBetween(salary1, salary2);
        return employees;
    }

    public Employee getEmployeeById(String id) {
        Employee emp= employeeRepository.findById(id).orElseThrow(()->new NoSuchElementException("employee details not found"));
        return  emp;
    }

    public List<EmployeeSalaryAggregationResult> gettotalSalaryOfEmployeesMatchedByNameAndGroupedByLocation(String name, String location) {
        // Match operation to filter the employees by their location
        MatchOperation matchOperation = match(Criteria.where("location").is(location));
        // Group operation to sum the salary based on location
        GroupOperation groupOperation = group("location")
                .sum("salary").as("totalSalary");

        Aggregation aggregation = newAggregation(matchOperation, groupOperation);

        AggregationResults<EmployeeSalaryAggregationResult> results = mongoTemplate.aggregate(aggregation, "employee", EmployeeSalaryAggregationResult.class);
        return results.getMappedResults();
    }
}
