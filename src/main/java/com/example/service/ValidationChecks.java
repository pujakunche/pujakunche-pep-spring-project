package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Component
public class ValidationChecks {

    AccountRepository accountRepository;

    @Autowired
    public ValidationChecks(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Boolean registrationValidation(Account account){
        Boolean passesValidation = false;

        // Account fetchPotentialDups = accountRepository.findDuplicates(account.getUsername());
        Account fetchPotentialDups = accountRepository.findAccountByUsername(account.getUsername());

        if(fetchPotentialDups==(null)){
            passesValidation = true;
            return passesValidation;
        } else {
            return passesValidation;        
        }
    }

  

}
