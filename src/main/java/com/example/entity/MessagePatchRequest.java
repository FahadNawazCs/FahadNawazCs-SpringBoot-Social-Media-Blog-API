package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagePatchRequest {
    
    private String messageText;

    public String getMessageText() {
        return messageText;
    }
}
