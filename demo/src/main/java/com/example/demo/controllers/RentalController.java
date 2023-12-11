package com.example.demo.controllers;

import com.example.demo.models.Rental;
import com.example.demo.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Rental")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RentalController {
    @Autowired
    private RentalService rentalService;
    @GetMapping(value = "/{Id}")
    public ResponseEntity<Optional<Rental>> getRentalById(@PathVariable Long Id) {
        Optional<Rental> rental = rentalService.findById(Id);
        if(rental.isPresent()) return ResponseEntity.ok(rental);
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.find();
        if(rentals.isEmpty()) ResponseEntity.noContent().build();
        return ResponseEntity.ok(rentals);
    }

    @PostMapping()
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) {
        rentalService.save(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody Rental rental) {
        try {
            rentalService.update(id, rental);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        try {
            rentalService.delete(id);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
