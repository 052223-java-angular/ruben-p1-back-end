package com.revature.p1.repositories;


import com.revature.p1.entities.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatsRepo extends JpaRepository<Stats, String> {

    //Optional<Stats> findByName(String name);

    Optional<Stats> findById(String player_id);

    Optional<Stats> findByUsername(String username);
}
