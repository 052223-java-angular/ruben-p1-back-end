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
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="http://localhost:4200", exposedHeaders="Access-Control-Allow-Origin")
@AllArgsConstructor
@RestController
@RequestMapping("/soldier")
public class SoldierController {
    private final SoldierService soldierService;

    /**
     * Return the army by its id
     * @param req army id to return soldiers by
     * @return list of soldiers tied to that army id
     */
    @GetMapping("/{army_id}")
    public ResponseEntity<?> findByArmy(@PathVariable FindSoldierArmyIdRequest req){
        List<ArmyCreature> userSoldiers = soldierService.findByArmy_id(req.getArmy_id());
        System.out.println("SOLDIER GET ALL hit");
        return ResponseEntity.status(HttpStatus.OK).body(userSoldiers);
    }

    /**
     * deletes soldier from the army
     * @param req soldier id to delete
     * @return status indicating success request or fail
     */
    @DeleteMapping("/delete/{soldier_id}")
    public ResponseEntity<?> deleteSolider(@RequestBody DeleteSoldierRequest req) {
        soldierService.deleteSoldier(req.getSoldier_id());
        System.out.println("Delete soldier hit");
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
