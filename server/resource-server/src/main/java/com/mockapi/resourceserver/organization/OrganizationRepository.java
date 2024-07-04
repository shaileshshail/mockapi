package com.mockapi.resourceserver.organization;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,String> {
    Organization findByUserId(String userId);
    String deleteByUserId(String userId);
}
