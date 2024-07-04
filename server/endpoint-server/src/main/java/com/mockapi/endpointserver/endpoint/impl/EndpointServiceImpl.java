package com.mockapi.endpointserver.endpoint.impl;

import com.mockapi.endpointserver.endpoint.Endpoint;
import com.mockapi.endpointserver.endpoint.EndpointRepository;
import com.mockapi.endpointserver.endpoint.EndpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EndpointServiceImpl implements EndpointService {

    @Autowired
    EndpointRepository endpointRepository;

    @Override
    public String GET_Endpoint(String projectId, String path) {
        return filter(projectId,path, Endpoint.HttpRequest.GET);
    }

    @Override
    public String POST_Endpoint(String projectId, String path) {
        return filter(projectId,path, Endpoint.HttpRequest.POST);
    }

    @Override
    public String PUT_Endpoint(String projectId, String path) {
        return filter(projectId,path, Endpoint.HttpRequest.PUT);
    }

    @Override
    public String DELETE_Endpoint(String projectId, String path) {
        return filter(projectId,path, Endpoint.HttpRequest.DELETE);
    }

    public String filter(String projectId, String path, Endpoint.HttpRequest httpRequest){
        List<Endpoint> endpoints = endpointRepository.custWork(projectId,path, httpRequest);
        if (endpoints.isEmpty())
            return "NOT FOUND";
        return endpoints.get(0).getBody();
    };
}
