package com.revature.p1.services;


import com.revature.p1.entities.Stats;
import com.revature.p1.repositories.StatsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatsService {
    private final StatsRepo statsRepo;

    public Stats saveStats(String username) {
        Stats newStats = new Stats(username);
        return  statsRepo.save(newStats);
    }
}

