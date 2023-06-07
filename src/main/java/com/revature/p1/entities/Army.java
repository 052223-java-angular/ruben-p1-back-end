package com.revature.p1.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "armies")
public class Army {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    private String power;

    @OneToMany(mappedBy = "army", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ArmyCreature> armies;


}
