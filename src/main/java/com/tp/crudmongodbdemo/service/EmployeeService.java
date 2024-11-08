package com.tp.crudmongodbdemo.service;

import com.tp.crudmongodbdemo.dto.Employeedto;
import com.tp.crudmongodbdemo.model.Employee;
import com.tp.crudmongodbdemo.util.EmployeeSalaryAggregationResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public Employee createEmployee(Employeedto employeedto);

    public List<Employee> getEmployee();

    public Employee getEmployeeById(String id);

    public String deleteEmployee(String id);

    public String updateEmployee(Employeedto empdto);

    public List<Employee> getEmployeesByName(String name);

    public List<Employee> getEmployeeBySalaryGreaterThan(long salary);

    public List<Employee> getEmployeeBySalaryLessThan(long salary);

    public List<Employee> getEmployeeBySalaryBetween(long salary1, long salary2);

    public List<EmployeeSalaryAggregationResult> gettotalSalaryOfEmployeesMatchedByNameAndGroupedByLocation(String name, String location);
}
