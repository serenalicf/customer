package online.shopping.system.customer_service.service;

import online.shopping.system.customer_service.entity.Customer;
import online.shopping.system.customer_service.exception.BusinessException;
import online.shopping.system.customer_service.keycloak.dto.UserDTO;
import org.springframework.security.oauth2.jwt.Jwt;

public interface CustomerService {

    Customer getCustomer(Integer customerId, Jwt jwt) throws BusinessException;

    void createCustomer(UserDTO userDTO) throws BusinessException;
}
