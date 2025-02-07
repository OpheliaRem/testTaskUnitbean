package org.example.testtask.services;

import org.example.testtask.config.EmployeeDetails;
import org.example.testtask.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var employee = repository.findEmployeeByLogin(username);

        if (employee == null) {
            throw new UsernameNotFoundException(username + "not found");
        }

        return new EmployeeDetails(employee);
    }
}
