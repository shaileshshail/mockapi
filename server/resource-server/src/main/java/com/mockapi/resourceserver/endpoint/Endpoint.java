package com.mockapi.resourceserver.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.mockapi.resourceserver.project.Project;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.HttpMethod;

@Entity
@Data
public class Endpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private HttpRequest httpRequest;
    private String path;
    private String body;

    @ManyToOne
    @JsonIgnore
    private Project project;

    public enum HttpRequest{
        GET,POST,PUT,DELETE
    }
}
