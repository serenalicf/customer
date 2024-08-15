package com.example.customerservice.keycloak.service;

import com.example.customerservice.keycloak.dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeyCloakService {
    void addUser(UserDTO usrDTO);

    List<UserRepresentation> getUser(String username);

    void updateUser(String userId, UserDTO userDTO);

    void deleteUser(String userId);

    void sendVerificationLink(String userId);

    void sendResetPassword(String userId);
}
