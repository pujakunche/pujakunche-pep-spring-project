package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    
    private MessageRepository messageRepository;
    private ValidationChecks validationChecks;

    @Autowired
    public MessageService(MessageRepository messageRepository, ValidationChecks validationChecks){
        this.messageRepository = messageRepository;
        this.validationChecks = validationChecks;
    }

    public Message createMessage(Message message){
        Message newMessage = new Message();
        newMessage.setMessageText(message.getMessageText());
        newMessage.setPostedBy(message.getPostedBy());
        newMessage.setTimePostedEpoch(message.getTimePostedEpoch());

        Boolean validationResult = validationChecks.createMessageValidation(message);

        if(validationResult){
            Message result = messageRepository.save(newMessage);
            return result;
        } else {
            return null;
        }

        
    }

}
