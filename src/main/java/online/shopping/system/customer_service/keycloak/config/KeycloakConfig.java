package online.shopping.system.customer_service.keycloak.config;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.admin.client.Keycloak;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix ="keycloak")
public class KeycloakConfig {

    private String serverUrl = "";

    private String realm = "";

    private String clientId = "";

    private  String clientSecret = "";
    private  String username = "";
    private  String password = "";

    public KeycloakConfig(){
    }

}
