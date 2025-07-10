package com.webapp.bankwebapp.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.bankwebapp.entity.Transaction;
import com.webapp.bankwebapp.entity.User;
import com.webapp.bankwebapp.repository.TransactionRepository;
import com.webapp.bankwebapp.repository.UserRepository;
import com.webapp.bankwebapp.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/allTransaction")
    public ResponseEntity<List<Transaction>> getAllTransaction(@RequestBody Map<String,String>data)
    {
        String email=data.get("email");
        User user=userRepository.findByEmail(email);
        if(user==null)
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Transaction> transaction=transactionRepository.findByUser(user);
        return new ResponseEntity<>(transaction,HttpStatus.OK);        
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Map<String,Object>data)
    {
        String email = (String) data.get("email");
        double amount = ((Number) data.get("amount")).doubleValue();

        try{
        transactionService.deposit(email, amount);
        return new ResponseEntity<>(true,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawAmount(@RequestBody Map<String,Object>data)
    {
        String email = (String) data.get("email");
        double amount = ((Number) data.get("amount")).doubleValue();

        try{

        transactionService.withdraw(email, amount);
        return new ResponseEntity<>(true,HttpStatus.CREATED);

        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody Map<String,Object>data)
    {
        String senderEmail = (String) data.get("senderEmail");
        String receiverEmail = (String) data.get("receiverEmail");
        double amount = ((Number) data.get("amount")).doubleValue();
        
        try{
            transactionService.transfer(senderEmail, receiverEmail, amount);
            return new ResponseEntity<>(true,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
