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

    public ArmyCreature addToArmy(NewArmyMonsterRequest req) {

        ArmyCreature newSoldier = new ArmyCreature(creatureService.findByName(req.getName()),
                armyService.findByUsername(req.getUsername()).get());

        return soldierRepo.save(newSoldier);
    }

    public List<ArmyCreature> findByArmy_id(String army_id) {
        List<ArmyCreature> creatureList = soldierRepo.findAllByArmy_id(army_id);
        return creatureList;
    }

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
