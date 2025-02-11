package org.example.testtask.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.testtask.model.Employee;

@Data
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String username;
    private String password;

    public EmployeeDto(Employee employee) {
        id = employee.getId();
        username = employee.getUsername();
        password = employee.getPassword();
    }
}
