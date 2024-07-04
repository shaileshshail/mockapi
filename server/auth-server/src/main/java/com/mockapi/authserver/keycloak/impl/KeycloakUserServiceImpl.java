package com.mockapi.authserver.keycloak.impl;

import com.mockapi.authserver.dto.UserRegistrationRecord;
import com.mockapi.authserver.keycloak.KeycloakUserService;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class KeycloakUserServiceImpl implements KeycloakUserService {

    @Value("${keycloak.realm}")
    private String realm;
    private Keycloak keycloak;

    public KeycloakUserServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public UserRepresentation createUser(UserRegistrationRecord userRegistrationRecord) {

        UserRepresentation user=new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(false);

        Map<String, List<String>> attributes=new HashMap<>();
        UUID uuid = UUID.randomUUID();
        attributes.put("orgId",List.of(uuid.toString()));

        user.setAttributes(attributes);

        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userRegistrationRecord.password());
        credentialRepresentation.setTemporary(false);

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(user);

        if(Objects.equals(201,response.getStatus())){

//            List<UserRepresentation> representationList = usersResource.searchByUsername(userRegistrationRecord.username(), true);
//            if(!CollectionUtils.isEmpty(representationList)){
//                UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
//                assert userRepresentation1 != null;
//                //emailVerification(userRepresentation1.getId());
//            }
            return  user;
        }

//        response.readEntity()

        return null;
    }

    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRepresentation getUserById(String userId) {


        return  getUsersResource().get(userId).toRepresentation();
    }


    @Override
    public void deleteUserById(String userId) {
        System.out.println(userId+"-------------------------------------------------------------------------------------");
        getUsersResource().delete(userId);
    }


    @Override
    public UserRepresentation updateUser(UserRepresentation userRepresentation,String userId) {
        if(!Objects.equals(userId, userRepresentation.getId()))
            return null;

        UserResource userResource = getUserResource(userId);
        //later
        //https://www.tabnine.com/code/java/methods/org.keycloak.admin.client.resource.UserResource/update

        return null;
    }

//
//    @Override
//    public void emailVerification(String userId){
//
//        UsersResource usersResource = getUsersResource();
//        usersResource.get(userId).sendVerifyEmail();
//    }

    public UserResource getUserResource(String userId){
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId);
    }

//    @Override
//    public void updatePassword(String userId) {
//
//        UserResource userResource = getUserResource(userId);
//        List<String> actions= new ArrayList<>();
//        actions.add("UPDATE_PASSWORD");
//        userResource.executeActionsEmail(actions);
//
//    }
}
