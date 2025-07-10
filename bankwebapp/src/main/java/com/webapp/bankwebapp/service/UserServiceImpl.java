package com.webapp.bankwebapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.bankwebapp.entity.User;
import com.webapp.bankwebapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        if(user.getAccountType().equalsIgnoreCase("Savings"))
        {
            user.setBalance(10000.0);
        }
        else if(user.getAccountType().equals("Current"))
        {
            user.setBalance(5000.0);
        }
        else{
            user.setBalance(0.0);
        }
        return userRepository.save(user);
    }
    
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserProfile(String email){
        return userRepository.findByEmail(email);
    }

    


}
