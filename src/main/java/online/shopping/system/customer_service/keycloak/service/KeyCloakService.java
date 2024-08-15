package online.shopping.system.customer_service.keycloak.service;

import online.shopping.system.customer_service.keycloak.dto.UserDTO;
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
