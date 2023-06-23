package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.DeleteSoldierRequest;

import com.revature.p1.dtos.responses.UserInfoRequest;
import com.revature.p1.entities.Army;

import com.revature.p1.entities.ArmyCreature;
import com.revature.p1.entities.Stats;
import com.revature.p1.entities.User;
import com.revature.p1.services.SoldierService;
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
@RequestMapping("/soldier")
public class SoldierController {
    private final SoldierService soldierService;

    /**
     * Return the army by its id
     *
     * @return list of soldiers tied to that army id
     */

    /*@GetMapping("/{armyid}")
    //@RequestMapping(value= "/{army_id}", method = RequestMethod.GET)
    public ResponseEntity<?> findByUsername(@PathVariable String armyid) {

        System.out.println("SOLDIER GET ALL hit ID: " + armyid);


        return ResponseEntity.status(HttpStatus.OK).body(userSoldiers);
    }*/
    @GetMapping("/{army_id}")
    @RequestMapping(value= "/{army_id}", method = RequestMethod.GET)
    public ResponseEntity<?> findByUsername(@PathVariable String army_id) {

        System.out.println("Testing army retrieve: " + army_id);
        List<ArmyCreature> userSoldiers = soldierService.findByArmy_id(army_id);

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
