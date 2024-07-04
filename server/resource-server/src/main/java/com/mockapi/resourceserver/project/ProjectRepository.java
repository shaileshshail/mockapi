package com.mockapi.resourceserver.project;

import com.mockapi.resourceserver.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,String> {
    List<Project> findByOrganization(Organization orgId);
}
