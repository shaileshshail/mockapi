package com.mockapi.authserver.config;


import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Value("${keycloak.realm}")
    private String kc_realm;

    @Value("${keycloak.urls.auth}")
    private String kc_serverURL;

    @Value("${keycloak.adminClientId}")
    private String kc_adminClientId;

    @Value("${keycloak.adminClientSecret}")
    private String kc_adminClientSecret;

    @Bean
    public Keycloak keycloak(){

        return KeycloakBuilder.builder()
                .serverUrl(kc_serverURL)
                .realm(kc_realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(kc_adminClientId)
                .clientSecret(kc_adminClientSecret)
                .build();
    }
}
