package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void checkIfPlaylistExists_ShouldReturnTrue_GivenPlaylist(){
        String id = "1";
        String name = "MY_PLAYLIST_1";
        List<Artist> featureArtists = new ArrayList<Artist>(){
            {
                add(new Artist("Ed Sheeran"));
                add(new Artist("Cardi.B"));
            }
        };
        List<Song> songs =  new ArrayList<Song>(){
            {
            add(new Song("1" ,"South of the Border", "Pop", new Album("No.6 Collaborations Project"), featureArtists.get(0), featureArtists));
            }
        };
        PlayList playlist = new PlayList(id, name, songs);
        User user =  new User(id, name, new ArrayList<PlayList>(){{add(playlist);}});
        Assertions.assertTrue(user.checkIfPlaylistExist(playlist));
    }

    @Test
    @DisplayName("checkIfPlaylistExists should Return False If No Playlist is Found")
    public void checkIfContestExists_ShouldReturnFalse_GivenContest(){
         //Arrange
         String id = "1";
         String name = "MY_PLAYLIST_1";
         List<Artist> featureArtists = new ArrayList<Artist>(){
             {
                 add(new Artist("Ed Sheeran"));
                 add(new Artist("Cardi.B"));
             }
         };
         List<Song> songs =  new ArrayList<Song>(){
             {
             add(new Song("1" ,"South of the Border", "Pop", new Album("No.6 Collaborations Project"), featureArtists.get(0), featureArtists));
             }
         };
         PlayList playlist = new PlayList(id, name, songs);
         User user =  new User(id, name);
         Assertions.assertFalse(user.checkIfPlaylistExist(playlist));
 
    }
    
}
