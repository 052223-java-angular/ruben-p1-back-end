package com.revature.p1.dtos.responses;

import com.revature.p1.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoRequest {
    private String id;
    private String username;
    private String army_id;
    private String stats_id;

    // DTO to establish user information for safe read-only: no password
    public UserInfoRequest(User user, String army_id, String stats_id) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.army_id = army_id;
        this.stats_id = stats_id;
    }
}