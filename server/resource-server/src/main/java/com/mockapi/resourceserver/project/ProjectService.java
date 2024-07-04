package com.mockapi.resourceserver.project;

import java.util.List;

public interface ProjectService {

    String createProject(String orgId,Project project,String userId);
    Project getProjectById(String orgId,String projectId,String userId);
    List<Project> getProjectsByOrgId(String orgId,String userId);

    Project updateById(String orgId,String projectId,Project updatedProject,String userId);
    String deleteProjectById(String orgId,String projectId,String userId);
}
