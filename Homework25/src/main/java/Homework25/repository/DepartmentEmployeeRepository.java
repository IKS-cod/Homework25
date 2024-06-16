package Homework25.repository;

import Homework25.exception.EmployeeNotFoundDepartmentException;
import Homework25.exception.EmployeeNotFoundException;
import Homework25.model.Employee;
import Homework25.service.EmployeeService;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Repository
public class DepartmentEmployeeRepository {
    private EmployeeService employeeService;

    public DepartmentEmployeeRepository(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public Collection<Employee> findEmployeesByDepartment(Integer departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId().equals(departmentId))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> findEmployeesAllDepartment() {
        return employeeService.findAll().stream()
                .collect(groupingBy(Employee::getDepartmentId));

    }

    public Double findMaxSalaryInDepartment(Integer departmentId) {
        Double max = Double.MIN_VALUE;
        int count =0;
        for (Employee employee : employeeService.findAll()) {
            if (employee.getDepartmentId().equals(departmentId)) {
                if (employee.getSalary() > max) {
                    max = employee.getSalary();
                    count++;
                }
            }
        }
        if (count == 0) {
            throw new EmployeeNotFoundException();
        }
        return max;
    }

    public Double findMinSalaryInDepartment(Integer departmentId) {
        Double min = Double.MAX_VALUE;
        int count =0;
        for (Employee employee : employeeService.findAll()) {
            if (employee.getDepartmentId().equals(departmentId)) {
                if (employee.getSalary() < min) {
                    min = employee.getSalary();
                    count++;
                }
            }
        }
        if (count == 0) {
            throw new EmployeeNotFoundException();
        }
        return min;
    }

    public Double findSumSalaryInDepartment(Integer departmentId) {
        Double sum = 0.0;
        for (Employee employee : employeeService.findAll()) {
            if (employee.getDepartmentId().equals(departmentId)) {
                sum = sum + employee.getSalary();
            }
        }
        return sum;
    }
}

