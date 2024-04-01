package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User extends BaseEntity {
    private String name;
    private List<PlayList> playLists;
    private CurrentSong currentSong;
    public User(String id, String name, List<PlayList> playlists){
        this(id, name);
        this.playLists = playlists;
    }
    public User(String id, String name){
        this(name);
        this.id = id;
        this.currentSong = new CurrentSong();
    }
    public User(String name){
        this.name = name;
        this.playLists = new ArrayList<PlayList>();
    }


    public void addPlaylist(PlayList playlist){
        playLists.add(playlist);
    }
    public void deletePlaylist(PlayList playlist){
        playLists.removeIf(p-> p.getId() == playlist.getId());
    }
    public List<PlayList> getPlaylists() {
        return playLists.stream().collect(Collectors.toList());
    }
    public String getName() {
        return name;
    }
    public List<PlayList> getPlayLists() {
        return playLists;
    }
    public CurrentSong getCurrentSong() {
        return currentSong;
    }
    public void setCurrentSong(CurrentSong currentSong){
        this.currentSong=currentSong;
    }
    public boolean checkIfPlaylistExist(PlayList playlist){
        if(this.getPlayLists().isEmpty()){
            return false;
        }
        return this.getPlayLists().stream().anyMatch(p-> p.equals(playlist));
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", playlists=" + playLists + "]";
    }
}
