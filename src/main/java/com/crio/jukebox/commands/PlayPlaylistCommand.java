package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.services.PlayListService;

public class PlayPlaylistCommand implements ICommand{
    private PlayListService playListService;
    

    public PlayPlaylistCommand(PlayListService playListService) {
        this.playListService = playListService;
    }


    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);
        try {
            SongDto songDto=playListService.playPlaylist(userId, playlistId);
            System.out.println(songDto);
        } catch (RuntimeException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        
    }
    
    
}
