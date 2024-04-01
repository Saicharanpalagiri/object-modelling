package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.services.SongService;

public class PlaySongCommand implements ICommand{
    private SongService songService;

    public PlaySongCommand(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId = tokens.get(1);
        String param = tokens.get(2);
        SongDto sDto=null;
        try {
            if(param.equals("NEXT")){
                sDto=songService.nextSong(userId);
            }
            else if(param.equals("BACK")){
                sDto=songService.backSong(userId);
            }else{
                sDto=songService.nextSong(userId);
            }
            System.out.println(sDto);
            
        } catch (RuntimeException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }       
    }
}