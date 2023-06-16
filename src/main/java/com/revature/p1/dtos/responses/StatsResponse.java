package com.revature.p1.dtos.responses;

import com.revature.p1.entities.Stats;
import com.revature.p1.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatsResponse {
    String username;
    int win;
    int lose;

    public StatsResponse(String username, Stats stats) {
        this.username = username;
        this.win = stats.getWin();
        this.lose = stats.getLose();
    }

}
