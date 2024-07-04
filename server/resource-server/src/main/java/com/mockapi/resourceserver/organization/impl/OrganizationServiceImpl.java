package com.mockapi.resourceserver.organization.impl;

import com.mockapi.resourceserver.organization.Organization;
import com.mockapi.resourceserver.organization.OrganizationRepository;
import com.mockapi.resourceserver.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;


    @Override
    public String createOrgByUserId(String userId,String orgId) {
        Organization organization = new Organization();
        organization.setId(orgId);
        organization.setUserId(userId);
        organization.setOrgType(Organization.OrgType.DEV);

        organizationRepository.save(organization);
        return organization.getId();
    }

    @Override
    public Organization getOrgById(String orgId,String userId) {
        Organization organization =  organizationRepository.findById(orgId).orElse(null);
        if (organization==null || !Objects.equals(organization.getUserId(), userId))
            return null;
        return organization;
    }

    @Override
    public String deleteOrgById(String orgId) {
         organizationRepository.deleteById(orgId);
         return "DELETED";
    }

    public boolean isValidOrganization(String orgId,String userId){
        Organization organization =  organizationRepository.findById(orgId).orElse(null);
        if (organization==null || !Objects.equals(organization.getUserId(), userId))
            return false;
        return true;
    }
}
