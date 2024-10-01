package Book.Employee.EmployeesBook.model.service;

import Book.Employee.EmployeesBook.exception.EmployeeNotFoundException;
import Book.Employee.EmployeesBook.exception.exception.EmployeeAlreadyAddedException;
import Book.Employee.EmployeesBook.exception.exception.exception.EmployeeStorageIsFullException;
import Book.Employee.EmployeesBook.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    void testAdd() {
        assertThat(employeeService.findAll()).size().isEqualTo(3);
        var emp = employeeService.add("Test", "test test", 1, 1000);
        assertThat(emp.getFirstName()).isEqualTo("Test");
        assertThat(emp.getLastName()).isEqualTo("test test");
        assertThat(emp.getDepartmentId()).isEqualTo(1);
        assertThat(emp.getSalary()).isEqualTo(1000);

        assertThat(employeeService.findAll()).size().isEqualTo(4);
        assertThat(employeeService.findAll()).contains(emp);

    }

    @Test
    void testAddAlreadyExists() {
        var emp = employeeService.add("Test", "test test", 1, 1000);
        assertThat(emp).isNotNull();
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add("Test", "test test", 1, 1000));
    }

    @Test
    void testAddMaxEmployee() {
        employeeService.add("Test1", "test test1", 1, 1000);
        employeeService.add("Test3", "test test3", 3, 1000);
        employeeService.add("Test2", "test test2", 2, 1000);
        employeeService.add("Test5", "test test5", 3, 1000);
        employeeService.add("Test6", "test test6", 4, 1000);
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.add("Test4", "test test4", 4, 4000));
    }

    @Test
    void testFindEmployee() {
        employeeService.add("Test1", "test test1", 1, 1000);
        var actual = employeeService.find("Test1", "test test1");

        assertThat(actual).isNotNull();
        assertThat(actual.getFirstName()).isEqualTo("Test1");
        assertThat(actual.getLastName()).isEqualTo("test test1");
        assertThat(actual.getDepartmentId()).isEqualTo(1);
        assertThat(actual.getSalary()).isEqualTo(1000);
    }

    @Test
    void testNotFound() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("NotFound", "notFound"));
    }

    @Test
    void testFindAll(){
        var expected = List.of(
                new Employee("Ivan", "Ivanov", 1,1000),
                new Employee("Petr", "Petrov", 2, 1500),
                new Employee("Dima", "Sidorov", 2, 2000),
                new Employee("Test4", "test test4", 4, 4000),
                new Employee("Test5", "test test5", 5, 4000),
                new Employee("Test6", "test test6", 6, 4000),
                new Employee("Test7", "test test7", 7, 4000),
                new Employee("Test8", "test test8", 8, 4000));

        employeeService.add("Test4", "test test4", 4, 4000);
        employeeService.add("Test5", "test test5", 5, 4000);
        employeeService.add("Test6", "test test6", 6, 4000);
        employeeService.add("Test7", "test test7", 7, 4000);
        employeeService.add("Test8", "test test8", 8, 4000);

        assertThat(employeeService.findAll()).containsExactlyElementsOf(expected);
    }

    @Test
    void testRemoveEmployee() {
        assertThat(employeeService.remove("Ivan", "Ivanov")).isTrue();
        assertThat(employeeService.remove("NOTFOUND", "NOTFOUND")).isFalse();
    }






}
