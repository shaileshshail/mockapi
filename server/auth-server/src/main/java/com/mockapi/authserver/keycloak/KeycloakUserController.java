package com.mockapi.authserver.keycloak;

import com.mockapi.authserver.dto.UserRegistrationRecord;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth/users")
@AllArgsConstructor
public class KeycloakUserController {
    private KeycloakUserService keycloakUserService;

    @PostMapping
    public ResponseEntity<UserRepresentation> createUser(@RequestBody UserRegistrationRecord userRegistrationRecord){
        System.out.println(userRegistrationRecord);
        UserRepresentation status = keycloakUserService.createUser(userRegistrationRecord);

        if (status==null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(status);
    }
    @PutMapping
    public ResponseEntity<UserRepresentation> updateUser(@RequestBody UserRepresentation user,Principal principal){
        UserRepresentation status = keycloakUserService.updateUser(user,principal.getName());

        if (status==null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(status);
    }

    @GetMapping
    public ResponseEntity<UserRepresentation> getUserById(Principal principal){
        System.out.println(principal.getName());
        return ResponseEntity.ok(keycloakUserService.getUserById(principal.getName()));
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId){
         keycloakUserService.deleteUserById(userId);
    }

}
