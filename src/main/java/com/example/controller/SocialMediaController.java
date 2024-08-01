package com.example.controller;

import org.jboss.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account){
            Account result = accountService.createAccount(account);
            if(result != null){
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(409).body(null);

                // return ResponseEntity.notFound().build();
            }
        // return ResponseEntity.status(401).body("Unauthorized resource!");
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account){
        Account result = accountService.loginUser(account);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message result = messageService.createMessage(message);
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(400).body(null);

            // return ResponseEntity.notFound().build();
        }
    // return ResponseEntity.status(401).body("Unauthorized resource!");
}

    

}
