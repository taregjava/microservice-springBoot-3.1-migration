package com.halfacode.departmentservice.controller;

import com.halfacode.departmentservice.client.EmployeeClient;
import com.halfacode.departmentservice.model.Department;
import com.halfacode.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER= LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department){
        LOGGER.info("DEPARTMENT ADD");
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll(){
        LOGGER.info("DEPARTMENT FIND ALL");
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){
        LOGGER.info("DEPARTMENT findById");
        return repository.findById(id);
    }
    @GetMapping("/with-employee")
    public List<Department> findAllWithEmployees(){
        LOGGER.info("DEPARTMENT FIND ALL");
        List<Department> departments= repository.findAll();
        departments.forEach(department ->
                department.setEmployees(employeeClient.findDepartment(department.getId())));

        return departments;
    }
}
