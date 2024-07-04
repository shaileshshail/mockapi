package com.mockapi.resourceserver.organization;

import com.mockapi.resourceserver.project.Project;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Data
@Entity
public class Organization {

    @Id
    private String id;

    private String userId;

    @Enumerated(value = EnumType.STRING)
    private OrgType orgType;

    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    public enum OrgType{
        DEV,QA,PROD
    }
}

