package com.revature.p1.repositories;

import com.revature.p1.entities.Creature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreatureRepo extends JpaRepository <Creature, String> {


    public List<Creature> findAll();

    public List<Creature> findByCategory_id(String category);

    Optional<Creature> findByName(String name);
}
