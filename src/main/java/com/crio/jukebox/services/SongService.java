package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.entities.Song;

public interface SongService {
    public SongDto playSong(String userId, String songId);

    public SongDto nextSong(String userId);

    public SongDto backSong(String userId);
}
