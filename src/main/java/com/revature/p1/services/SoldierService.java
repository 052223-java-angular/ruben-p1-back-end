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
import java.util.UUID;

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
        //set the power for creatures
        int power = 0;


        ArmyCreature newSoldier = new ArmyCreature(req, armyService.findByUsername(req.getUsername()).get());

        // set the power of the creature by parsing name, switch to lower for spelling
        String name = newSoldier.getName().toLowerCase();
        // get elemental and status attributes
        if (name.contains("ice") || name.contains("fire") || name.contains("thunder") || name.contains("electric")) {
            power += 3;
        }  if (name.contains("cursed") || name.contains("blue") || name.contains("stal")) {
            power += 4;
        }  if (name.contains("black") || name.contains("treasure") || name.contains("stal")) {
            power += 6;
        }  if (name.contains("blight") || name.contains("rare") || name.contains("stal")) {
            power += 20;
        }  if (name.contains("yiga") || name.contains("white") || name.contains("water")) {
            power += 6;
        }

        // parse and set power based on creature
        if (name.contains("guardian")) {
            newSoldier.setPower(10 + power);
        } else if (name.contains("lynel")){
            newSoldier.setPower(10 + power);
        } else if (name.contains("lizal")){
            newSoldier.setPower(5 + power);
        } else if (name.contains("hinox")){
            newSoldier.setPower(10 + power);
        } else if (name.contains("octorok")){
            newSoldier.setPower(3 + power);
        } else if (name.contains("moblin")){
            newSoldier.setPower(3 + power);
        }  else if (name.contains("robe")){
            newSoldier.setPower(5 + power);
        } else if (name.contains("moblin")){
            newSoldier.setPower(5 + power);
        }  else if (name.contains("dinraal")){
            newSoldier.setPower(100 + power);
        } else if (name.contains("bokoblin")){
            newSoldier.setPower(3 + power);
        }  else if (name.contains("calamity")){
            newSoldier.setPower(40 + power);
        } else if (name.contains("dark beast")){
            newSoldier.setPower(40 + power);
        } else if (name.contains("farosh")){
            newSoldier.setPower(100 + power);
        } else if (name.contains("talus")){
            newSoldier.setPower(25 + power);
        }  else if (name.contains("pebblit")){
            newSoldier.setPower(5 + power);
        } else if (name.contains("scout")){
            newSoldier.setPower(10 + power);
        }  else if (name.contains("molduga")){
            newSoldier.setPower(40 + power);
        } else if (name.contains("moldkung")){
            newSoldier.setPower(65 + power);
        }  else if (name.contains("naydra")){
            newSoldier.setPower(100 + power);
        } else if (name.contains("blademaster")){
            newSoldier.setPower(10 + power);
        } else {
            newSoldier.setPower(2 + power);
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
    public void deleteSoldier(UUID soldier_id) {
        Optional<ArmyCreature> soldierExists = soldierRepo.findById(soldier_id);
        // throw exception if not found
        if (soldierExists.isEmpty()) {
            throw new CreatureNotFoundException("Soldier does not exist");
        }
        // make updates to army stats if/when necessary
        soldierRepo.deleteById(soldier_id);
    }


}
