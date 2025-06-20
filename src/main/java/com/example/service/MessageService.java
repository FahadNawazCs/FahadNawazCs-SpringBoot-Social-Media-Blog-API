package com.example.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired 
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message){
        if(message.getMessageText() == null || message.getMessageText().isEmpty() || message.getMessageText().length() > 255 || !accountRepository.existsAccountByAccountId(message.getPostedBy()) ){
            return null;
        } 
        

        Message savedMessage = messageRepository.save(message);
        return savedMessage;
    }

    public List<Message> retrieveAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessage(Integer messageId){
        return messageRepository.findByMessageId(messageId);
    }

    @Transactional
    public Integer deleteMessageByMessageId(Integer messageId){
        return messageRepository.deleteByMessageId(messageId);
    }

    @Transactional 
    public Message updateMessage(Message message){
        
    }
}
