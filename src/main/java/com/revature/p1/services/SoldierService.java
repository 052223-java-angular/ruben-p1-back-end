package com.revature.p1.services;

import com.revature.p1.dtos.requests.NewArmyMonsterRequest;
import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.entities.Army;
import com.revature.p1.entities.ArmyCreature;
import com.revature.p1.entities.Creature;
import com.revature.p1.entities.Role;
import com.revature.p1.repositories.CreatureRepo;
import com.revature.p1.repositories.SoldierRepo;
import com.revature.p1.repositories.UserRepository;
import com.revature.p1.utils.CreatureNotFoundException;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SoldierService {
    private final CreatureService creatureService;
    private final ArmyService armyService;
    private final SoldierRepo soldierRepo;

    /**
     * Creates a new soldier from creature details
     * @param req creature name, army username
     * @return saves soldier to soldiers repo
     */
    public ArmyCreature addToArmy(NewArmyMonsterRequest req) {


        ArmyCreature newSoldier = new ArmyCreature(req, armyService.findByUsername(req.getUsername()).get());

        // set the power of the creature by parsing name, switch to lower for spelling
        String name = newSoldier.getName().toLowerCase();


        // parse and set power based on creature
        if (name.contains("guardian")) {
            newSoldier.setPower(15);
        } else if (name.contains("lynel")){
            newSoldier.setPower(10);
        } else if (name.contains("lizal")){
            newSoldier.setPower(5);
        } else if (name.contains("lynel")){
            newSoldier.setPower(10);
        } else if (name.contains("octorok")){
            newSoldier.setPower(3);
        } else if (name.contains("moblin")){
            newSoldier.setPower(3);
        }  else if (name.contains("wizzrobe")){
            newSoldier.setPower(5);
        } else {
            newSoldier.setPower(1);
        }



        return soldierRepo.save(newSoldier);
    }

    /**
     * Finds list of soldiers that exist in army
     * @param army_id
     * @return list of creatures
     */
    public List<ArmyCreature> findByArmy_id(String army_id) {
        List<ArmyCreature> creatureList = soldierRepo.findAllByArmy_id(army_id);
        return creatureList;
    }

    /**
     * Deletes existing soldier from the army
     * @param soldier_id query repo with id
     * @return nothing
     */
    public void deleteSoldier(String soldier_id) {
        Optional<ArmyCreature> soldierExists = soldierRepo.findById(soldier_id);
        // throw exception if not found
        if (soldierExists.isEmpty()) {
            throw new CreatureNotFoundException("Soldier does not exist");
        }
        // make updates to army stats if/when necessary
        soldierRepo.deleteById(soldier_id);
    }


}
