package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.NewMonsterRequest;
import com.revature.p1.entities.Creature;
import com.revature.p1.services.CreatureService;
import com.revature.p1.utils.CreatureNotFoundException;
import com.revature.p1.utils.ResourceConflictException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/monsters")
public class MonstersController {
    private final CreatureService creatureService;

    // inject services

    @PostMapping("/create")
    // only admins should use this field to add new monsters
    public ResponseEntity<?> createMonster(@RequestBody NewMonsterRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(req);
    }

    // returns all the monsters that exist on the database
    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<Creature> allCreatures = creatureService.findAll();
        System.out.println("Monster GET ALL hit");
        return ResponseEntity.status(HttpStatus.OK).body(allCreatures);
    }

    // finding creature by its name
    @GetMapping("/{name}")
    public ResponseEntity<?> findById(@RequestBody NewMonsterRequest req) {
        System.out.println("FIND BY NAME hit");
        Creature foundCreature = creatureService.findByName(req.getName());
        return ResponseEntity.status(HttpStatus.OK).body(foundCreature);
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
