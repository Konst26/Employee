package Book.Employee.EmployeesBook.exception.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends RuntimeException {

    public EmployeeStorageIsFullException() {
        System.out.println("Список заполнен");
    }
}
