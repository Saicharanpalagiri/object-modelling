package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dtos.PlayListDto;
import com.crio.jukebox.services.PlayListService;

;

public class DeletePlaylistCommand implements ICommand{

    private PlayListService playListService;
    

    public DeletePlaylistCommand(PlayListService playListService) {
        this.playListService = playListService;
    }


    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId=tokens.get(1);
        String playListId=tokens.get(2);
        try {
            playListService.deletePlaylist(userId, playListId);
            System.out.println("Deleted Successful");
        } catch (RuntimeException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        
    }
    
}
