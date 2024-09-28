package Book.Employee.EmployeesBook.model.service;

import Book.Employee.EmployeesBook.exception.EmployeeNotFoundException;
import Book.Employee.EmployeesBook.exception.exception.EmployeeAlreadyAddedException;
import Book.Employee.EmployeesBook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String,Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }



    @Override
    public Employee find(String firstName, String lastName) {
    Employee employee = new Employee(firstName, lastName);
    if(employees.contains(employee)) {
        return employee;
    }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableList(employees);       //возвращает неизменяемую копию//
    }
}