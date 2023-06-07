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
