package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.DeleteSoldierRequest;
import com.revature.p1.dtos.requests.FindSoldierArmyIdRequest;
import com.revature.p1.dtos.requests.FindUserRequest;
import com.revature.p1.entities.ArmyCreature;
import com.revature.p1.services.SoldierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/soldier")
public class SoldierController {
    private final SoldierService soldierService;

    @GetMapping("/{army_id}")
    public ResponseEntity<?> findByArmy(@RequestBody FindSoldierArmyIdRequest req){
        List<ArmyCreature> userSoldiers = soldierService.findByArmy_id(req.getArmy_id());
        System.out.println("SOLDIER GET ALL hit");
        return ResponseEntity.status(HttpStatus.OK).body(userSoldiers);
    }

    @DeleteMapping("/delete/{soldier_id}")
    public ResponseEntity<?> deleteSolider(@RequestBody DeleteSoldierRequest req) {
        soldierService.deleteSoldier(req.getSoldier_id());
        System.out.println("Delete soldier hit");
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
