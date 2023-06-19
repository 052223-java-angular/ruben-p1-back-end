package com.revature.p1.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HyruleMonsterAllRequest {
    private String name;
    private String description;
    private String image;
    private String location;

    // use for token ?
    private String user_id;
}
