package com.crio.jukebox.dtos;

import java.util.List;

public class SongDto {
    public String song;
    public String albumName;
    public List<String> featureArtists;
    @Override
    public String toString() {
        return "SongDto [artist=" + albumName + ", featureArtists=" + featureArtists + ", song=" + song
                + "]";
    }
    public SongDto(String song, String albumName, List<String> featureArtists) {
        this.song = song;
        this.albumName = albumName;
        this.featureArtists = featureArtists;
    }
    public String getSong() {
        return song;
    }
    public String getAlbumName() {
        return albumName;
    }
    public List<String> getFeatureArtists() {
        return featureArtists;
    }
    
}
