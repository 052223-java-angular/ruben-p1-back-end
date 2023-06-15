package com.revature.p1.services;

import com.revature.p1.entities.Creature;
import com.revature.p1.repositories.CreatureRepo;
import com.revature.p1.utils.RoleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreatureService {
    private final CreatureRepo creatureRepo;

    public List<Creature> findAll() {
        List<Creature> creatureList = creatureRepo.findAll();
        return creatureList;
    }

    public List<Creature> findByCategory_id(String category) {
        List<Creature> creatureList = creatureRepo.findByCategory_id(category);
        return creatureList;
    }

    public Creature findByName(String name) {
        return creatureRepo.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Creature " + name + " not found"));
    }

}
