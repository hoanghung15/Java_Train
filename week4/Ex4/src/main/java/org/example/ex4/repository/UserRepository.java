package org.example.ex4.repository;

import org.example.ex4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsername(String username);
}
