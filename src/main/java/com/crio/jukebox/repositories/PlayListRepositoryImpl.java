package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.crio.jukebox.entities.PlayList;

public class PlayListRepositoryImpl implements PlaylistRepository{
    private final Map<String, PlayList> playlistMap;
    private Integer index = 0;

    public PlayListRepositoryImpl(){
        playlistMap=new HashMap<String,PlayList>();
    }

    public PlayListRepositoryImpl(Map<String, PlayList> playlistMap, Integer index) {
        this.playlistMap = playlistMap;
        this.index = playlistMap.size();
    }

    @Override
    public PlayList save(PlayList entity) {
        // TODO Auto-generated method stub
        if (entity.getId() == null) {
            index++;
            PlayList p = new PlayList(Integer.toString(index), entity.getName(), entity.getSongs());
            playlistMap.put(p.getId(), p);
            return p;
        }
        playlistMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<PlayList> findAll() {
        // TODO Auto-generated method stub
        return playlistMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<PlayList> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return playlistMap.containsKey(id);
    }

    @Override
    public void delete(PlayList entity) {
        // TODO Auto-generated method stub
        if(entity.getId() == null){
            return;
        }
        playlistMap.remove(entity.getId());
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        if(id == null){
            return;
        }
        playlistMap.remove(id);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return playlistMap.values().stream().count();
    }
    
}
