package Homework25.repository;

import Homework25.exception.EmployeeAlreadyAddedException;
import Homework25.exception.EmployeeNotFoundException;
import Homework25.model.Employee;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final List<Employee> employeeRepository=new ArrayList<>();

    public Employee removeEmployee(String firstName, String lastName, Integer departmentId) {
        Employee employee = new Employee(firstName,lastName, departmentId);
        if (!employeeRepository.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employeeRepository.remove(employee);
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName, Integer departmentId) {
        Employee employee = new Employee(firstName,lastName, departmentId);
        if (!employeeRepository.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> findAll() {
        return Collections.unmodifiableList(employeeRepository);
    }
    public Employee addEmployee(String firstName, String lastName, Integer departmentId, Double salary) {
        Employee employee = new Employee(firstName,lastName,departmentId,salary);
        if (employeeRepository.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeRepository.add(employee);
        return employee;
    }
}
