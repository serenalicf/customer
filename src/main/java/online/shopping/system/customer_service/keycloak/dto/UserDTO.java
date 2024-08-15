package online.shopping.system.customer_service.keycloak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {
    private String username;
    private String emailId;
    private String password;
    private String firstname;
    private String lastName;
    private String phone;
}


