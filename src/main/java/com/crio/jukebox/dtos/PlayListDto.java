package com.crio.jukebox.dtos;

import java.util.List;

public class PlayListDto {
    private final String id;
    private final String playList;
    private final List<String> songs;
    public PlayListDto(String id, String playList, List<String> songs) {
        this.id = id;
        this.playList = playList;
        this.songs = songs;
    }
    public String getId() {
        return id;
    }
    public String getPlayList() {
        return playList;
    }
    public List<String> getSongs() {
        return songs;
    }
    @Override
    public String toString() {
        return "PlayListDto [id=" + id + ", playList=" + playList + ", songs=" + songs + "]";
    }

    
}
