package com.example.demo.repositories;

import com.example.demo.models.Movie;
import com.example.demo.models.UserMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMainRepository  extends JpaRepository<UserMain, Long> {
    Optional<UserMain> findByUserCredentialEmail(String email);
}
