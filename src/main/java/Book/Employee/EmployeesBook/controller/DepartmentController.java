package Book.Employee.EmployeesBook.controller;

import Book.Employee.EmployeesBook.model.Employee;
import Book.Employee.EmployeesBook.model.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max")
    public Employee max(@RequestParam int departmentId) {
        return departmentService.getMaxSalary(departmentId);
    }

    @GetMapping("/min")
    public Employee min(@RequestParam int departmentId) {
        return departmentService.getMinSalary(departmentId);
    }

    @GetMapping(value = "/all-dept")
    public List<Employee> all(@RequestParam int departmentId) {
        return departmentService.getByDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> all() {
        return departmentService.groupByDepartment();
    }
}
