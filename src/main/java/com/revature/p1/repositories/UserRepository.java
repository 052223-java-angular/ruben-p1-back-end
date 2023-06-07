package com.revature.p1.repositories;


import com.revature.p1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> { //entity, id type

    // key! 'findBy' + 'column name'
    Optional<User> findByUsername(String username);
}
