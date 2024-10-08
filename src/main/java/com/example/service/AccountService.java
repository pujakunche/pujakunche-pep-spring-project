package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accountRepository;

    ValidationChecks validationChecks;

    @Autowired
    public AccountService(AccountRepository accountRepository, ValidationChecks validationChecks) {
        this.accountRepository = accountRepository;
        this.validationChecks = validationChecks;
    }


    public Account createAccount(Account account){
        Account freshAccount = new Account();
        freshAccount.setPassword(account.getPassword());
        freshAccount.setUsername(account.getUsername());

        Boolean validationResult = validationChecks.registrationValidation(freshAccount);
        
        if(validationResult){
            Account result = accountRepository.save(freshAccount);
            return result;
        } else {
            return null;
        }
    }

    public Account loginUser(Account account){
        String username = account.getUsername();
        String password = account.getPassword();
        Account userExists = accountRepository.findAccountByUsernameAndPassword(username, password);
        // Boolean valdationResult = validationChecks.loginValidation(userExists);

        if(userExists != null){
            return userExists;
        } else {
            return null;
        }
    }



    
}
