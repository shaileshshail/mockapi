package com.mockapi.resourceserver.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mockapi.resourceserver.endpoint.Endpoint;
import com.mockapi.resourceserver.organization.Organization;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String basepath;

    @ManyToOne
    @JsonIgnore
    private Organization organization;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endpoint> endpoints;
}
