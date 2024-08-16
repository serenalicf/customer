package online.shopping.system.customer_service.keycloak.service;

import online.shopping.system.customer_service.keycloak.Credentials;
import online.shopping.system.customer_service.keycloak.config.KeycloakConfig;
import online.shopping.system.customer_service.keycloak.dto.UserDTO;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class KeyCloakServiceImpl implements KeyCloakService{

    @Autowired
    KeycloakConfig keycloakConfig;

    Keycloak keycloak = null;

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
        UsersResource instance = getKeycloakUsers();
        instance.create(user);
    }

    @Override
    public List<UserRepresentation> getUser(String username) {
        UsersResource usersResource = getKeycloakUsers();
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

        UsersResource usersResource = getKeycloakUsers();
        usersResource.get(userId).update(user);
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = getKeycloakUsers();
        usersResource.get(userId)
                .remove();
    }

    @Override
    public void sendVerificationLink(String userId) {
        UsersResource usersResource = getKeycloakUsers();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    @Override
    public void sendResetPassword(String userId) {
        UsersResource usersResource = getKeycloakUsers();
        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));

    }

    public UsersResource getKeycloakUsers(){
        return getKeycloakInstance().realm(keycloakConfig.getRealm()).users();
    }

    public  Keycloak getKeycloakInstance() {
        if( keycloak == null){
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakConfig.getServerUrl())
                    .realm(keycloakConfig.getRealm())
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(keycloakConfig.getUsername())
                    .password(keycloakConfig.getPassword())
                    .clientId(keycloakConfig.getClientId())
                    .clientSecret(keycloakConfig.getClientSecret())
                    .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                    .build();
        }
        return keycloak;
    }
}
