package com.revature.p1.services;

import com.revature.p1.dtos.requests.NewRoleRequest;
import com.revature.p1.entities.Role;
import com.revature.p1.repositories.RoleRepository;
import com.revature.p1.repositories.UserRepository;
import com.revature.p1.utils.RoleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepo;

    /*
    *  @req - new role request containing role field
    *  @return - returns a newly made role object
    */
    public Role saveRole(NewRoleRequest req) {
        Role newRole = new Role(req.getRoleName());
        return roleRepo.save(newRole);
    }

    /*
    *  @name - passes in role name and searches if exist.
    *  @return - returns role found OR throws exception if role does not exist
    * */
    public Role findByName(String name) {
        return roleRepo.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role " + name + " not found"));
    }

    // checks to see if role already exists: use to range check queries about role
    public boolean isUniqueRole(String name) {
        return roleRepo.findByName(name).isEmpty();
    }
}
