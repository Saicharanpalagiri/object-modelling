package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dtos.UserDto;
import com.crio.jukebox.entities.User;

public interface UserService {
    public UserDto create(String name);

}
