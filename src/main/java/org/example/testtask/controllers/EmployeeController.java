package org.example.testtask.controllers;

import lombok.AllArgsConstructor;
import org.example.testtask.dtos.EmployeeDto;
import org.example.testtask.security.JwtAuthenticationDto;
import org.example.testtask.security.RefreshTokenDto;
import org.example.testtask.security.UserCredentialsDto;
import org.example.testtask.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    EmployeeService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody EmployeeDto employeeDto) {
        return service.createEmployee(employeeDto);
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationDto> signIn(
            @RequestBody UserCredentialsDto userCredentialsDto
    ) {
        try {
            JwtAuthenticationDto jwtAuthenticationDto = service.signIn(
                    userCredentialsDto
            );
            return ResponseEntity.ok(jwtAuthenticationDto);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed" + e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public JwtAuthenticationDto refresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
        return service.refreshToken(refreshTokenDto);
    }
}
