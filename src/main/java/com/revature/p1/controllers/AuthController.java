package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.NewLoginRequest;
import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.services.ArmyService;
import com.revature.p1.services.JwtTokenService;
import com.revature.p1.services.UserService;
import com.revature.p1.utils.ResourceConflictException;
import com.revature.p1.utils.UserNotFoundException;
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
    private final ArmyService armyService;
    private final JwtTokenService tokenService;

    // return a DTO containing new user information
    @PostMapping("/register") // sub path
    public ResponseEntity<?>registerUser(@RequestBody NewUserRequest req) { // DTO
        // check unique username
        if (!userService.isUniqueUsername(req.getUsername())) {
            throw new ResourceConflictException("Username is not unique");
        }
        // check password
        if (!userService.isValidPassword(req.getPassword())) {
            throw new ResourceConflictException("Password invalid. 8+ chars, one digit, one upper/lower");
        }

        // check confirmed password with password
        if (!userService.checkPasswords(req.getPassword(), req.getConfirmPassword())) {
            throw new ResourceConflictException("Passwords do not match");
        }
        System.out.println("Register user confirmation");
        userService.registerUser(req);
        System.out.println(req.getUsername());
        armyService.saveArmy(req.getUsername());


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Principal> login(@RequestBody NewLoginRequest req) {
        // check if username exists
        if (userService.isUniqueUsername(req.getUsername())) {
            throw new UserNotFoundException("User not found");
        }
        // login and set the principal
        Principal principal= userService.login(req);

        // create a jwt token and set the principal token
        String token = tokenService.generateToken(principal);
        principal.setToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(principal);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Map<String, Object>> handleResourceConflictException(ResourceConflictException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }
}
