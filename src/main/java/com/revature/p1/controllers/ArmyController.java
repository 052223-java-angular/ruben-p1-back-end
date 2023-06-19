package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.FindArmyRequest;
import com.revature.p1.dtos.requests.FindUserRequest;
import com.revature.p1.entities.Army;
import com.revature.p1.entities.User;
import com.revature.p1.services.ArmyService;
import lombok.AllArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(ArmyController.class);

    /**
     * Finds an army by its username.
     * @param req contains the username to query and the session id
     * @return the army that belongs to username
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> findByUsername(@RequestBody FindArmyRequest req) {
        logger.info("Searching army by username");
        Optional<Army> foundUser = armyService.findByUsername(req.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(foundUser);
    }

}
