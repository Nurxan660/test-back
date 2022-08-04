package com.example.chat.controller;


import com.example.chat.dto.GetCommandRequest;
import com.example.chat.dto.MessageResponse;
import com.example.chat.dto.SaveCommandRequest;
import com.example.chat.entity.Command;
import com.example.chat.security.UserDetailsImpl;
import com.example.chat.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommandController {

    @Autowired
    private CommandService commandService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/command/add")
    public void saveComments(@Payload SaveCommandRequest saveCommandRequest) {
        MessageResponse messageResponse =commandService.addCommand(saveCommandRequest);
        messagingTemplate.convertAndSend( "/user/addCommand/"+saveCommandRequest.getUserId(),messageResponse);

    }

    @MessageMapping("/command/get")
    public void saveComments(@Payload GetCommandRequest getCommandRequest) {
        List<Command> res=commandService.getByCommand(getCommandRequest);
        messagingTemplate.convertAndSend( "/user/"+getCommandRequest.getUserId(),res);

    }
}



