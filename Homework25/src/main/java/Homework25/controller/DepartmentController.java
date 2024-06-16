package Homework25.controller;

import Homework25.model.Employee;
import Homework25.service.DepartmentService;
import Homework25.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> findEmployeesAllDepartment() {
        return departmentService.findEmployeesAllDepartment();
    }

    @GetMapping("/{departmentId}/employees")
    public Collection<Employee> findEmployeesByDepartment(@PathVariable Integer departmentId) {
        return departmentService.findEmployeesByDepartment(departmentId);
    }

    @GetMapping("/{departmentId}/salary/sum")
    public Double findSumSalaryInDepartment(@PathVariable Integer departmentId) {
        return departmentService.findSumSalaryInDepartment(departmentId);
    }

    @GetMapping("/{departmentId}/salary/max")
    public Double findMaxSalaryInDepartment(@PathVariable Integer departmentId) {
        return departmentService.findMaxSalaryInDepartment(departmentId);
    }

    @GetMapping("/{departmentId}/salary/min")
    public Double findMinSalaryInDepartment(@PathVariable Integer departmentId) {
        return departmentService.findMinSalaryInDepartment(departmentId);
    }
}
