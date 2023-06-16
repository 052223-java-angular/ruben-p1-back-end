package com.revature.p1.services;


import com.revature.p1.dtos.requests.FindUserRequest;
import com.revature.p1.dtos.responses.StatsResponse;
import com.revature.p1.entities.Stats;
import com.revature.p1.entities.User;
import com.revature.p1.repositories.StatsRepo;
import com.revature.p1.repositories.UserRepository;
import com.revature.p1.utils.ResourceConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StatsService {
    private final StatsRepo statsRepo;
    private final UserRepository userRepo;
    private final UserService userService;


    public Stats saveStats(String username) {
        Stats newStats = new Stats(username);
        return  statsRepo.save(newStats);
    }

    public Optional<Stats> findById(String id) {
        Optional<Stats> userOpt = statsRepo.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResourceConflictException("User not found");
        }
        return userOpt;
    }

    public StatsResponse updateStats(int win, int loss, String username) {

        // get player stats to update
        Optional<Stats> statsOpt = statsRepo.findByUsername(username);
        System.out.println("Testing " + username);
        // find user, verify and get username

        if (win > loss) {
            statsOpt.get().setWin(statsOpt.get().getWin() + win);
        } else {
            statsOpt.get().setLose(statsOpt.get().getLose() + loss);
        }

        System.out.println(statsOpt.get().getWin());
        System.out.println(statsOpt.get().getLose());

        statsRepo.save(statsOpt.get());

        return new StatsResponse(username, statsOpt.get());
    }


}

