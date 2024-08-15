package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.exception.BusinessException;
import com.example.customerservice.keycloak.dto.UserDTO;
import org.springframework.security.oauth2.jwt.Jwt;

public interface CustomerService {

    Customer getCustomer(Integer customerId, Jwt jwt) throws BusinessException;

    void createCustomer(UserDTO userDTO) throws BusinessException;
}
