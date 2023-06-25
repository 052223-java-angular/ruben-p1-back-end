package com.revature.p1.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class BattleOutcomeResponse {
    private String winner;
    private String loser;

    public BattleOutcomeResponse(String winner, String loser) {
        this.winner = winner;
        this.loser = loser;
    }
}
