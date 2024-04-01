package com.crio.jukebox.entities;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.codingame.entities.User;


public class PlayList extends BaseEntity{
    private String name;
    private List<Song> songs;
    

   
    public PlayList(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }
    public PlayList(String id, String name, List<Song> songs){
        this(name, songs);
        this.id = id;
    }
    public void deleteSong(Song song){
        songs.removeIf(s-> s.getId() == song.getId());
    }
    public PlayList(PlayList playList){
        this(playList.getId(), playList.getName(), playList.getSongs());
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayList other = (PlayList) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Playlist [id=" + id + ", name=" + name + ", songIds=" + songs + "]";
    }

    public void addSong(Song song){
        songs.add(song);
    }

    // public void deleteSong(Song song){
    //     songs.removeIf(s-> s.getId() == song.getId());
    // }
    public List<Song> getSongList(){
        return songs.stream().collect(Collectors.toList());
    }

    public boolean checkIfSongExists(Song song){
        if(this.getSongs().isEmpty()){
            return false;
        }
        return this.getSongs().stream().anyMatch(s->s.equals(song));
    }
}
