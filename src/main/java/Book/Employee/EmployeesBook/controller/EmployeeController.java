package Book.Employee.EmployeesBook.controller;

import Book.Employee.EmployeesBook.model.Employee;
import Book.Employee.EmployeesBook.model.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int departmentId,
                                @RequestParam int salary) {
        return service.add(firstName, lastName, departmentId, salary);
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName) {
        return service.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        return service.find(firstName, lastName);
    }

    @GetMapping("/all")
    public List<Employee> findAll() {
        return service.findAll();
    }





}
