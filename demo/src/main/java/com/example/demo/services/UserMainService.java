package com.example.demo.services;

import com.example.demo.models.UserMain;
import com.example.demo.repositories.UserMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserMainService {
    @Autowired
    private UserMainRepository userMainRepository;

    public Optional<UserMain> findById(Long Id){
        return userMainRepository.findById(Id);
    }

    public Optional<UserMain> findByUserCredentialEmail(String email){
        return userMainRepository.findByUserCredentialEmail(email);
    }

    public List<UserMain> find(){
        return userMainRepository.findAll();
    }
    @Transactional
    public void save(UserMain userMain){
        userMainRepository.save(userMain);
    }

    @Transactional
    public void update(Long Id,UserMain userMain) throws Exception{
        Optional<UserMain> foundUser = userMainRepository.findById(Id);
        if(foundUser.isPresent()){
            foundUser.get().setName(userMain.getName());
            foundUser.get().setSurname(userMain.getSurname());
            foundUser.get().setOrderMains(userMain.getOrderMains());
            foundUser.get().setUserCredential(userMain.getUserCredential());
            userMainRepository.save(foundUser.get());
        }
        throw new Exception("USER NOT FOUND");
    }

    @Transactional
    public void delete(Long Id) throws Exception{
        Optional<UserMain> foundUser = userMainRepository.findById(Id);
        if(foundUser.isPresent()){
            userMainRepository.delete(foundUser.get());
        }
        throw new Exception("USER NOT FOUND");
    }

}
