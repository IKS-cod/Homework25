package Homework25.service;

import Homework25.exception.EmployeeNotFoundDepartmentException;
import Homework25.model.Employee;
import Homework25.repository.DepartmentEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentService {
    private final DepartmentEmployeeRepository departmentEmployeeRepository;

    public DepartmentService(DepartmentEmployeeRepository departmentEmployeeRepository) {
        this.departmentEmployeeRepository = departmentEmployeeRepository;
    }

    public Double findMaxSalaryInDepartment(Integer departmentId) {
        return departmentEmployeeRepository.findMaxSalaryInDepartment(departmentId);
    }

    public Double findMinSalaryInDepartment(Integer departmentId) {
        return departmentEmployeeRepository.findMinSalaryInDepartment(departmentId);
    }

    public Double findSumSalaryInDepartment(Integer departmentId) {
        return departmentEmployeeRepository.findSumSalaryInDepartment(departmentId);
    }

    public Collection<Employee> findEmployeesByDepartment(Integer departmentId) {
        return departmentEmployeeRepository.findEmployeesByDepartment(departmentId);
    }

    public Map<Integer, List<Employee>> findEmployeesAllDepartment() {
        return departmentEmployeeRepository.findEmployeesAllDepartment();

    }


}
