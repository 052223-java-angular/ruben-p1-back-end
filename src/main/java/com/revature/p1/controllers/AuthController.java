package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.services.UserService;
import com.revature.p1.utils.ResourceConflictException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// forefront when hitting the endpoint
@AllArgsConstructor
@RestController
@RequestMapping("/auth") // first path --> sub paths
public class AuthController {
    private final UserService userService;

    // return a DTO containing new user information
    @PostMapping("/register") // sub path
    public ResponseEntity<?>registerUser(@RequestBody NewUserRequest req) { // DTO
        // check unique username
        if (!userService.isUniqueUsername(req.getUsername())) {
            throw new ResourceConflictException("Username is not unique");
        }

        // user name is valid

        // check password

        // check confirmed password with password


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Map<String, Object>> handleResourceConflictException(ResourceConflictException e) {
        Map<String, Object> map = new HashMap<>();

        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }
}
