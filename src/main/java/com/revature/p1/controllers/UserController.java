package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.FindUserRequest;
import com.revature.p1.dtos.responses.UserInfoRequest;
import com.revature.p1.entities.Creature;
import com.revature.p1.entities.User;
import com.revature.p1.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200", exposedHeaders="Access-Control-Allow-Origin")
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    /**
     * Finds all users that exist on the database
     * @return list of users that exist on the databse. only usernames
     */

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<UserInfoRequest> allUsers = userService.findAll();
        System.out.println("USER GET ALL hit");
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    /**
     * Finds and returns User entitity
     * @param req username to query
     * @return user found
     */

    @GetMapping("/{username}")
    public ResponseEntity<?> findByUsername(@RequestBody FindUserRequest req) {
        Optional<User> foundUser = userService.findByUsername(req);

        return ResponseEntity.status(HttpStatus.OK).body(foundUser);
    }
}
