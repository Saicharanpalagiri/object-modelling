package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Testing playList")
public class PlayListTest {

    @Test
    @DisplayName("checkIfSongExists should Return true If Song is Found")
    public void checkIfPlaylistExists_ShouldReturnTrue_GivenPlaylist(){
        //Arrange
        String id = "1";
        String name = "MY_PLAYLIST_1";
        List<Artist> featureArtists = new ArrayList<Artist>(){
            {
                add(new Artist("Ed Sheeran"));
                add(new Artist("Cardi.B"));
            }
        };
        Song s1 = new Song("1" ,"South of the Border", "Pop", new Album("No.6 Collaborations Project"), featureArtists.get(0), featureArtists);
        Song s2 = new Song("2", "I Dont'Care", "Pop", new Album("No.6 Collaborations Project"), featureArtists.get(0), featureArtists);
        PlayList playlist = new PlayList(id, name, new ArrayList<Song>(){{ add(s1);  }});

        //Assert
        Assertions.assertTrue(playlist.checkIfSongExists(s1));
        Assertions.assertFalse(playlist.checkIfSongExists(s2));

    }
    
}
