package com.mockapi.gateway.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class GatewayConfiguration{

@Bean
public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
        .route("auth-keycloak",r->r.path("/realms/mockapi/protocol/openid-connect/**")
        .and().method("POST","GET")
        .and().host("localhost*")
        .uri("http://localhost:8081"))

        .route("auth-server",r->r.path("/auth/users/**")
        .and().method("POST","DELETE")
        .and().host("localhost*")
        .uri("http://localhost:8010"))

        .route("endpoint-server",r->r.path("/server/**")
        .and().method("POST","GET","PUT","DELETE")
        .and().host("localhost*")
        .uri("http://localhost:8030"))

        .route("resource-server",r->r.path("/api/v1/**","/resource-server/**")
        .and().method("POST","GET","PUT","DELETE")
        .and().host("localhost*")
        .uri("http://localhost:8020"))
        .build();
        }

}
