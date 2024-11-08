package com.tp.crudmongodbdemo.service;

import com.tp.crudmongodbdemo.model.Employee;
import com.tp.crudmongodbdemo.service.serviceimpl.EmployeeServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class HtmlGeneratorService {
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private EmployeeServiceimpl employeeServiceimpl;

    public void generateHtmlFile() throws IOException {
        List<Employee> employees = employeeServiceimpl.getEmployee();
        Context context = new Context();
        context.setVariable("employees", employees);

        String htmlContent = templateEngine.process("employees", context);
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // Create the output directory if it doesn't exist
        }

        File outputFile = new File(outputDir, "employees.html");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(htmlContent);
        }

    }

    public Path getHtmlFilePath() {
        return Paths.get("output/employees.html");
    }
}


