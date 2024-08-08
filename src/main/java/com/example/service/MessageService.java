package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    
    private MessageRepository messageRepository;
    private ValidationChecks validationChecks;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ValidationChecks validationChecks, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.validationChecks = validationChecks;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message){
        Boolean validationResult = validationChecks.createMessageValidation(message);
        Optional<Account> fetchAccount = accountRepository.findById(message.getPostedBy());
        if(fetchAccount.isPresent()){
            if(validationResult){
                Message newMessage = new Message();
                newMessage.setMessageText(message.getMessageText());
                newMessage.setPostedBy(message.getPostedBy());
                newMessage.setTimePostedEpoch(message.getTimePostedEpoch());
                Message result = messageRepository.save(newMessage);
                return result;
            } else {
                return null;
            }       
        } else {
            return null;
        }     
    }

    public Integer updateMessage(Message message){

        Integer rowsUpdated = 0;

        Boolean validationResult = validationChecks.updateMessageValidation(message);
        Optional<Message> fetchMessage = messageRepository.findById(message.getMessageId());
        if(fetchMessage.isPresent()){
            if(validationResult){

                // int temp =  message.getMessageId();
                // int temp = message.getMessageId().intValue();
                // Integer temp = 9999;

                Message updatedMessage = new Message();
                updatedMessage.setMessageId(message.getMessageId());
                updatedMessage.setMessageText(message.getMessageText());
                // updatedMessage.setPostedBy(temp);
                updatedMessage.setPostedBy(message.getMessageId());
                updatedMessage.setTimePostedEpoch(message.getTimePostedEpoch());
    
                Message result = messageRepository.save(updatedMessage);
                if(result != null){
                    rowsUpdated = 1;
                }
                return rowsUpdated;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Message getMessage(Integer messageId){
        Optional<Message> fetchMessage = messageRepository.findById(messageId);
        if(fetchMessage.isPresent()){
            return fetchMessage.get();
        } else {
            return null;
        }
    }

    public List<Message> getAllMessage(){
        List<Message> fetchAllMessage = messageRepository.findAll();
            return fetchAllMessage;
    }

    public Integer deleteMessage(Integer messageId){
        Integer rowAffected = 0;
        Optional<Message> fetchMessage = messageRepository.findById(messageId);
        if(fetchMessage.isPresent()){
            messageRepository.deleteById(messageId);
            if(fetchMessage.get() != null){
                rowAffected = 1;
            }
            return rowAffected;
        } else {
            return null;
        }
    }

    public List<Message> getAllMessageUserMessage(Integer accountId){
        Optional<Account> fetchAccount = accountRepository.findById(accountId);
        List<Message> collectUserMessage = new ArrayList<>();
        if(fetchAccount.isPresent()){
            List<Message> listOfAllMessages = messageRepository.findAll();
            for( Message element : listOfAllMessages){
                Integer getPostById = element.getPostedBy();
                //Its matching but not adding
                if(getPostById.equals(accountId)){
                    collectUserMessage.add(element);
                } 
            }
            return collectUserMessage;
        } else {
            return null;
        }
    }

}
