package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepositoryImpl implements SongRepository{
    public Map<String,Song> songMap;
    private Integer index=0;
    public SongRepositoryImpl(){
        songMap = new HashMap<String, Song>();
    }
    
    public SongRepositoryImpl(Map<String, Song> songMap) {
        this.songMap = songMap;
        this.index = songMap.size();
    }

    @Override
    public Song save(Song entity) {
        // TODO Auto-generated method stub
        if (entity.getId() == null) {
            index++;
            Song s = new Song(Integer.toString(index), entity.getTitle(), entity.getGenre(), entity.getAlbumName(),
                    entity.getAlbumArtist(), entity.getFeatureArtists());
            songMap.put(s.getId(), s);
            return s;
        }
        songMap.put(entity.getId(), entity);
        return entity;   
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return songMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return songMap.containsKey(id);
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        if(entity.getId() == null){
            return;
        }
        songMap.remove(entity.getId());
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        if(id!=null){
            songMap.remove(id);
            }
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return songMap.values().stream().count();
    }
    
}
