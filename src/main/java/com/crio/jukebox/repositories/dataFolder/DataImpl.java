package com.crio.jukebox.repositories.dataFolder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.SongRepository;

public class DataImpl implements Data {
    private SongRepository songRepository;
    public DataImpl(SongRepository songRepository){
        this.songRepository=songRepository;
    }
    @Override
    public void loadData(String name, String delimiter) {
        // TODO Auto-generated method stub
        BufferedReader bufferedReader;
        try {
            bufferedReader=new BufferedReader(new FileReader(name));
            String line=bufferedReader.readLine();
            while(line!=null){
                List<String> args=Arrays.asList(line.split(delimiter));
                addSong(args.get(0),args.get(1),args.get(2),args.get(3),args.get(4));
                line= bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public void addSong(String songName, String genre, String albumName, String owner, String featureArtists){
        List<String> featureAStrings = Arrays.asList(featureArtists.split("#"));
        List<Artist> featureAList = featureAStrings.stream().map(s-> new Artist(s)).collect(Collectors.toList());
        Artist artist = new Artist(owner); 
        Album album = new Album(albumName);
        this.songRepository.save(new Song(songName, genre, album, artist, featureAList));
    }
    
}
