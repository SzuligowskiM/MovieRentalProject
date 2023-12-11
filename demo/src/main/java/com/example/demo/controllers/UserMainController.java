package com.example.demo.controllers;
import com.example.demo.models.OrderMain;
import com.example.demo.models.UserMain;
import com.example.demo.services.UserMainService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/UserMain")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserMainController {
    @Autowired
    private UserMainService userMainService;
    @GetMapping(value = "/{Id}")
    public ResponseEntity<Optional<UserMain>> getMainUserById(@PathVariable Long Id) {
        Optional<UserMain> user = userMainService.findById(Id);
        if(user.isPresent()) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{Id}/orderMains")
    public ResponseEntity<Optional<List<OrderMain>>> getMainUserOrderMainsById(@PathVariable Long Id) {
        Optional<List<OrderMain>> orderMains = userMainService.findOrdersByUserId(Id);
        if(orderMains.isPresent()) return ResponseEntity.ok(orderMains);
        else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<Optional<UserMain>> getMainUserByCredentialEmail(@PathVariable String email) {
        Optional<UserMain> user = userMainService.findByUserCredentialEmail(email);
        if(user.isPresent()) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }
    @GetMapping()
    public ResponseEntity<List<UserMain>> getAllMainUsers() {
        List<UserMain> users = userMainService.find();
        if(users.isEmpty()) ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @PostMapping()
    public ResponseEntity<UserMain> createUser(@RequestBody UserMain userMain) {
        userMainService.save(userMain);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserMain> updateUser(@PathVariable Long id, @RequestBody UserMain userMain) {
        try {
            userMainService.update(id, userMain);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userMainService.delete(id);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
