package com.revature.p1.services;

import com.revature.p1.dtos.requests.NewArmyMonsterRequest;
import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.entities.Army;
import com.revature.p1.entities.ArmyCreature;
import com.revature.p1.entities.Role;
import com.revature.p1.repositories.CreatureRepo;
import com.revature.p1.repositories.SoldierRepo;
import com.revature.p1.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

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
}
