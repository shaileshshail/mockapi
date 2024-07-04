package com.mockapi.authserver.keycloak;

import com.mockapi.authserver.dto.UserRegistrationRecord;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakUserService {

    UserRepresentation createUser(UserRegistrationRecord userRegistrationRecord);
    UserRepresentation getUserById(String userId);
    void deleteUserById(String userId);

    UserRepresentation updateUser(UserRepresentation userRepresentation,String userId);


}
