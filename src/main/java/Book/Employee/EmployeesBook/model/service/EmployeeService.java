package Book.Employee.EmployeesBook.model.service;

import Book.Employee.EmployeesBook.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int departmentId, int salary);

    String remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    List<Employee> findAll();
}