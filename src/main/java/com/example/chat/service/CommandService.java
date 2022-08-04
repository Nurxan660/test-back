package com.example.chat.service;

import com.example.chat.dto.GetCommandRequest;
import com.example.chat.dto.MessageResponse;
import com.example.chat.dto.SaveCommandRequest;
import com.example.chat.entity.Command;
import com.example.chat.entity.User;
import com.example.chat.repository.CommandRepository;
import com.example.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommandRepository commandRepository;


    public MessageResponse addCommand(SaveCommandRequest saveCommandRequest){
        Command command=new Command();
        User user=userRepository.findById(saveCommandRequest.getUserId()).orElseThrow(()->new RuntimeException("not found"));
        command.setUser(user);
        command.setContent(saveCommandRequest.getContent());
        command.setName(saveCommandRequest.getCommand());
        commandRepository.save(command);
        return new MessageResponse("Команда успешно создана");
    }

    public List<Command> getByCommand(GetCommandRequest getCommandRequest){
        List<Command> res=new ArrayList<>();
        res.add(null);
        if(getCommandRequest.getCommand().equals("logs")){
            return commandRepository.findByUserId(getCommandRequest.getUserId());
        }
        return res;
    }

}
