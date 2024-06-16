package Homework25.service;

import Homework25.exception.EmployeeAlreadyAddedException;
import Homework25.exception.EmployeeNotFoundException;
import Homework25.model.Employee;
import Homework25.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee removeEmployee(String firstName, String lastName, Integer departmentId) {
        return employeeRepository.removeEmployee(firstName,lastName, departmentId);
    }

    public Employee findEmployee(String firstName, String lastName, Integer departmentId) {
        return employeeRepository.findEmployee(firstName,lastName, departmentId);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(String firstName, String lastName, Integer departmentId, Double salary) {
        return employeeRepository.addEmployee(firstName,lastName,departmentId,salary);
    }






}
