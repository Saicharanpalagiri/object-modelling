package com.crio.jukebox.commands;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.dtos.PlayListDto;
import com.crio.jukebox.exceptions.SongNotFoundException;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("CreatePlaylistCommandTest")
@ExtendWith(MockitoExtension.class)
public class CreatePlaylistCommandTest {
    PrintStream standardOut = System.out;
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Mock
    PlayListService playlistService;

    @InjectMocks
    CreatePlaylistCommand createPlaylistCommand;

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of CreatePlaylistCommand Should Print newly Created Playlist To Console")
    public void execute_ShouldPrintPlaylistSummaryDto(){
        String expectedOutput = "Playlist ID - 1" ;
        PlayListDto playListDto=new PlayListDto("1", "PLAYLIST_1", List.of("1","4","5","6"));
        when(playlistService.create("1","PLAYLIST_1", List.of("1", "4", "5","6"))).thenReturn(playListDto);

        createPlaylistCommand.execute(List.of("CREATE-PLAYLIST","1","PLAYLIST_1", "1", "4", "5","6"));
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        verify(playlistService, times(1)).create("1", "PLAYLIST_1", List.of("1", "4", "5","6"));
    }

    @Test
    @DisplayName("execute method of CreatePlaylistCommand Should Print Error Message To Console If User Not Found")
    public void execute_ShouldPrintErrorMessage_GivenUserNotFound(){
        //Arrange
        String expectedOutput = "User for given id: 1 not found!";
        doThrow(new UserNotFoundException(expectedOutput)).when(playlistService).create(anyString(), anyString(), anyList());
        //Act
        createPlaylistCommand.execute(List.of("CREATE-PLAYLIST", "1", "PLAYLIST_1", "1", "4", "5", "6"));
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        verify(playlistService, times(1)).create(anyString(), anyString(), anyList());
    }

    @Test
    @DisplayName("execute method of CreatePlaylistCommand Should Print Error Message To Console If Song Not Found")
    public void execute_ShouldPrintErrorMessage_GivenSongNotFound(){
        //Arrange
        String expectedOutput = "Some Requested Songs Not Available. Please try again.";
        doThrow(new SongNotFoundException(expectedOutput)).when(playlistService).create(anyString(), anyString(), anyList());
        //Act
        createPlaylistCommand.execute(List.of("CREATE-PLAYLIST", "1", "PLAYLIST_1", "1", "4", "5", "6"));
        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        verify(playlistService, times(1)).create(anyString(), anyString(), anyList());
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }
    
}
