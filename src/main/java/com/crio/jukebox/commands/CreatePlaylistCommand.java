package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dtos.PlayListDto;
import com.crio.jukebox.services.PlayListService;

public class CreatePlaylistCommand implements ICommand{
    private PlayListService playListService;
    
    public CreatePlaylistCommand(PlayListService playListService) {
        this.playListService = playListService;
    }



    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId=tokens.get(1);
        String playlistName=tokens.get(2);
        List<String> songs=new ArrayList<>();
        for(int i=3;i<tokens.size();i++){
            songs.add(tokens.get(i));
        }
        try {
            PlayListDto playListDto=playListService.create(userId, playlistName, songs);
            // System.out.println(playListDto);
             System.out.println("Playlist ID - " + playListDto.getId());
        } catch (RuntimeException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    
}
