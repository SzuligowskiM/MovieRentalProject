package com.example.demo.repositories;

import com.example.demo.models.Movie;
import com.example.demo.models.OrderMain;
import com.example.demo.models.UserMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMainRepository  extends JpaRepository<UserMain, Long> {
    Optional<UserMain> findByUserCredentialEmail(String email);
    @Query("SELECT u.orderMains FROM UserMain u WHERE u.id = :userId")
    Optional<List<OrderMain>> findOrdersByUserId(@Param("userId") Long userId);
}
