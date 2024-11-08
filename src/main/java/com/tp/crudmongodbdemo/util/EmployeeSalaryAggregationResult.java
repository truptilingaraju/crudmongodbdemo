package com.tp.crudmongodbdemo.util;

public class EmployeeSalaryAggregationResult {
    private String location;
    private long totalSalary;

    // Getters and setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(long totalSalary) {
        this.totalSalary = totalSalary;
    }
}

