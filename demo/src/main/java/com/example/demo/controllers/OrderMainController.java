package com.example.demo.controllers;

import com.example.demo.models.OrderMain;
import com.example.demo.services.OrderMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/OrderMain")
public class OrderMainController {
    @Autowired
    private OrderMainService orderMainService;
    @GetMapping(value = "/{Id}")
    public ResponseEntity<Optional<OrderMain>> getOrderMainById(@PathVariable Long Id) {
        Optional<OrderMain> orderMain = orderMainService.findById(Id);
        if(orderMain.isPresent()) return ResponseEntity.ok(orderMain);
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
