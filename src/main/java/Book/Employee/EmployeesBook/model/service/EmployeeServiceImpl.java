package Book.Employee.EmployeesBook.model.service;

import Book.Employee.EmployeesBook.exception.EmployeeNotFoundException;
import Book.Employee.EmployeesBook.exception.exception.EmployeeAlreadyAddedException;
import Book.Employee.EmployeesBook.exception.exception.exception.EmployeeStorageIsFullException;
import Book.Employee.EmployeesBook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final int MAX_EMPLOYEES = 8;

    private final List<Employee> employees = new ArrayList<>();


    @Override
    public Employee add(String firstName, String lastName, int departmentID, int salary) {
        Employee employee = new Employee(firstName, lastName,departmentID, salary);
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        } else if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public String remove(String firstName, String lastName) {
        boolean removed = employees.removeIf(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName));
        if (removed) {
            return "Сотрудник " + firstName + " " + lastName + "удален.";
        }
        return "Сотрудник " + firstName + " " + lastName + "не найден!";
        }

    @Override
    public Employee find(String firstName, String lastName) {
    return employees.stream()
            .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
            .findFirst()
            .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<Employee> findAll() {
        return employees;       //возвращает неизменяемую копию//
    }
}