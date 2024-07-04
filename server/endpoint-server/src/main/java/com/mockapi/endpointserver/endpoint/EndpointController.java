package com.mockapi.endpointserver.endpoint;

import com.mockapi.endpointserver.endpoint.impl.EndpointServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/server")
public class EndpointController {

    @Autowired
    private EndpointServiceImpl endpointService;

    @GetMapping("/{projectId}/**")
    public String getEndpoint(HttpServletRequest request, @PathVariable String projectId){
        String path = request.getServletPath().substring(44);
        System.out.println(path);

        return endpointService.GET_Endpoint(projectId,path);
    }
    @PostMapping("/{projectId}/**")
    public String postEndpoint(HttpServletRequest request, @PathVariable String projectId){
        String path = request.getServletPath().substring(44);
        System.out.println(path);

        return endpointService.GET_Endpoint(projectId,path);
    }
    @PutMapping("/{projectId}/**")
    public String putEndpoint(HttpServletRequest request, @PathVariable String projectId){
        String path = request.getServletPath().substring(44);
        System.out.println(path);

        return endpointService.GET_Endpoint(projectId,path);
    }
    @DeleteMapping("/{projectId}/**")
    public String deleteEndpoint(HttpServletRequest request, @PathVariable String projectId){
        String path = request.getServletPath().substring(44);
        System.out.println(path);

        return endpointService.GET_Endpoint(projectId,path);
    }

}
