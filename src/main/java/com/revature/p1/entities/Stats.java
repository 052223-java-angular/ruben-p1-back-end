package com.revature.p1.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "statistics")
public class Stats {

    @Id
    private String id;
    private String name;

    private String win;

    private String lose;

    private String kd_ratio;

    @OneToMany(mappedBy = "stats", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<User> users;

    public Stats(String username) {
        this.id = UUID.randomUUID().toString();
        this.name = username;
        this.win = "0";
        this.lose = "0";
        this.kd_ratio = "0";
    }


}

