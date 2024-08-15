package online.shopping.system.customer_service.controller;

import online.shopping.system.customer_service.dto.CustomerDto;
import online.shopping.system.customer_service.entity.Customer;
import online.shopping.system.customer_service.entity.mapper.CustomerMapper;
import online.shopping.system.customer_service.exception.BusinessException;
import online.shopping.system.customer_service.keycloak.dto.UserDTO;
import online.shopping.system.customer_service.service.CustomerService;
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
