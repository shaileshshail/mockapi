package com.mockapi.resourceserver.organization;

public interface OrganizationService {

    String createOrgByUserId(String userId,String orgId);
    Organization getOrgById(String orgId,String userId);
    String deleteOrgById(String orgId);
}
