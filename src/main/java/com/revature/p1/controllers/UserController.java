package com.revature.p1.controllers;

import com.revature.p1.dtos.responses.UserInfoRequest;
import com.revature.p1.entities.Army;

import com.revature.p1.entities.Stats;
import com.revature.p1.entities.User;
import com.revature.p1.services.ArmyService;
import com.revature.p1.services.StatsService;
import com.revature.p1.services.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins="http://localhost:4200", exposedHeaders="Access-Control-Allow-Origin")
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final StatsService statsService;
    private final ArmyService armyService;

    /**
     * Finds all users that exist on the database
     * @return list of users that exist on the databse. only usernames
     */

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<UserInfoRequest> allUsers = userService.findAll();
        System.out.println("USER GET ALL hit" + allUsers);
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    /**
     * Finds and returns User entitity
     * @param username username to query
     * @return user found
     */

    @GetMapping("/{username}")
    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> findByUsername(@PathVariable String username) {

        System.out.println("Testing user retrieve: " + username);

        Optional<User> foundUser = userService.findByUsername(username);
        System.out.println("Testing user id: " + foundUser.get().getId());
        Optional<Stats> foundStats = statsService.findByName(username);
        Optional<Army> foundArmy = armyService.findByUsername(username);

        UserInfoRequest user = new UserInfoRequest(foundUser, foundArmy.get().getId(), foundStats.get().getId());
        System.out.println(foundUser.get());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
