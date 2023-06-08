package com.revature.p1.repositories;


import java.util.Optional;

import com.revature.p1.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The RoleRepository interface provides database operations for Role entities.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    /**
     * Finds a role by its name.
     * @param name the name of the role to search for
     * @return an Optional containing the Role object if found, or an empty Optional
     *         otherwise
     */
    Optional<Role> findByName(String name);
}