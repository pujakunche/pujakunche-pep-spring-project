package com.example.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.jboss.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message result = messageService.createMessage(message);
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable Integer messageId ){
        Message result = messageService.getMessage(messageId);
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(200).body(null);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessage(){
        List<Message> result = messageService.getAllMessage();
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(200).body(null);
        }
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Integer messageId ){
        Message result = messageService.deleteMessage(messageId);
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(200).body(null);
        }
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessageByAccount(@PathVariable Integer accountId ){
        List<Message> result = messageService.getAllMessageUserMessage(accountId);
        if(result != null){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(200).body(null);
        }
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.PATCH)
    // @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Message> updateMessage(@RequestBody Message message, @PathVariable Integer messageId){
            Message result = messageService.updateMessage(message, messageId);
            if(result != null){
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(400).body(null);
            }
    }

    

}
