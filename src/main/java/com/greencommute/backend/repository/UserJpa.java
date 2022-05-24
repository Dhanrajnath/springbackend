package com.greencommute.backend.repository;

import com.greencommute.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpa extends JpaRepository<User, Integer> {
}
