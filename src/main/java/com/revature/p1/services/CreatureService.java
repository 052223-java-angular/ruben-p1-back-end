package com.revature.p1.services;

import com.revature.p1.entities.Creature;
import com.revature.p1.repositories.CreatureRepo;
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
}
