package com.mockapi.resourceserver.project.impl;

import com.mockapi.resourceserver.organization.Organization;
import com.mockapi.resourceserver.organization.OrganizationRepository;
import com.mockapi.resourceserver.project.Project;
import com.mockapi.resourceserver.project.ProjectRepository;
import com.mockapi.resourceserver.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public String createProject(String orgId,Project project,String userId) {
        Organization organization = organizationRepository.findById(orgId).orElse(null);
        if(organization==null || !organization.getUserId().equals(userId))
            return null;
        project.setOrganization(organization);
        return projectRepository.save(project).getId();
    }

    @Override
    public Project getProjectById(String orgId,String projectId,String userId) {
        Organization organization = organizationRepository.findById(orgId).orElse(null);
        if(organization==null || !organization.getUserId().equals(userId))
            return null;
        return projectRepository.findById(projectId).orElse(null);
    }

    @Override
    public List<Project> getProjectsByOrgId(String orgId,String userId) {
        Organization organization = organizationRepository.findById(orgId).orElse(null);
        if(organization==null || !organization.getUserId().equals(userId))
            return null;

        return projectRepository.findByOrganization(organization);
    }

    @Override
    public Project updateById(String orgId,String projectId, Project updatedProject,String userId) {
        Organization organization = organizationRepository.findById(orgId).orElse(null);
        if(organization==null || !organization.getUserId().equals(userId))
            return null;

        Project project = projectRepository.findById(projectId).orElse(null);
        if (project==null || !projectId.equals(updatedProject.getId()))
            return null;

        project.setName(updatedProject.getName());
        project.setBasepath(updatedProject.getBasepath());
        return projectRepository.save(project);
    }

    @Override
    public String deleteProjectById(String orgId,String projectId,String userId) {
        Organization organization = organizationRepository.findById(orgId).orElse(null);
        if(organization==null || !organization.getUserId().equals(userId))
            return null;

        projectRepository.deleteById(projectId);
        return "DELETED";
    }

    public boolean isValidProject(String orgId,String projectId,String userId){
        Organization organization = organizationRepository.findById(orgId).orElse(null);
        if (organization==null || !organization.getUserId().equals(userId))
            return false;

        Project project =projectRepository.findById(projectId).orElse(null);

        if (project==null )
            return false;
        return true;
    }
}
