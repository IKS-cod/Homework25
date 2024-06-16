package Homework25.service;

import Homework25.exception.EmployeeAlreadyAddedException;
import Homework25.exception.EmployeeNotFoundException;
import Homework25.model.Employee;
import Homework25.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.function.Predicate.isEqual;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    private final EmployeeService employeeService = new EmployeeService(employeeRepository);

    @Test
    void addEmployeeTest() {
        Employee expected = new Employee("Олег", "Олегов", 1, 10.0);
        Employee actual = employeeService.addEmployee("Олег", "Олегов", 1, 10.0);
       assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEqualTo(employeeService.findEmployee("Олег", "Олегов", 1));
    }

    @Test
    void addEmployeeWithExceptionTest() {
        boolean exceptionThrown = false;
        employeeService.addEmployee("Олег", "Олегов", 1, 10.0);
        try {
            employeeService.addEmployee("Олег", "Олегов", 1, 10.0);
        } catch (EmployeeAlreadyAddedException e) {
            exceptionThrown = true;
        }
        Assertions.assertTrue(exceptionThrown);
    }

    @Test
    void removeEmployeeTest() {
        Employee expected = new Employee("Олег", "Олегов", 1);
        employeeService.addEmployee("Олег", "Олегов", 1, 10.0);
        Employee actual = employeeService.removeEmployee("Олег", "Олегов", 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void removeEmployeeWithExceptionTest() {
        boolean exceptionThrown = false;
        employeeService.addEmployee("Олег", "Олегов", 1, 10.0);
        try {
            employeeService.removeEmployee("Олег", "Петров", 1);
        } catch (EmployeeNotFoundException e) {
            exceptionThrown = true;
        }
        Assertions.assertTrue(exceptionThrown);
    }

    @Test
    void findEmployeeTest() {
        Employee expected = new Employee("Олег", "Олегов", 1);
        employeeService.addEmployee("Олег", "Олегов", 1, 10.0);
        Employee actual = employeeService.findEmployee("Олег", "Олегов", 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findEmployeeWithExceptionTest() {
        boolean exceptionThrown = false;
        employeeService.addEmployee("Олег", "Олегов", 1, 10.0);
        try {
            employeeService.findEmployee("Олег", "Петров", 1);
        } catch (EmployeeNotFoundException e) {
            exceptionThrown = true;
        }
        Assertions.assertTrue(exceptionThrown);
    }

    @Test
    void findAllTest() {
        Employee employee = new Employee("Олег", "Олегов", 1, 10.0);
        Employee employee1 = new Employee("Олег", "Петров", 1, 10.0);
        Employee employee2 = new Employee("Олег", "Сидоров", 1, 10.0);
        List<Employee> expected = new ArrayList<>();
        expected.add(employee);
        expected.add(employee1);
        expected.add(employee2);
        employeeService.addEmployee("Олег", "Олегов", 1, 10.0);
        employeeService.addEmployee("Олег", "Петров", 1, 10.0);
        employeeService.addEmployee("Олег", "Сидоров", 1, 10.0);
        List<Employee> actual = employeeService.findAll();
        Assertions.assertIterableEquals(expected, actual);
    }

}