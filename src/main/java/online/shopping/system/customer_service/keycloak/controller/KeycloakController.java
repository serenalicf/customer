package online.shopping.system.customer_service.keycloak.controller;

import online.shopping.system.customer_service.keycloak.dto.UserDTO;
import online.shopping.system.customer_service.keycloak.service.KeyCloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class KeycloakController {

    @Autowired
    KeyCloakService keyCloakService;

    @PostMapping
    public String addUser(@RequestBody UserDTO usrDTO){
        keyCloakService.addUser(usrDTO);
        return "User is added successfully.";
    }

    @GetMapping("/{username}")
    public List<UserRepresentation> getUser(@PathVariable("username") String username){
        List<UserRepresentation> user = keyCloakService.getUser(username);
        return user;
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @RequestBody UserDTO userDTO){
        keyCloakService.updateUser(userId, userDTO);
        return "User details is updated successfully.";
    }

    @DeleteMapping("{userId}")
     public String deleteUser(@PathVariable("userId") String userId){
            keyCloakService.deleteUser(userId);
            return "User is deleted successfully.";
    }

    @GetMapping("/verification-link/{userId}")
    public String sendVerificationLink(@PathVariable("userId") String userId){
        keyCloakService.sendVerificationLink(userId);
        return "Verification link is sent to registered email id.";
    }

    @GetMapping("reset-password/{userId}")
    public String sendResetPassword(@PathVariable("userId") String userId){
        keyCloakService.sendResetPassword(userId);
        return "Reset password link is sent successfully to registered email id.";
    }
}
