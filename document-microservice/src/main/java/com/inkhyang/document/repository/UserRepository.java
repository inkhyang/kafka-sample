package com.inkhyang.document.repository;

import com.inkhyang.document.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findPersonByIdCard(String idCard);

}
