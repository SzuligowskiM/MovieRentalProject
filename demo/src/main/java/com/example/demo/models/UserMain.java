package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
@Entity
public class UserMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @OneToMany
    private List<OrderMain> orderMains;
    @OneToOne
    private UserCredential userCredential;
}
