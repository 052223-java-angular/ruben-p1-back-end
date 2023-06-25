package com.revature.p1.services;

import com.revature.p1.dtos.requests.FindUserRequest;
import com.revature.p1.dtos.requests.NewArmyRequest;
import com.revature.p1.dtos.requests.NewRoleRequest;
import com.revature.p1.entities.Army;
import com.revature.p1.entities.Role;
import com.revature.p1.entities.User;
import com.revature.p1.repositories.ArmyRepo;
import com.revature.p1.utils.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ArmyService {
    private final ArmyRepo armyRepo;

    /**
     * saves new army tied to username
     * @param username username of the new registered user
     * @return newly saved army entity
     */
    public Army saveArmy(String username) {
        Army newArmy = new Army(username);
        return armyRepo.save(newArmy);
    }

    /**
     * checks if army is valid by username
     * @param username registered user to query for army
     * @return true or false if army exists
     */
    public boolean isValidArmy(String username) {
        Optional<Army> armyOpt = armyRepo.findByName(username);
        // if returns empty if does not exist
        return armyOpt.isEmpty();
    }

    /**
     * Finds army entity and returns
     * @param username username query for army
     * @return add status
     */
    public Optional<Army> findByUsername(String username) {
        return armyRepo.findByName(username);
    }
}
