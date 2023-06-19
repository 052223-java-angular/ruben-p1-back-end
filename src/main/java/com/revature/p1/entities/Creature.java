package com.revature.p1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.p1.dtos.requests.HyruleMonsterAllRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "creatures")
public class Creature {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String location;
    private String image;

    @OneToMany(mappedBy = "creature", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ArmyCreature> armies;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    public Creature(HyruleMonsterAllRequest req) {
        this.id = UUID.randomUUID().toString();
        this.name = req.getName();
        this.description = req.getDescription();
        this.location = req.getLocation();
        this.image = req.getImage();
    }
}
