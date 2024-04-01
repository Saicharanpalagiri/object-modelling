package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dtos.PlayListDto;
import com.crio.jukebox.services.PlayListService;

public class ModifyPlaylistCommand implements ICommand{
    private PlayListService playListService;
    

    public ModifyPlaylistCommand(PlayListService playListService) {
        this.playListService = playListService;
    }


    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String commandName = tokens.get(1);
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        List<String> songIds = new ArrayList<>();
        for(int i = 4; i < tokens.size(); i++){
            songIds.add(tokens.get(i));
        }
        PlayListDto playListDto=null;
        try {
            if(commandName.equals("ADD-SONG")){
                playListDto=playListService.addSongPlaylist(userId, playlistId, songIds);
            }
             if(commandName.equals("DELETE-SONG")){
                playListDto=playListService.deleteSongPlaylist(userId, playlistId, songIds);
            }
            System.out.println(playListDto);
        } catch (RuntimeException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    
}
