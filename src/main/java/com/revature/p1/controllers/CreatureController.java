package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.NewArmyMonsterRequest;
import com.revature.p1.dtos.requests.NewMonsterRequest;
import com.revature.p1.dtos.requests.TokenRequest;
import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.entities.ArmyCreature;
import com.revature.p1.entities.Creature;
import com.revature.p1.services.*;
import com.revature.p1.utils.ArmyNotFoundException;
import com.revature.p1.utils.CreatureNotFoundException;
import com.revature.p1.utils.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/monsters")
public class CreatureController {
    private final CreatureService creatureService;
    private final ArmyService armyService;
    private final UserService userService;
    private final SoldierService soldierService;
    private final JwtTokenService jwtTokenService;

    // inject services

    @PostMapping("/create")
    // only admins should use this field to add new monsters
    public ResponseEntity<?> createMonster(@RequestBody NewMonsterRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(req);
    }

    // returns all the monsters that exist on the database
    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestBody TokenRequest req, HttpServletRequest sreq){

        // validate the token request
        String token = sreq.getHeader("auth_token");
        Principal principal = userService.findById(req.getUser_id());
        jwtTokenService.validateToken(token, principal);


        // return body containing list of all available hyrule creatures
        List<Creature> allCreatures = creatureService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCreatures);
    }

    // finding creature by its name
    @GetMapping("/{name}")
    public ResponseEntity<?> findById(@RequestBody NewMonsterRequest req, HttpServletRequest sreq) {

        // validate the token request
        String token = sreq.getHeader("auth_token");
        Principal principal = userService.findById(req.getUser_id());
        jwtTokenService.validateToken(token, principal);

        System.out.println("Fail case hit");
        Creature foundCreature = creatureService.findByName(req.getName());
        return ResponseEntity.status(HttpStatus.OK).body(foundCreature);
    }

    @PostMapping("/{name}/add")
    public ResponseEntity<ArmyCreature> addToArmy(@RequestBody NewArmyMonsterRequest req) {
        // check if username to make sure actual user exists to add to their army
        if (userService.isUniqueUsername(req.getUsername())) {
            throw new UserNotFoundException("User not found");
        }
        // check to see if army exists to add into
        if (armyService.isValidArmy(req.getUsername())) {
            throw new ArmyNotFoundException("Army not found");
        }

        soldierService.addToArmy(req);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> findByCategory_id(@RequestBody NewMonsterRequest req) {
        System.out.println("HITTING category search");
        List<Creature> allCreatures = creatureService.findByCategory_id(req.getName());
        return ResponseEntity.status(HttpStatus.OK).body(allCreatures);
    }


    // TODO edit for later exceptions with modifications
    @ExceptionHandler(CreatureNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceConflictException(CreatureNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }
}
