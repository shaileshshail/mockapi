package com.mockapi.resourceserver.endpoint;

import com.mockapi.resourceserver.endpoint.impl.EndpointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/endpoint")
public class EndpointController {

    @Autowired
    private EndpointServiceImpl endpointService;

    @PostMapping("/{orgId}/{projectId}")
    public ResponseEntity<Endpoint> createEndpoint(@PathVariable String orgId, @PathVariable String projectId,@RequestBody Endpoint endpoint,Principal principal){
        Endpoint endpoint1 = endpointService.createEndpoint(orgId,projectId,endpoint,principal.getName());

        if (endpoint!=null)
            return ResponseEntity.ok(endpoint1);

        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{orgId}/{projectId}/{endpointId}")
    public ResponseEntity<Endpoint> updateEndpoint( @PathVariable String orgId, @PathVariable String projectId,
                                                    @PathVariable Long endpointId,@RequestBody Endpoint endpoint, Principal principal){

        Endpoint endpoint1 = endpointService.updateEndpoint(orgId,projectId,endpointId,endpoint,principal.getName());

        if (endpoint!=null)
            return ResponseEntity.ok(endpoint1);

        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{orgId}/{projectId}/{endpointId}")
    public ResponseEntity<String> deleteEndpoint(@PathVariable String orgId, @PathVariable String projectId,
                                                 @PathVariable Long endpointId, Principal principal){

        String res = endpointService.deleteEndpoint(orgId,projectId,endpointId,principal.getName());
        if (res!=null)
            return ResponseEntity.ok("Deleted");

        return new ResponseEntity<>("Invalid Credentials or Access", HttpStatus.BAD_REQUEST);
    }
}
