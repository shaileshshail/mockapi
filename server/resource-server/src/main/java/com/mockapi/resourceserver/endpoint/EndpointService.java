package com.mockapi.resourceserver.endpoint;

public interface EndpointService {
    Endpoint createEndpoint(String orgId,String projectId,Endpoint endpoint,String userId);
    Endpoint updateEndpoint(String orgId,String projectId,Long id,Endpoint updatedEndpoint,String userId);
    String deleteEndpoint(String orgId,String projectId,Long id,String userId);
}
