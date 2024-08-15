package online.shopping.system.customer_service.keycloak.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix ="keycloak")
public class KeycloakConfig {

    static Keycloak keycloak = null;

    final static String serverUrl = "http://localhost:8080";

    public final static String realm = "ShoppingSystem";

    final static String clientId = "customer-service";

    final static String clientSecret = "mGVdl2giqvdU3A5LbGkqziEoOhF11M5Y";
    final static String username = "user";
    final static String password = "password";

    public KeycloakConfig(){
    }

    public static Keycloak getInstance() {
        if(keycloak == null){
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(username)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                    .build();
        }
        return keycloak;
    }


}
