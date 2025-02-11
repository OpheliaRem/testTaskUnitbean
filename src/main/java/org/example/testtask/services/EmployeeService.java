package org.example.testtask.services;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.EmployeeDto;
import org.example.testtask.model.Employee;
import org.example.testtask.repositories.EmployeeRepository;
import org.example.testtask.security.JwtAuthenticationDto;
import org.example.testtask.security.RefreshTokenDto;
import org.example.testtask.security.UserCredentialsDto;
import org.example.testtask.security.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final PasswordEncoder passwordEncoder;
    private EmployeeRepository repository;
    private PasswordEncoder encoder;
    private JwtService jwtService;

    public JwtAuthenticationDto signIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        Employee employee = findByCredentials(userCredentialsDto);
        return jwtService.generateAuthToken(employee.getUsername());
    }

    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
        String refreshToken = refreshTokenDto.getRefreshToken();

        if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            Employee employee = findByUsername(jwtService.getEmailFromToken(refreshToken));
            return jwtService.refreshBaseToken(employee.getUsername(), refreshToken);
        }
        throw new AuthenticationException("Invalid refresh token");
    }

    public ResponseEntity<String> createEmployee(EmployeeDto employeeDto) {
        var employee = new Employee(
                employeeDto.getId(),
                employeeDto.getUsername(),
                employeeDto.getPassword()
        );

        employee.setPassword(encoder.encode(employee.getPassword()));

        repository.save(employee);

        return new ResponseEntity<>("employee was added", HttpStatus.CREATED);
    }

    private Employee findByCredentials(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        Optional<Employee> optionalEmployee = repository
                .findByUsername(userCredentialsDto.getUsername());

        if (optionalEmployee.isEmpty()) {
            throw new AuthenticationException("Username is not correct");
        }

        Employee employee = optionalEmployee.get();

        if (!passwordEncoder
                .matches(userCredentialsDto.getPassword(), employee.getPassword())) {
            throw new AuthenticationException("Password is not correct");
        }

        return employee;
    }

    private Employee findByUsername(String username) throws Exception {
        return repository.findByUsername(username).orElseThrow(
                () -> new Exception(String.format("User with username %s not found", username))
        );
    }
}
