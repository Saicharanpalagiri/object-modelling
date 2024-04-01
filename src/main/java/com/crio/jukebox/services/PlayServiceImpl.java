package com.crio.jukebox.services;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.dtos.PlayListDto;
import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.entities.CurrentSong;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;

public class PlayServiceImpl implements PlayListService{
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;
    private UserRepository userRepository;

    public PlayServiceImpl(PlaylistRepository playlistRepository, SongRepository songRepository,
            UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PlayListDto create(String userId, String name, List<String> songIds) throws UserNotFoundException,SongNotFoundException {
        // TODO Auto-generated method stub
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        List<Song> songs = songIds.stream().map(s -> songRepository.findById(s).orElseThrow(() -> new SongNotFoundException("Some Requested Songs Not Available. Please try again.")))
        .collect(Collectors.toList());
        PlayList p = playlistRepository.save(new PlayList(name, songs));
        PlayListDto playListDto=new PlayListDto(p.getId(), p.getName(), p.getSongList().stream().map(s-> s.getTitle()).collect(Collectors.toList()));
        user.addPlaylist(p);
        userRepository.save(user);
        return playListDto;
    }

    @Override
    public void deletePlaylist(String userId, String playlistId) throws PlaylistNotFoundException,UserNotFoundException {
        // TODO Auto-generated method stub
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        PlayList p = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist IDs do not exist"));
        if(! user.checkIfPlaylistExist(p)){
            throw new PlaylistNotFoundException("Playlist Not Found");
        }
        user.deletePlaylist(p);
        playlistRepository.deleteById(playlistId);
        userRepository.save(user);
    }

    @Override
    public PlayListDto addSongPlaylist(String userId, String playlistId, List<String> songIds)
            throws SongNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        PlayList p = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist IDs do not exist"));
        if(! user.checkIfPlaylistExist(p)){
            throw new PlaylistNotFoundException("Playlist Not Found");
        }
        List<Song> songs = songIds.stream().map(s-> songRepository.findById(s)
        .orElseThrow(() -> new SongNotFoundException("Some Requested Songs Not Available. Please try again.")))
        .collect(Collectors.toList());
        for(Song s : songs){ 
            if(! p.checkIfSongExists(s)){
                p.addSong(s);
            }
        }
        playlistRepository.save(p);
        userRepository.save(user);
        PlayListDto playListDto=new PlayListDto(p.getId(),p.getName(),
        p.getSongList().stream().map(s->s.getId()).collect(Collectors.toList()));
        return playListDto;
    }

    @Override
    public PlayListDto deleteSongPlaylist(String userId, String playlistId, List<String> songIds)
            throws SongNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        PlayList p = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist IDs do not exist"));
        if(! user.checkIfPlaylistExist(p)){
            throw new PlaylistNotFoundException("Playlist Not Found");
        }
        List<Song> songs = songIds.stream().map(s-> songRepository.findById(s)
        .orElseThrow(() -> new SongNotFoundException("Some Requested Songs Not Available. Please try again.")))
        .collect(Collectors.toList());
        for(Song s : songs){
            if(p.checkIfSongExists(s)){
                p.deleteSong(s);
            } else {
                throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }
        }
        playlistRepository.save(p);
        userRepository.save(user);
        PlayListDto playListDto=new PlayListDto(p.getId(),p.getName(),
        p.getSongList().stream().map(s->s.getId()).collect(Collectors.toList()));
        return playListDto;
    }

    @Override
    public SongDto playPlaylist(String userId, String playlistId) throws PlaylistNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        PlayList p = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Playlist IDs do not exist"));
        if(! user.checkIfPlaylistExist(p)){
            throw new PlaylistNotFoundException("Playlist Not Found");
        }
        List<Song> songs = p.getSongList();
        if(songs.isEmpty()){
            throw new PlaylistEmptyException("Playlist is empty.");
        }
        Song s = songs.get(0);
        CurrentSong currentSong=new CurrentSong(p, s);
        user.setCurrentSong(currentSong);
        userRepository.save(user);
        SongDto songDto=new SongDto(s.getTitle(), s.getAlbumName().getName(),
         s.getFeatureArtists().stream().map(song->song.getName()).collect(Collectors.toList()));
        return songDto;
    }
    
}
