package com.request_params.services;

import com.request_params.models.Order;
import com.request_params.repositories.OrderRepository;
import com.request_params.specifications.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders(Long orderNumber, String customerName, String status,
                                    BigDecimal minAmount,  BigDecimal maxAmount, LocalDate startDate, LocalDate endDate){
        Specification<Order> spec = Specification
                .where(OrderSpecification.hasOrderNumber(orderNumber))
                .and(OrderSpecification.customerNameContains(customerName))
                .and(OrderSpecification.hasStatus(status))
                .and(OrderSpecification.hasTotalAmountRange(minAmount, maxAmount))
                .and(OrderSpecification.hasOrderDateRange(startDate, endDate));

        return orderRepository.findAll(spec);
    }
}
