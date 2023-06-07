package com.revature.p1.services;

import com.revature.p1.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.revature.p1.entities.User;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        // if returns empty then it is unique
        return userOpt.isEmpty();
    }
}
