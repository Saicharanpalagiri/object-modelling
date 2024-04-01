package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dtos.UserDto;
import com.crio.jukebox.services.UserService;

public class CreateUserCommand implements ICommand{
    private UserService userService;
    

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String name=tokens.get(1);
        UserDto userDto=userService.create(name);
        System.out.println(userDto.getId() + " " + userDto.getName());
    }
    
}
