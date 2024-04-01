package com.crio.jukebox.services;

import com.crio.jukebox.dtos.UserDto;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.UserRepository;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(String name) {
        // TODO Auto-generated method stub
        User u =new User(name);
        User savedUser=userRepository.save(u);
        return new UserDto(savedUser.getId(),savedUser.getName());
    }

}
