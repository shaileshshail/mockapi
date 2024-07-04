package com.mockapi.resourceserver.organization;

import com.mockapi.resourceserver.organization.impl.OrganizationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/org")
@RequiredArgsConstructor
public class OrganizationController {
    @Autowired
    private OrganizationServiceImpl organizationService;

    @PostMapping("/{orgId}/user/{userId}")
    public ResponseEntity<String> createOrg(@PathVariable String userId,@PathVariable String orgId){
        String res= organizationService.createOrgByUserId(userId,orgId);
        if (res!=null)
            return new ResponseEntity<>(res,HttpStatus.CREATED);
        return new ResponseEntity<>("FAILED",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<Organization> getOrgById(@PathVariable String orgId, Principal principal){
        Organization org = organizationService.getOrgById(orgId,principal.getName());
        if (org!=null)
            return new ResponseEntity<>(org,HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteOrdByUserId(@PathVariable String userId){

        return new ResponseEntity<>(organizationService.deleteOrgById(userId),HttpStatus.CREATED);
    }
}
