package com.mockapi.endpointserver.endpoint;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Endpoint {

    @Id
    private Long id;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private HttpRequest httpRequest;
    private String path;
    private String body;

    private String projectId;

    public enum HttpRequest{
        GET,POST,PUT,DELETE
    }
}
