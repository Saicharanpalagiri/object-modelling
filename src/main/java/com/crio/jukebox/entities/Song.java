package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity{
    private String title;
    private String genre;
    private Album albumName;
    private Artist albumArtist;
    private List<Artist> featureArtists;
    public Song(String title, String genre, Album albumName, Artist albumArtist,
            List<Artist> featureArtists) {
        this.title = title;
        this.genre = genre;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.featureArtists = featureArtists;
    }
    public Song(String id ,String title, String genre, Album albumName, Artist albumArtist, List<Artist> featureArtists){
        this(title, genre, albumName, albumArtist, featureArtists);
        this.id = id;
    }
    public Song(Song song){
        this(song.getId(), song.getTitle(), song.getGenre(), song.getAlbumName(), song.getAlbumArtist(), song.getFeatureArtists());
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Album getAlbumName() {
        return albumName;
    }
    public void setAlbumName(Album albumName) {
        this.albumName = albumName;
    }
    public Artist getAlbumArtist() {
        return albumArtist;
    }
    public void setAlbumArtist(Artist albumArtist) {
        this.albumArtist = albumArtist;
    }
    public List<Artist> getFeatureArtists() {
        return featureArtists;
    }
    public void setFeatureArtists(List<Artist> featureArtists) {
        this.featureArtists = featureArtists;
    }
    @Override
    public String toString() {
        return "Song [albumArtist=" + albumArtist + ", albumName=" + albumName + ", featureArtists="
                + featureArtists + ", genre=" + genre + ", title=" + title + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
