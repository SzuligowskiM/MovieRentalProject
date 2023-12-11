package com.example.demo.services;

import com.example.demo.models.UserCredential;
import com.example.demo.repositories.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserCredentialService {
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    public Optional<UserCredential> findById(Long Id){
        return userCredentialRepository.findById(Id);
    }

    public List<UserCredential> find(){
        return userCredentialRepository.findAll();
    }
    @Transactional
    public void save(UserCredential userCredential){
        userCredentialRepository.save(userCredential);
    }

    @Transactional
    public void update(Long Id,UserCredential userCredential) throws Exception{
        Optional<UserCredential> foundUserCredential = userCredentialRepository.findById(Id);
        if(foundUserCredential.isPresent()){
            foundUserCredential.get().setEmail(userCredential.getEmail());
            foundUserCredential.get().setPassword(userCredential.getPassword());
            userCredentialRepository.save(foundUserCredential.get());
        }
        else throw new Exception("USER CREDENTIAL NOT FOUND");
    }

    @Transactional
    public void delete(Long Id) throws Exception{
            Optional<UserCredential> foundUserCredential = userCredentialRepository.findById(Id);
        if(foundUserCredential.isPresent()){
            userCredentialRepository.delete(foundUserCredential.get());
        }
        else throw new Exception("USER CREDENTIAL NOT FOUND");
    }
}
