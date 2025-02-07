package org.example.testtask.repositories;

import org.example.testtask.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByLogin(String login);
}
