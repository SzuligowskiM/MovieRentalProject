package com.example.demo.controllers;

import com.example.demo.models.UserCredential;
import com.example.demo.services.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/UserCredential")
public class UserCredentialController {
    @Autowired
    private UserCredentialService userCredentialService;
    @GetMapping(value = "/{Id}")
    public ResponseEntity<Optional<UserCredential>> getUserCredentialById(@PathVariable Long Id) {
        Optional<UserCredential> userCredential = userCredentialService.findById(Id);
        if(userCredential.isPresent()) return ResponseEntity.ok(userCredential);
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<UserCredential>> getAllUserCredentials() {
        List<UserCredential> userCredentials = userCredentialService.find();
        if(userCredentials.isEmpty()) ResponseEntity.noContent().build();
        return ResponseEntity.ok(userCredentials);
    }

    @PostMapping()
    public ResponseEntity<UserCredential> createUserCredential(@RequestBody UserCredential userCredential) {
        userCredentialService.save(userCredential);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCredential);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCredential> updateUserCredential(@PathVariable Long id, @RequestBody UserCredential userCredential) {
        try {
            userCredentialService.update(id, userCredential);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userCredential);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserCredential(@PathVariable Long id) {
        try {
            userCredentialService.delete(id);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
