package com.crio.jukebox.commands;

import static org.mockito.Mockito.doThrow;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.services.PlayListService;
import org.junit.jupiter.api.AfterEach;
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
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("Testing Delete PlayList command")
@ExtendWith(MockitoExtension.class)
public class DeletePlaylistCommandTest {

    PrintStream standardOut = System.out;

    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    PlayListService playListService;

    @InjectMocks
    DeletePlaylistCommand deletePlaylistCommand;

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of DeletePlaylistCommand Should Print Error Message To Console If User Not Found")
    public void execute_ShouldPrintErrorMessage_GivenUserNotFound(){
        //Arrange
        String expectedOutput = "User for given id: 1 not found!";
        doThrow(new UserNotFoundException(expectedOutput)).when(playListService)
        .deletePlaylist(anyString(), anyString());
        //Mocking Void Methods with Mockito
        //Act
        deletePlaylistCommand.execute(List.of("DELETE-PLAYLIST","1", "1"));
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        verify(playListService, times(1)).deletePlaylist(anyString(), anyString());
    }

    @Test
    @DisplayName("execute method of DeletePlaylistCommand Should Print Error Message To Console If Playlist Not Found")
    public void execute_ShouldPrintErrorMessage_GivenPlaylistNotFound(){
        //Arrange
        String expectedOutput = "Playlist Not Found";
        doThrow(new PlaylistNotFoundException(expectedOutput)).when(playListService).deletePlaylist(anyString(), anyString());
        //Act
        deletePlaylistCommand.execute(List.of("DELETE-PLAYLIST", "1", "1"));
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        verify(playListService, times(1)).deletePlaylist(anyString(), anyString());
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }
    
}
