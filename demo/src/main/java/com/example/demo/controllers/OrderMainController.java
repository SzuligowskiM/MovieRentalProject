package com.example.demo.controllers;

import com.example.demo.models.OrderMain;
import com.example.demo.models.UserMain;
import com.example.demo.services.OrderMainService;
import com.example.demo.services.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/OrderMain")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class OrderMainController {
    @Autowired
    private OrderMainService orderMainService;
    @Autowired
    private UserMainService userMainService;
    @GetMapping(value = "/{Id}")
    public ResponseEntity<Optional<OrderMain>> getOrderMainById(@PathVariable Long Id) {
        Optional<OrderMain> orderMain = orderMainService.findById(Id);
        if(orderMain.isPresent()) return ResponseEntity.ok(orderMain);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<OrderMain> addOrderToUser(@PathVariable Long userId, @RequestBody OrderMain order) {
        Optional<UserMain> user = userMainService.findById(userId);
        if (user.isPresent()) {
            user.get().getOrderMains().add(order);
            userMainService.save(user.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}/balance")
    public ResponseEntity<Optional<UserMain>> changeBalanceOfUser(@PathVariable Long userId, @RequestBody Double balance) {
        Optional<UserMain> user = userMainService.findById(userId);
        if (user.isPresent()) {
            user.get().setBalance(balance);
            userMainService.save(user.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping()
    public ResponseEntity<List<OrderMain>> getAllOrderMains() {
        List<OrderMain> orders = orderMainService.find();
        if(orders.isEmpty()) ResponseEntity.noContent().build();
        return ResponseEntity.ok(orders);
    }

    @PostMapping()
    public ResponseEntity<OrderMain> createOrderMain(@RequestBody OrderMain orderMain) {
        orderMainService.save(orderMain);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMain);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderMain> updateOrderMain(@PathVariable Long id, @RequestBody OrderMain orderMain) {
        try {
            orderMainService.update(id, orderMain);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderMain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderMain(@PathVariable Long id) {
        try {
            orderMainService.delete(id);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
