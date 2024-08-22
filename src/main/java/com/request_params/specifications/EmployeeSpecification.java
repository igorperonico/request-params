package com.request_params.specifications;

import com.request_params.models.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeSpecification {

    public static Specification<Employee> hasFirstName(String firstName) {
        return ((root, query, criteriaBuilder) ->
                firstName == null ? null : criteriaBuilder.like(criteriaBuilder
                        .lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"));
    }

    public static Specification<Employee> hasDepartment(String department) {
        return ((root, query, criteriaBuilder) ->
                department == null? null : criteriaBuilder.like(criteriaBuilder
                       .lower(root.get("department")), "%" + department.toLowerCase() + "%"));
    }

    public static Specification<Employee> hasSalaryRange(BigDecimal minSalary, BigDecimal maxSalary) {
        return ((root, query, criteriaBuilder) -> {
            if (minSalary!= null && maxSalary!= null)
                return criteriaBuilder.between(root.get("salary"), minSalary, maxSalary);
            else if (minSalary!= null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), minSalary);
            else if (maxSalary!= null)
                return criteriaBuilder.lessThanOrEqualTo(root.get("salary"), maxSalary);
            else
                return null;
        });
    }

    public static Specification<Employee> hasHiredDateRange(LocalDate hiredBefore, LocalDate hiredAfter) {
        return ((root, query, criteriaBuilder) -> {
            if (hiredBefore!= null && hiredAfter!= null)
                return criteriaBuilder.between(root.get("hireDate"), hiredBefore, hiredAfter);
            else if (hiredBefore!= null)
                return criteriaBuilder.lessThanOrEqualTo(root.get("hireDate"), hiredBefore);
            else if (hiredAfter!= null)
                return criteriaBuilder.greaterThanOrEqualTo(root.get("hireDate"), hiredAfter);
            else
                return null;
        });
    }

    public static Specification<Employee> isActive(Boolean active) {
        return ((root, query, criteriaBuilder) ->
                active == null? null : criteriaBuilder.equal(root.get("active"), active));
    }


}
