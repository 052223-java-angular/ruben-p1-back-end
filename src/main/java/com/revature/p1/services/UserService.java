package com.revature.p1.services;

import com.revature.p1.dtos.requests.FindUserRequest;
import com.revature.p1.dtos.requests.NewLoginRequest;
import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.entities.Creature;
import com.revature.p1.entities.Role;
import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.repositories.UserRepository;
import com.revature.p1.utils.ResourceConflictException;
import com.revature.p1.utils.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.revature.p1.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final RoleService roleService;

    public List<String> findAll() {
        List<User> userList = userRepo.findAll();
        // sub array for containing user IDs
        List<String> userNames = new ArrayList<>();

        // Only return list of userIDs
        for (User u: userList) {
            userNames.add(u.getUsername());
        }
        return userNames;
    }

    public Principal findById(String id) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResourceConflictException("User notn found");
        }
        return new Principal(userOpt.get());
    }

    public Optional<User> findByUsername(FindUserRequest req) {
        Optional<User> userOpt = userRepo.findByUsername(req.getUsername());

        if (userOpt.isPresent()) {
            User foundUser = userOpt.get();
           return Optional.of(foundUser);
        }
        throw new UserNotFoundException("User was not found");
    }

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
