package com.revature.p1.repositories;

import com.revature.p1.entities.Army;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArmyRepo extends JpaRepository<Army, String> {

    // returns army by username
    Optional<Army> findByName(String name);
}
