package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.crio.jukebox.dtos.UserDto;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@DisplayName("Testing User Service")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    @DisplayName("create method should create UserInfoDto")
    public void create_ShouldReturnUser(){
        User expectedUser = new User("1", "testUser");
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
        UserDto expectedUserDto=new UserDto("1", "testUser");

        UserDto actualUserDto=userService.create("testUser");
        Assertions.assertEquals(expectedUserDto.toString(), actualUserDto.toString());
        verify(userRepository,times(1)).save(any(User.class));
 
    }
}
