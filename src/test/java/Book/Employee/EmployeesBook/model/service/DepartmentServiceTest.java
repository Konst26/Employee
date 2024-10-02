package Book.Employee.EmployeesBook.model.service;

import Book.Employee.EmployeesBook.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee("Ivan", "Ivanov", 1, 1000),
                new Employee("Petr", "Petrov", 2, 1500),
                new Employee("Dima", "Sidorov", 2, 2000)
        ));
        when(employeeService.findAll()).thenReturn(employees);
    }

    @Test
    void testMaxSalary() {
        assertEquals(1000, departmentService.getMaxSalary(1).getSalary());
        assertNull(departmentService.getMaxSalary(-1));
    }

    @Test
    void testMinSalary() {
        assertEquals(1500, departmentService.getMinSalary(2).getSalary());
        assertNull(departmentService.getMaxSalary(-1));
    }

    @Test
    void testByDepartment() {
        assertThat(departmentService.getByDepartment(2)).containsExactly(
                new Employee("Petr", "Petrov", 2, 1500),
                new Employee("Dima", "Sidorov", 2, 2000));

        assertThat(departmentService.getByDepartment(1)).containsExactly(
                new Employee("Ivan", "Ivanov", 1, 1000));
    }

    @Test
    void testGroupByDepartment() {
        Map<Integer, List<Employee>> actual = departmentService.groupByDepartment();
        assertThat(actual).isEqualTo(
                Map.of
                        (1, List.of(new Employee("Ivan", "Ivanov", 1, 1000)),
                                2, List.of(new Employee("Petr", "Petrov", 2, 1500), new Employee("Dima", "Sidorov", 2, 2000))
                        ));
    }
}