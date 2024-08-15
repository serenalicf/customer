package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.exception.BusinessException;
import com.example.customerservice.exception.constant.ErrorCode;
import com.example.customerservice.keycloak.dto.UserDTO;
import com.example.customerservice.keycloak.service.KeyCloakService;
import com.example.customerservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    KeyCloakService keyCloakService;

    @Override
    public Customer getCustomer(Integer customerId, Jwt jwt) throws BusinessException {
        try {
            Customer customer = customerRepository.findByCustomerId(customerId).orElse(null);
            String jwtId = jwt.getClaimAsString("sub");
            if(customer != null){
                if(!jwtId.equals(customer.getJwtId())){
                    throw new BusinessException(ErrorCode.JWT_ID_NOT_MATCH, jwtId);
                }
            }
            return customer;
        } catch (Exception ex){
            log.error(ex.getMessage(), ex);
            throw ex;
        }

    }

    @Override
    public void createCustomer(UserDTO userDTO) throws BusinessException {
        try{
            Optional<Customer> customer = customerRepository.findByUsername(userDTO.getUsername());
            if(customer.isPresent()){
                throw new BusinessException(ErrorCode.CUSTOMER_ALREADY_EXIST, userDTO.getUsername());
            }
            keyCloakService.addUser(userDTO);
            List<UserRepresentation> user = keyCloakService.getUser(userDTO.getUsername());

            String userId = user.get(0).getId();
            Customer newCustomer = Customer.builder()
                    .username(userDTO.getUsername())
                    .name(userDTO.getFirstname() + " " + userDTO.getLastName())
                    .email(userDTO.getEmailId())
                    .phone(userDTO.getPhone())
                    .jwtId(userId)
                    .createdOn(LocalDateTime.now())
                    .lastModifiedOn(LocalDateTime.now())
                    .build();
            customerRepository.save(newCustomer);
        } catch (Exception ex){
            log.error(ex.getMessage(), ex);
            throw ex;
        }

    }
}
