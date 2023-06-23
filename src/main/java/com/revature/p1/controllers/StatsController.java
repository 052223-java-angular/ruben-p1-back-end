package com.revature.p1.controllers;

import com.revature.p1.dtos.requests.FindUserRequest;
import com.revature.p1.dtos.requests.UpdateScoreRequest;
import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.dtos.responses.StatsResponse;
import com.revature.p1.entities.Stats;
import com.revature.p1.entities.User;
import com.revature.p1.services.JwtTokenService;
import com.revature.p1.services.StatsService;
import com.revature.p1.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="http://localhost:4200", exposedHeaders="Access-Control-Allow-Origin")
@AllArgsConstructor
@RestController
@RequestMapping("/stats")
public class StatsController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final StatsService statsService;

    /**
     * Updates the scoreboard of the requested user
     * @param req player to update, win/loss update, session id
     * @param sreq header for token authentication
     * @return response for the updated scoreboard
     */
    @PutMapping("/{username}")
    public ResponseEntity<?> findByUsername(@RequestBody UpdateScoreRequest req, HttpServletRequest sreq) {

        // validate the token request
        String token = sreq.getHeader("auth-token");
        Principal principal = userService.findById(req.getUser_id());
        jwtTokenService.validateToken(token, principal);

        System.out.println(req.getLoss());

        //update the stats of this player
        StatsResponse statsResponse = statsService.updateStats(req.getWin(), req.getLoss(), req.getUsername());

        // update the scores
        System.out.println(statsResponse);
        return ResponseEntity.status(HttpStatus.OK).body(statsResponse);
    }

}
