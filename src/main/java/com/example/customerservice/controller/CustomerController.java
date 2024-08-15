package com.example.customerservice.controller;

import com.example.customerservice.dto.CustomerDto;
import com.example.customerservice.entity.Customer;
import com.example.customerservice.entity.mapper.CustomerMapper;
import com.example.customerservice.exception.BusinessException;
import com.example.customerservice.keycloak.dto.UserDTO;
import com.example.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers/{customerId}")
    CustomerDto getCustomer(@Validated @PathVariable("customerId") Integer customerId, @AuthenticationPrincipal Jwt jwt) throws BusinessException {
        Customer customer = customerService.getCustomer(customerId, jwt);
        return CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }

    @PostMapping("/customers")
    void createCustomer(@RequestBody UserDTO userDTO) throws BusinessException {
        customerService.createCustomer(userDTO);
    }

}
