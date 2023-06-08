package com.revature.p1.services;

import com.revature.p1.dtos.requests.NewArmyRequest;
import com.revature.p1.dtos.requests.NewRoleRequest;
import com.revature.p1.entities.Army;
import com.revature.p1.entities.Role;
import com.revature.p1.repositories.ArmyRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArmyService {
    private final ArmyRepo armyRepo;

    public Army saveArmy(String username) {
        Army newArmy = new Army(username);
        return armyRepo.save(newArmy);
    }
}
