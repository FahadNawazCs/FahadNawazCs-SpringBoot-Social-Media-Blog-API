package com.example.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.MessagePatchRequest;
import com.example.service.AccountService;
import com.example.entity.Message;
import com.example.service.MessageService;


import java.util.List;




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
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }
    
    @PostMapping("/register")
    ResponseEntity<Account> regsiterAccount(@RequestBody Account request){

        if(accountService.doesUsernameExist(request.getUsername())){
            return ResponseEntity.status(409).body(request);
        }

        Account registeredAccount = accountService.registerAccount(request.getUsername(), request.getPassword());

        if(Objects.isNull(registeredAccount)){
            return ResponseEntity.status(400).body(request);
        }
        
        return ResponseEntity.ok(registeredAccount);
    }

    @PostMapping("/login")
    ResponseEntity<Account> login(@RequestBody Account request){

        Account loggedInAccount = accountService.login(request);

        if(Objects.isNull(loggedInAccount)){
            return ResponseEntity.status(401).body(null);
        }
        
        return ResponseEntity.ok(loggedInAccount);
    }

    @PostMapping("/messages")
    ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage == null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(createdMessage);

    }

    @GetMapping("/messages")
    ResponseEntity<List<Message>> getAllMessages(){
        return ResponseEntity.ok(messageService.retrieveAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    ResponseEntity<Message> getMessageByMessageId(@PathVariable Integer messageId){
      
        return ResponseEntity.ok(messageService.getMessage(messageId));
    }

    @DeleteMapping("/messages/{messageId}")
    ResponseEntity<Integer> deleteMessageByMessageId(@PathVariable Integer messageId){
       Integer rowsAffected  = messageService.deleteMessageByMessageId(messageId);
        if(rowsAffected == 0){
        return ResponseEntity.ok(null);
       }
        return ResponseEntity.ok(rowsAffected);

        
 }

 @PatchMapping("/messages/{messageId}")
 ResponseEntity<Integer> updateMessage(@RequestBody MessagePatchRequest request, @PathVariable Integer messageId){
    Message updatedMessage = messageService.updateMessage(messageId,request.getMessageText() );
    if(updatedMessage == null){
        return ResponseEntity.status(400).body(null);
    }
    return ResponseEntity.ok(1);

 }

 @GetMapping("/accounts/{accountId}/messages")
 ResponseEntity<List<Message>> getAllMessagesByAccouuntId(@PathVariable Integer accountId){
    List<Message> allMessages =  messageService.getAllMessagesByAccountId(accountId);
    
    return ResponseEntity.ok(allMessages);
 }

    
}
