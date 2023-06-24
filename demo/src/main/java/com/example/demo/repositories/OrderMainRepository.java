package com.example.demo.repositories;

import com.example.demo.models.OrderMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMainRepository extends JpaRepository<OrderMain, Long> {

}
