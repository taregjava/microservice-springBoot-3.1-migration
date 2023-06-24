package com.halfacode.departmentservice.client;
import com.halfacode.departmentservice.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {


    @GetExchange("/employee/department/{departmentId}")
    public List<Employee> findDepartment(@PathVariable("departmentId") Long departmentId);
}
