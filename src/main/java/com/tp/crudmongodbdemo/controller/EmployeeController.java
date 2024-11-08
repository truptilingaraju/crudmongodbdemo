package com.tp.crudmongodbdemo.controller;

import com.tp.crudmongodbdemo.dto.Employeedto;
import com.tp.crudmongodbdemo.model.Employee;
import com.tp.crudmongodbdemo.model.ResponseStructure;
import com.tp.crudmongodbdemo.service.HtmlGeneratorService;
import com.tp.crudmongodbdemo.service.serviceimpl.EmployeeMongoTemplateService;
import com.tp.crudmongodbdemo.service.serviceimpl.EmployeeServiceimpl;
import com.tp.crudmongodbdemo.util.EmployeeSalaryAggregationResult;
import com.tp.crudmongodbdemo.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emp")
@Validated
public class EmployeeController {

    //using mongo repository:
    private final EmployeeServiceimpl employeeServiceimpl;

    private final HtmlGeneratorService htmlGeneratorService;

    //using mongoTemplate class:
//    private  final EmployeeMongoTemplateService employeeServiceimpl;

    @Operation(description = "To save Employee Info" ,summary="Employee will be saved in the database")
    @ApiResponses(value = @ApiResponse(description = "Employee created", responseCode="201"))
    @PostMapping("/create")

    public ResponseEntity<ResponseStructure> createEmployee(@Valid @RequestBody Employeedto emp){
        Employee createdEmp = employeeServiceimpl.createEmployee(emp);
        return ResponseUtil.getCreatedResponse(createdEmp);

    }

    @Operation(description = "To get all Employees Info" ,summary="All Employees will be displayed")
    @ApiResponses(value = @ApiResponse(description = "Retrieves all Employees", responseCode="200"))
    @GetMapping("/get/employee")
    public ResponseEntity<ResponseStructure> getEmployees(){
        List<Employee> employees = employeeServiceimpl.getEmployee();
        return ResponseUtil.getOkResponse(employees);

    }

    @Operation(description = "To get Employee by id" ,summary="Employee with specified id is displayed")
    @ApiResponses(value = @ApiResponse(description = "Retrieves Employee with specific id", responseCode="200"))
    @GetMapping("/get/employeeById/{id}")
    public ResponseEntity<ResponseStructure> getEmployeeById(@PathVariable String id){
        Employee employee = employeeServiceimpl.getEmployeeById(id);
        return ResponseUtil.getOkResponse(employee);

    }

    @Operation(description = "To delete Employee with specific id" ,summary="Employee with specified id is deleted")
    @ApiResponses(value = @ApiResponse(description = "delete Employee with specific id", responseCode="200"))
    @DeleteMapping("/delete/employee")
    public ResponseEntity<ResponseStructure> deleteEmployee(@RequestParam String id){
        String deletedEmp = employeeServiceimpl.deleteEmployee(id);
        return ResponseUtil.getOkResponse(deletedEmp);

    }

    @Operation(description = "To update Employee" ,summary="Employee will be updated")
    @ApiResponses(value = @ApiResponse(description = "Employee will be updated", responseCode="200"))
    @PutMapping("/update/employee")
    public ResponseEntity<ResponseStructure> updateEmployee(@RequestBody Employeedto emp){
        String updatedEmp = employeeServiceimpl.updateEmployee(emp);
        return ResponseUtil.getOkResponse(updatedEmp);

    }

    @Operation(description = "To get Employee by Name" ,summary="Employee with specified name is displayed")
    @ApiResponses(value = @ApiResponse(description = "Retrieves Employee with specific name", responseCode="200"))
    @GetMapping("/get/employeeByName/{empName}")
    public ResponseEntity<ResponseStructure> getEmployeeByName(@PathVariable  String empName){
        List<Employee> employees = employeeServiceimpl.getEmployeesByName(empName);
        return ResponseUtil.getOkResponse(employees);
    }

    @Operation(description = "To get all Employees whose salary greater than specified " ,summary="Employees with salary greater than specified will be displayed")
    @ApiResponses(value = @ApiResponse(description = "Retrieves Employee whose salary greater than specified", responseCode="200"))
    @GetMapping("/get/employeeBySalGreaterThan/{salary}")
    public ResponseEntity<ResponseStructure> getEmployeeBySalaryGreaterThan(@PathVariable  long salary){
        List<Employee> employees = employeeServiceimpl.getEmployeeBySalaryGreaterThan(salary);
        return ResponseUtil.getOkResponse(employees);
    }

    @Operation(description = "To get Employee whose salary is less than specified" ,summary="Employees with salary less than specified will be displayed")
    @ApiResponses(value = @ApiResponse(description = "Retrieves Employee whose salary is less than specified", responseCode="200"))
    @GetMapping("/get/employeeBySalLessThan/{salary}")
    public ResponseEntity<ResponseStructure> getEmployeeBySalaryLess3Than(@PathVariable  long salary){
        List<Employee> employees = employeeServiceimpl.getEmployeeBySalaryLessThan(salary);
        return ResponseUtil.getOkResponse(employees);
    }

    @Operation(description = "To get Employee whose salary is in between specified" ,summary="Employees with salary in between specified will be displayed")
    @ApiResponses(value = @ApiResponse(description = "Retrieves Employee whose salary is in between specified", responseCode="200"))
    @GetMapping("/get/employeeBySalBetween/{salary1}/{salary2}")
    public ResponseEntity<ResponseStructure> getEmployeeBySalaryLessThan(@PathVariable  long salary1, @PathVariable long salary2){
        List<Employee> employees = employeeServiceimpl.getEmployeeBySalaryBetween(salary1, salary2);
        return ResponseUtil.getOkResponse(employees);
    }

    @GetMapping("/get/totalSalaryOfEmployeesGroupedByLocation/{name}/{location}")
    public ResponseEntity<ResponseStructure> gettotalSalaryOfEmployeesGroupedByLocation(@PathVariable String name,@PathVariable String location){
        List<EmployeeSalaryAggregationResult> employees = employeeServiceimpl.gettotalSalaryOfEmployeesMatchedByNameAndGroupedByLocation(name,location);
        return ResponseUtil.getOkResponse(employees);
    }

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeServiceimpl.getEmployee();
        model.addAttribute("employees", employees);
        return "employees";
    }

//    @GetMapping("/generateHtml")
//    public String generateHtml() {
//        try {
//            htmlGeneratorService.generateHtmlFile();
//            return "HTML file generated successfully!";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error generating HTML file.";
//        }
//    }

    @GetMapping("/generateHtml")
    public ResponseEntity<InputStreamResource> generateHtml() throws IOException {
//        List<Employee> employees = employeeServiceimpl.getEmployee();
        htmlGeneratorService.generateHtmlFile();

        Path filePath = htmlGeneratorService.getHtmlFilePath();
        System.out.println(filePath);
        try {
            InputStreamResource resource = new InputStreamResource(Files.newInputStream(filePath));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.html");
            headers.setContentType(MediaType.TEXT_HTML);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(Files.size(filePath))
                    .body(resource);

        } catch (IOException e) {
            System.out.println(" Error while generating or reading HTML file");
            return ResponseEntity.status(500).body(null);
        }
    }


}
