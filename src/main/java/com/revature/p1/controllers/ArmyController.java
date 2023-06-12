package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.FindArmyRequest;
import com.revature.p1.dtos.requests.FindUserRequest;
import com.revature.p1.entities.Army;
import com.revature.p1.entities.User;
import com.revature.p1.services.ArmyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import com.revature.p1.services.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/armies")
public class ArmyController {
    private final UserService userService;
    private final ArmyService armyService;

    @GetMapping("/{username}")
    public ResponseEntity<?> findByUsername(@RequestBody FindArmyRequest req) {
        Optional<Army> foundUser = armyService.findByUsername(req.getUsername());


        return ResponseEntity.status(HttpStatus.OK).body(foundUser);
    }

}
