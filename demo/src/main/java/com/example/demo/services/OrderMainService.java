package com.example.demo.services;

import com.example.demo.models.OrderMain;
import com.example.demo.repositories.OrderMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderMainService {
    @Autowired
    private OrderMainRepository orderMainRepository;

    public Optional<OrderMain> findById(Long Id){
        return orderMainRepository.findById(Id);
    }

    public List<OrderMain> find(){
        return orderMainRepository.findAll();
    }
    @Transactional
    public void save(OrderMain orderMain){
        orderMainRepository.save(orderMain);
    }

    @Transactional
    public void update(Long Id,OrderMain orderMain) throws Exception{
        Optional<OrderMain> foundOrder = orderMainRepository.findById(Id);
        if(foundOrder.isPresent()){
            foundOrder.get().setRentals(orderMain.getRentals());
            foundOrder.get().setOrderDate(orderMain.getOrderDate());
            orderMainRepository.save(foundOrder.get());
        }
        else throw new Exception("ORDER NOT FOUND");
    }

    @Transactional
    public void delete(Long Id) throws Exception{
        Optional<OrderMain> foundOrder = orderMainRepository.findById(Id);
        if(foundOrder.isPresent()){
            orderMainRepository.delete(foundOrder.get());
        }
        else throw new Exception("ORDER NOT FOUND");
    }


}
