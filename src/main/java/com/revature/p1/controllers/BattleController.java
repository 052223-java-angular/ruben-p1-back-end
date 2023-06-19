package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.NewBattleRequest;
import com.revature.p1.dtos.requests.NewMonsterRequest;
import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.entities.Army;
import com.revature.p1.entities.ArmyCreature;
import com.revature.p1.services.*;
import com.revature.p1.utils.ArmyNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/battle")
public class BattleController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final ArmyService armyService;
    private final SoldierService soldierService;
    private final StatsService statsService;

    /**
     * Creates a battle between two users
     * @param req player1, player 2, session id
     * @return status for success or fail
     */
    @GetMapping("/create")
    public ResponseEntity<?> createBattle(@RequestBody NewBattleRequest req, HttpServletRequest sreq) {
        // validate the token request
        String token = sreq.getHeader("auth-token");
        Principal principal = userService.findById(req.getUser_id());
        jwtTokenService.validateToken(token, principal);

        Boolean over = false;
        int count = 0;

        // grab the two passed users to battle
        Optional<Army> p1 = armyService.findByUsername(req.getPlayer_1());
        Optional<Army> p2 = armyService.findByUsername(req.getPlayer_2());

        // verify if users exist
        if ((p1.isEmpty() || p2.isEmpty())) {
            throw new ArmyNotFoundException("One or both players do not exist/army");
        }

        // define the player power level
        int power1 = p1.get().getPower() ;
        int power2 = p2.get().getPower();

        // get list of soldiers in player armies
        List<ArmyCreature> pOneArmy = soldierService.findByArmy_id(p1.get().getId());
        List<ArmyCreature> pTwoArmy = soldierService.findByArmy_id(p2.get().getId());

        // over power conditions: larger armies || power difference
        if (pOneArmy.size() > pTwoArmy.size()) {
            System.out.println("Army size differences");
        }

        int blueSoldier = 0;
        int redSoldier = 0;

        // iterate through and "battle"
        while(!over) {
            // if either army is out of soldiers
            if (pOneArmy.get(count) == null || pTwoArmy.get(count) == null ) {
                over = true;
                break;
            }

            // if either player has reached 0 health
            if ((power1 <= 0) || (power2 <= 0)) {
                over = true;
                break;
            }
            // obtain powers from next soldiers
            blueSoldier = pOneArmy.get(count).getPower();
            redSoldier = pTwoArmy.get(count).getPower();

            // battle of power
            if (blueSoldier > redSoldier) {
                // notess
                power2 -= redSoldier;
            }
            else {
                power1 -= blueSoldier;
            }
            count++;
        }
        // define winners
        if (power1 > power2) {
            // this is where I needed to update the scores...
        }

        // update scoreboard for BOTH players

        // output win/loss

        return ResponseEntity.status(HttpStatus.CREATED).body(req);
    }

}