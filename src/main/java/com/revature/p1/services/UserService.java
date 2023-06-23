package com.revature.p1.services;

import com.revature.p1.dtos.requests.FindUserByIdRq;
import com.revature.p1.dtos.requests.FindUserRequest;
import com.revature.p1.dtos.requests.NewLoginRequest;
import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.dtos.responses.UserInfoRequest;
import com.revature.p1.entities.*;
import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.repositories.ArmyRepo;
import com.revature.p1.repositories.StatsRepo;
import com.revature.p1.repositories.UserRepository;
import com.revature.p1.utils.ResourceConflictException;
import com.revature.p1.utils.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final RoleService roleService;
    private final ArmyRepo armyRepo;
    private final StatsRepo statsRepo;

    /**
     * Finds User by username
     * @return list of existing users by STRING only
     */
    public List<UserInfoRequest> findAll() {
        List<User> userList = userRepo.findAll();
        // sub array for containing user IDs
        List<UserInfoRequest> users = new ArrayList<>();

        // Go through user objects and only return SAFE metrics
        for (User u: userList) {
            Optional<Army> armyOpt = armyRepo.findByName(u.getUsername());
            Optional<Stats> statsOpt = statsRepo.findByUsername(u.getUsername());

            String army_id = armyOpt.get().getId();
            String stats_id = statsOpt.get().getId();

            users.add(new UserInfoRequest(u.getId(), u.getUsername(), army_id, stats_id));
        }
        return users;
    }

    /**
     * Finds User by id
     * @param id name to query
     * @return user if found
     */
    public Principal findById(String id) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResourceConflictException("User notn found");
        }
        return new Principal(userOpt.get());
    }

    public Optional<User> findById(FindUserByIdRq req) {
        Optional<User> userOpt = userRepo.findByUsername(req.getUser_id());

        if (userOpt.isPresent()) {
            User foundUser = userOpt.get();
            return Optional.of(foundUser);
        }
        throw new UserNotFoundException("User was not found");
    }

    /**
     * Finds User by username
     * @param req name to query
     * @return user if found
     */
    public Optional<User> findByUsername(FindUserRequest req) {
        Optional<User> userOpt = userRepo.findByUsername(req.getUsername());

        if (userOpt.isPresent()) {
            User foundUser = userOpt.get();
           return Optional.of(foundUser);
        }
        throw new UserNotFoundException("User was not found");
    }

    /**
     * Queries for user and returns if success
     * @param req username, password and confirmPassword to verify
     * @return Principal for session details
     */
    public Principal login(NewLoginRequest req) {
        // retrieve if user exists
        Optional<User> userOpt = userRepo.findByUsername(req.getUsername());

        if (userOpt.isPresent()) {
            User foundUser = userOpt.get();
            if (BCrypt.checkpw(req.getPassword(), foundUser.getPassword())) {
                return new Principal(foundUser);
            }
        }
        throw new UserNotFoundException("Invalid credentials");
    }

    /**
     * Creates a new user
     * @param req username of registered user, password to hash
     * @return saves new user to repo
     */
    public User registerUser(NewUserRequest req) {
        // search if role exists and return
        Role userRole = roleService.findByName("USER");

        // generate a salt and encrypt password
        String hashedPass = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());
        User newUser = new User(req.getUsername(), hashedPass, userRole);

        return userRepo.save(newUser);
    }
    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        // if returns empty then it is unique
        return userOpt.isEmpty();
    }
/*                  REGEX
^                 # start-of-string
(?=.*[0-9])       # a digit must occur at least once
(?=.*[a-z])       # a lower case letter must occur at least once
(?=.*[A-Z])       # an upper case letter must occur at least once
(?=.*[@#$%^&+=])  # a special character must occur at least once
(?=\S+$)          # no whitespace allowed in the entire string
.{8,}             # anything, at least eight places though
$                 # end-of-string
 */
    public boolean isValidPassword(String password) {
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return false;
        }
        return true;
    }

    // check if confirmPassword matches with password
    public boolean checkPasswords(String password, String checkPassword) {
        if (password.matches(checkPassword)) { return true; }
        if (password == checkPassword) {
            return true;
        }
        return false;
    }
}
