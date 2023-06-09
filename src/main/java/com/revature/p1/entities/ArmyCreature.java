package com.revature.p1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String power;

    private String stock;

    @ManyToOne
    @JoinColumn(name = "army_id")
    @JsonBackReference
    private Army army;

    @ManyToOne
    @JoinColumn(name = "creature_id")
    @JsonBackReference
    private Creature creature;
}
