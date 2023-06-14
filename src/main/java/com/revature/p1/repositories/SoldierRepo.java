package com.revature.p1.repositories;

import com.revature.p1.entities.ArmyCreature;
import com.revature.p1.entities.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SoldierRepo extends JpaRepository<ArmyCreature, String> {

    Optional<Stats> findByName(String name);
    public List<SoldierRepo> findAllByArmy_id(String army_id);

}
