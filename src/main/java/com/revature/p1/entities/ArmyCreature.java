package com.revature.p1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.p1.dtos.requests.NewArmyMonsterRequest;
import com.revature.p1.services.ArmyService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private int power;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "army_id")
    @JsonBackReference
    private Army army;


    public ArmyCreature (Creature creature, Army army) {
        this.id = UUID.randomUUID();
        this.name = creature.getName();
        this.power = 0;

        // these may get shifted
        this.army = army;
    }

    public ArmyCreature (NewArmyMonsterRequest req, Army army) {
        this.id = UUID.randomUUID();
        this.name = req.getName();
        this.description = req.getDescription();
        this.image = req.getImage();
        this.power = 0;
        this.army = army;
    }

}
