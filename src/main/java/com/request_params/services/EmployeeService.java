package com.request_params.services;

import com.request_params.models.Employee;
import com.request_params.repositories.EmployeeRepository;
import com.request_params.specifications.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(String firstName, String department, BigDecimal minSalary, BigDecimal maxSalary, LocalDate hiredBefore, LocalDate hiredAfter, Boolean active) {
        Specification<Employee> spec = Specification
                .where(EmployeeSpecification.hasFirstName(firstName))
                .and(EmployeeSpecification.hasDepartment(department))
                .and(EmployeeSpecification.hasSalaryRange(minSalary, maxSalary))
                .and(EmployeeSpecification.hasHiredDateRange(hiredBefore, hiredAfter))
                .and(EmployeeSpecification.isActive(active));

        return employeeRepository.findAll(spec);
    }
}
