package com.crio.jukebox.entities;

public class CurrentSong {
    private PlayList playList;
    private Song song;
   public CurrentSong(){
   }
   public CurrentSong(PlayList p, Song s){
    this.playList = p;
    if(p.checkIfSongExists(s)){
        this.song = s;
    }
   }   
   
   public PlayList getCurrentPlayList(){
    return playList;
}

public Song getCurrentSong(){
    return song;
}
public void changeActiveSong(PlayList playList ,Song song){
    this.playList = playList;
    this.song = song;
}
@Override
public String toString() {
    return "CurrentSong [playList=" + playList + ", song=" + song + "]";
}

    
}
