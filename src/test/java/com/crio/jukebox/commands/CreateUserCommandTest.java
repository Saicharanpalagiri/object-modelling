package com.crio.jukebox.commands;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.crio.codingame.services.UserService;
import com.crio.jukebox.dtos.UserDto;
import com.crio.jukebox.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Testing CreateUserCommandTest")
@ExtendWith(MockitoExtension.class)
public class CreateUserCommandTest {
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private CreateUserCommand createUserCommand;

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void executeShouldPrintUserInfoDto() {
        String expectedOutput = "1 name";
        UserDto userDto=new UserDto("1", "name");
        when(userService.create("name")).thenReturn(userDto);

        createUserCommand.execute(List.of("CREATE-USER","name"));
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

        verify(userService,times(1)).create(anyString());
    }
}
