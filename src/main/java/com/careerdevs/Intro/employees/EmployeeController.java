package com.careerdevs.Intro.employees;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;
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
        id = idCounter.incrementAndGet();
        employees.put(id, new Employee(id,"Maria", "Manager", 25));
    }

    // CRUD

    @GetMapping
    public List<Employee> all() {
        return new ArrayList<Employee>(employees.values());
     }

     // Create  - create new employee
     @PostMapping
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        Long id = idCounter.incrementAndGet();
        newEmployee.setId(id);
        employees.put(id, newEmployee);
        return newEmployee;
     }

     // Read - get one employee by id / get all employees
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employees.get(id);
    }

    // Update - update one employee by id
    // sometimes use PUT modern systems can POST
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updateData) {
        Employee emp = employees.get(id);

        if (emp == null) {
            return emp;
        }

        if(updateData.getName() != null) {
            emp.setName(updateData.getName());
        }
        if(updateData.getRole() != null) {
            emp.setRole(updateData.getRole());
        }
        if(updateData.getAge() != null) {
            emp.setAge(updateData.getAge());
        }
        return emp;
    }

    // Destroy - delete one employee by id
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employees.remove(id);
    }

}
