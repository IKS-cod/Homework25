package Homework25.service;

import Homework25.exception.EmployeeNotFoundException;
import Homework25.model.Employee;
import Homework25.repository.DepartmentEmployeeRepository;
import Homework25.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    DepartmentEmployeeRepository departmentEmployeeRepository;
    private final List<Employee> employees = List.of(
            new Employee("Олег", "Олегов", 1, 10.0),
            new Employee("Александр", "Александров", 1, 12.0),
            new Employee("Иван", "Олегов", 1, 40.0),
            new Employee("Денис", "Денисов", 2, 30.0),
            new Employee("Сергей", "Сергеев", 2, 20.0),
            new Employee("Петр", "Петров", 3, 15.0)
    );

    @BeforeEach
    public void beforeEach() {
        when(employeeService.findAll()).thenReturn(employees);
    }

    @Test
    void findEmployeesByDepartmentTest() {
        assertThat(departmentEmployeeRepository.findEmployeesByDepartment(1))
                .containsExactlyInAnyOrder(
                        new Employee("Олег", "Олегов", 1, 10.0),
                        new Employee("Александр", "Александров", 1, 12.0),
                        new Employee("Иван", "Олегов", 1, 40.0)
                );
    }

    @Test
    void findMaxSalaryInDepartmentTest() {
        assertThat(departmentEmployeeRepository.findMaxSalaryInDepartment(1))
                .isEqualTo(40.0);
    }

    @Test
    void findMaxSalaryInDepartmentNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentEmployeeRepository.findMaxSalaryInDepartment(4));

    }


    @Test
    void findMinSalaryInDepartmentTest() {
        assertThat(departmentEmployeeRepository.findMinSalaryInDepartment(1))
                .isEqualTo(10.0);
    }

    @Test
    void findMinSalaryInDepartmentNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentEmployeeRepository.findMinSalaryInDepartment(4));
    }

    @Test
    void findSumSalaryInDepartmentTest() {
        assertThat(departmentEmployeeRepository.findSumSalaryInDepartment(2))
                .isEqualTo(50.0);
    }

    @Test
    void findSumSalaryInDepartmentNegativeTest() {
        assertThat(departmentEmployeeRepository.findSumSalaryInDepartment(4))
                .isEqualTo(0.0);
    }


    @Test
    void findEmployeesAllDepartmentTest() {
        assertThat(departmentEmployeeRepository.findEmployeesAllDepartment())
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                1, List.of(new Employee("Олег", "Олегов", 1, 10.0), new Employee("Александр", "Александров", 1, 12.0), new Employee("Иван", "Олегов", 1, 40.0)),
                                2, List.of(new Employee("Денис", "Денисов", 2, 30.0), new Employee("Сергей", "Сергеев", 2, 20.0)),
                                3, List.of(new Employee("Петр", "Петров", 3, 15.0))
                        )
                );
    }
}