package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dtos.PlayListDto;
import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public interface PlayListService {
    public PlayListDto create(String userId, String name, List<String> songIds);

    public void deletePlaylist(String userId, String playlistId) throws PlaylistNotFoundException;
    
    public PlayListDto addSongPlaylist(String userId, String playlistId, List<String> songIds) throws SongNotFoundException;

    public PlayListDto deleteSongPlaylist(String userId, String playlistId, List<String> songIds) throws SongNotFoundException;

    public SongDto playPlaylist(String userId, String playlistId) throws PlaylistNotFoundException;

}
