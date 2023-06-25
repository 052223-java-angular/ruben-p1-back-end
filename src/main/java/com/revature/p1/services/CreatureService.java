package com.revature.p1.services;

import com.revature.p1.entities.Creature;
import com.revature.p1.repositories.CreatureRepo;
import com.revature.p1.utils.CreatureNotFoundException;
import com.revature.p1.utils.RoleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreatureService {
    private final CreatureRepo creatureRepo;

    /**
     * Calls repo and returns list of creatures
     * @return list of creatures
     */
    public List<Creature> findAll() {
        List<Creature> creatureList = creatureRepo.findAll();
        return creatureList;
    }

    /**
     * Calls repo and returns creatures by category
     * @param category category to query
     * @return list of creatures found on category
     */
    public List<Creature> findByCategory_id(String category) {
        List<Creature> creatureList = creatureRepo.findByCategory_id(category);
        return creatureList;
    }

    /**
     * finds creature by name
     * @param name of creature
     * @return creature that exists
     */
    public Creature findByName(String name) {
        return creatureRepo.findByName(name)
                .orElseThrow(() -> new CreatureNotFoundException("Creature " + name + " not found"));
    }

}
