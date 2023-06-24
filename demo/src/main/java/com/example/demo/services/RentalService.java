package com.example.demo.services;

import com.example.demo.models.Rental;
import com.example.demo.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public Optional<Rental> findById(Long Id){
        return rentalRepository.findById(Id);
    }

    public List<Rental> find(){
        return rentalRepository.findAll();
    }
    @Transactional
    public void save(Rental rental){
        rentalRepository.save(rental);
    }

    @Transactional
    public void update(Long Id,Rental rental) throws Exception{
        Optional<Rental> foundRental = rentalRepository.findById(Id);
        if(foundRental.isPresent()){
            foundRental.get().setMovie(rental.getMovie());
            foundRental.get().setExpDate(rental.getExpDate());
            rentalRepository.save(foundRental.get());
        }
        throw new Exception("RENTAL NOT FOUND");
    }

    @Transactional
    public void delete(Long Id) throws Exception{
        Optional<Rental> foundRental = rentalRepository.findById(Id);
        if(foundRental.isPresent()){
            rentalRepository.delete(foundRental.get());
        }
        throw new Exception("RENTAL NOT FOUND");
    }
}
