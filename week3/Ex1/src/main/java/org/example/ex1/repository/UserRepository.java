package org.example.ex1.repository;

import org.example.ex1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {
}
