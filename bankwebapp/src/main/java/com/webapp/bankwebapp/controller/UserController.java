package com.webapp.bankwebapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.bankwebapp.entity.User;
import com.webapp.bankwebapp.service.UserServiceImpl;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        System.out.println(user.toString());
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<User> findByEmail(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");
    
        User user = userService.findByEmail(email);
    
        if (user != null) {
            if (user.getPassword().equals(password)) {
                System.out.println("Success: Correct User Welcome");
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                System.out.println("Invalid credentials");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        System.out.println("User does not exist");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
     
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestParam String email)
    {
        User user=userService.getUserProfile(email);
        if(user!=null)
        {
           return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/existByEmail")
    public ResponseEntity<Boolean>existByEmail(@RequestBody Map<String,String>data)
    {
        String email=data.get("email");
        boolean res=userService.existByEmail(email);
        if (res) {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.OK);
    }    
}
