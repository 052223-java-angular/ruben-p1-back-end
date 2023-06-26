package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.NewRoleRequest;
import com.revature.p1.services.RoleService;
import com.revature.p1.utils.ResourceConflictException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="http://p1bucket-hyrule.s3-website-us-west-1.amazonaws.com/", exposedHeaders="Access-Control-Allow-Origin")
//@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/role") // subpath for roles
public class RoleController {
    private final RoleService roleService;


    // create a role entity for database
    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody NewRoleRequest req) {
        if (!roleService.isUniqueRole(req.getRoleName())) {
            throw new ResourceConflictException("Role already exists: " + req.getRoleName());
        }

        roleService.saveRole(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Exception handler for ResourceConflictException.
     *
     * @param e the ResourceConflictException to handle
     * @return ResponseEntity with the error message and status code indicating
     *         resource conflict
     */
    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Map<String, Object>> handleResourceConflictException(ResourceConflictException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }
}
