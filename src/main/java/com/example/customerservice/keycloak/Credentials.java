package com.example.customerservice.keycloak;

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
