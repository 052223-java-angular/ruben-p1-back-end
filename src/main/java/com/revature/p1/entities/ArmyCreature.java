package com.revature.p1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.p1.services.ArmyService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "army_creatures")
public class ArmyCreature{

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private int power;


    @ManyToOne
    @JoinColumn(name = "army_id")
    @JsonBackReference
    private Army army;

    @ManyToOne
    @JoinColumn(name = "creature_id")
    @JsonBackReference
    private Creature creature;

    public ArmyCreature (Creature creature, Army army) {
        this.id = UUID.randomUUID().toString();
        this.name = creature.getName();
        this.power = 0;

        // these may get shifted
        this.army = army;
        this.creature = creature;
    }

}
