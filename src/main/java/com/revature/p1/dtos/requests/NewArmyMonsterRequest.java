package com.revature.p1.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewArmyMonsterRequest {
    private String username;
    private String name;
    private String army_id;
    private String creature_id;
    private String power;
    private String stock;

    // for the token
    private String user_id;
}
