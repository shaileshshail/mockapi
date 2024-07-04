package com.mockapi.endpointserver.endpoint;

public interface EndpointService {
    String GET_Endpoint(String projectId,String path);
    String POST_Endpoint(String projectId,String path);
    String PUT_Endpoint(String projectId,String path);
    String DELETE_Endpoint(String projectId,String path);
}
