package com.careerdevs.Intro.employees;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private Map<Long, Employee> employees = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong();

//    private final EmployeeRepository repository;
//
//    public EmployeeController (EmployeeRepository repository) {
//        this.repository = repository;
//    }

    public EmployeeController() {
        Long id = idCounter.incrementAndGet();
        employees.put(id, new Employee(id,"Sergio", "Abyss starer", 36));
    }

    /*
    CRUD
    Create - create employee
    Read - get one employee by id / get all employees
    Update - update one employee by id
    Destroy - delete one employee by id
     */

    @GetMapping
    public List<Employee> all() {
        return new ArrayList<Employee>(employees.values());
     }

     @PostMapping
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        Long id = idCounter.incrementAndGet();
        newEmployee.setId(id);
        employees.put(id, newEmployee);
        return newEmployee;
     }

     @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employees.get(id);
     }
}
