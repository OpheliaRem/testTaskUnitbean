package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.model.Employee;
import org.example.testtask.repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository repository;
    private PasswordEncoder encoder;

    public ResponseEntity<String> createEmployee(Employee employee) {
        employee.setPassword(encoder.encode(employee.getPassword()));

        repository.save(employee);

        return new ResponseEntity<>("employee was added", HttpStatus.CREATED);
    }
}
