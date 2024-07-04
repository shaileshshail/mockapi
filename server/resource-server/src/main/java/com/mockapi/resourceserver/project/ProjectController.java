package com.mockapi.resourceserver.project;

import com.mockapi.resourceserver.project.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/project")
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectService;

    @PostMapping("/{orgId}")
    public ResponseEntity<String> createProject(@RequestBody Project project,@PathVariable String orgId, Principal principal){
        return new ResponseEntity<>(projectService.createProject(orgId,project,principal.getName()), HttpStatus.CREATED);
    }
    @GetMapping("/{orgId}")
    public ResponseEntity<List<Project>> getAll(@PathVariable String orgId, Principal principal){
        System.out.println(principal.getName());
        return new ResponseEntity<>(projectService.getProjectsByOrgId(orgId,principal.getName()), HttpStatus.OK);
    }
    @GetMapping("/{orgId}/{projectId}")
    public ResponseEntity<Project> getOne(@PathVariable String orgId,@PathVariable String projectId, Principal principal){
        return new ResponseEntity<>(projectService.getProjectById(orgId,projectId,principal.getName()), HttpStatus.OK);
    }

    @PutMapping("/{orgId}/{projectId}")
    public ResponseEntity<Project> updateById(@PathVariable String orgId,@PathVariable String projectId,@RequestBody Project project, Principal principal){
        return ResponseEntity.ok(projectService.updateById(orgId,projectId,project,principal.getName()));
    }
    @DeleteMapping("/{orgId}/{projectId}")
    public ResponseEntity<String> deleteById(@PathVariable String orgId,@PathVariable String projectId, Principal principal){

        return ResponseEntity.ok(projectService.deleteProjectById(orgId,projectId,principal.getName()));
    }
}

