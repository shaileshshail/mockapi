package com.mockapi.resourceserver.endpoint.impl;

import com.mockapi.resourceserver.endpoint.Endpoint;
import com.mockapi.resourceserver.endpoint.EndpointRepository;
import com.mockapi.resourceserver.endpoint.EndpointService;
import com.mockapi.resourceserver.organization.OrganizationRepository;
import com.mockapi.resourceserver.project.Project;
import com.mockapi.resourceserver.project.ProjectRepository;
import com.mockapi.resourceserver.project.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndpointServiceImpl implements EndpointService {

    @Autowired
    EndpointRepository endpointRepository;

    @Autowired
    ProjectServiceImpl projectService;

    @Override
    public Endpoint createEndpoint(String orgId,String projectId,Endpoint endpoint,String userId) {
        if (!projectService.isValidProject(orgId,projectId,userId))
            return null;
        Project project = projectService.getProjectById(orgId,projectId,userId);

        System.out.println(project.getBasepath()+"-------"+endpoint.getPath());
        endpoint.setPath(project.getBasepath()+endpoint.getPath());
        endpoint.setProject(project);
        return endpointRepository.save(endpoint);
    }

    @Override
    public Endpoint updateEndpoint(String orgId,String projectId,Long id,Endpoint updatedEndpoint,String userId) {
        if (!projectService.isValidProject(orgId,projectId,userId))
            return null;

        Endpoint endpoint = endpointRepository.findById(id).orElse(null);
        if (endpoint==null)
            return null;
        endpoint.setBody(updatedEndpoint.getBody());
        endpoint.setPath(updatedEndpoint.getPath());
        endpoint.setHttpRequest(updatedEndpoint.getHttpRequest());

        return endpointRepository.save(endpoint);
    }

    @Override
    public String deleteEndpoint(String orgId,String projectId,Long id,String userId) {
        if (!projectService.isValidProject(orgId,projectId,userId))
            return null;
        endpointRepository.deleteById(id);
        return "DELETED";
    }
}
