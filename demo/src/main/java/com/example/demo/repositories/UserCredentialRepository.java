package com.example.demo.repositories;

import com.example.demo.models.Movie;
import com.example.demo.models.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
}