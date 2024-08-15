package online.shopping.system.customer_service.keycloak;

import lombok.Builder;
import org.keycloak.representations.idm.CredentialRepresentation;

@Builder
public class Credentials {

    public static CredentialRepresentation createPasswordCredentials(String password){
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;

    }
}
