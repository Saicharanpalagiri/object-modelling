package com.crio.jukebox.services;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.crio.jukebox.dtos.SongDto;
import com.crio.jukebox.entities.CurrentSong;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.repositories.UserRepositoryImpl;

public class SongServiceImpl implements SongService{

    public SongRepository songRepository;
    public UserRepository userRepository;

    public SongServiceImpl(SongRepository songRepository, UserRepository userRepository) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SongDto playSong(String userId, String songId) throws UserNotFoundException,SongNotFoundException {
        // TODO Auto-generated method stub
         User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
         PlayList p=user.getCurrentSong().getCurrentPlayList();
         Song s=songRepository.findById(songId).get();
         if(! p.checkIfSongExists(s)){ //check song exist or not
            throw new SongNotFoundException("Given song id is not a part of the active playlist");
        }
        CurrentSong currentSong=new CurrentSong(p, s);
        user.setCurrentSong(currentSong);
        SongDto songDto=new SongDto(s.getTitle(),s.getAlbumName().getName(),s.getFeatureArtists().stream().map(x-> x.getName()).collect(Collectors.toList()));
        return songDto;
    }

    @Override
    public SongDto nextSong(String userId) throws UserNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User for given id: " + userId + " not found!"));
        PlayList p=user.getCurrentSong().getCurrentPlayList();
        Song s=user.getCurrentSong().getCurrentSong();
        List<Song> songs=p.getSongs();
        int index=IntStream.range(0,songs.size()).filter(ind->songs.get(ind).equals(s)).findFirst().getAsInt();
        int size=songs.size();
        int nextIndex=(index+1)%size;
        Song nextSong=songs.get(nextIndex);
        CurrentSong currentSong=new CurrentSong(p, nextSong);
        user.setCurrentSong(currentSong);
        userRepository.save(user);
        SongDto songdto=new SongDto(nextSong.getTitle(),nextSong.getAlbumName().getName(),nextSong.getFeatureArtists().stream().map(x-> x.getName()).collect(Collectors.toList()));
        return songdto;
    }

    @Override
    public SongDto backSong(String userId) throws UserNotFoundException{
        // TODO Auto-generated method stub
        User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User for given id: " + userId + " not found!"));
        PlayList p=user.getCurrentSong().getCurrentPlayList();
        Song s=user.getCurrentSong().getCurrentSong();
        List<Song> songs=p.getSongs();
        int index=IntStream.range(0,songs.size()).filter(ind->songs.get(ind).equals(s)).findFirst().getAsInt();
        int size=songs.size();
        int nextIndex=(index+size-1)%size;
        Song nextSong=songs.get(nextIndex);
        CurrentSong currentSong=new CurrentSong(p, nextSong);
        user.setCurrentSong(currentSong);
        userRepository.save(user);
        SongDto songdto=new SongDto(nextSong.getTitle(),nextSong.getAlbumName().getName(),nextSong.getFeatureArtists().stream().map(x-> x.getName()).collect(Collectors.toList()));
        return songdto;
    }
    
}
