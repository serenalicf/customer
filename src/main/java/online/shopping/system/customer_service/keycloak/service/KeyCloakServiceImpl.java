package online.shopping.system.customer_service.keycloak.service;

import online.shopping.system.customer_service.keycloak.Credentials;
import online.shopping.system.customer_service.keycloak.config.KeycloakConfig;
import online.shopping.system.customer_service.keycloak.dto.UserDTO;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class KeyCloakServiceImpl implements KeyCloakService{

    @Override
    public void addUser(UserDTO userDTO) {
        CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        UsersResource instance = getInstance();
        instance.create(user);
    }

    @Override
    public List<UserRepresentation> getUser(String username) {
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.search(username, true);
        return user;
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }

    @Override
    public void sendVerificationLink(String userId) {
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    @Override
    public void sendResetPassword(String userId) {
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));

    }

    public UsersResource getInstance(){
        return KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
    }
}
