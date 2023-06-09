package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.NewMonsterRequest;
import com.revature.p1.dtos.requests.NewUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monsters")
public class MonstersController {
    // inject services

    @PostMapping("/create")
    // only admins should use this field to add new monsters
    public ResponseEntity<?> createMonster(@RequestBody NewMonsterRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(req);
    }
}
