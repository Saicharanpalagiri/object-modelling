package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;

public class UserRepositoryImpl implements UserRepository{
    private Map<String, User> userMap;
    private Integer index = 0;

    public UserRepositoryImpl(){
        userMap = new HashMap<String, User>();
    }

    public UserRepositoryImpl(Map<String, User> userMap) {
        this.userMap = userMap;
        this.index = userMap.size();
    }

    @Override
    public User save(User entity) {
        if (entity.getId() == null) {
            index++;
            User u = new User(Integer.toString(index), entity.getName());
            userMap.put(u.getId(), u);
            return u;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }
    
    @Override
    public List<User> findAll() {
        return userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return userMap.values().stream().filter(u->u.getId()==id).findFirst();
        // return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        User temp = userMap.get(id);
        if(temp == null){
            return false;
        }
        return true;
    }

    @Override
    public void delete(User entity) {
        if(entity.getId() == null){
            return;
        }
        userMap.remove(entity.getId());
    }

    @Override
    public void deleteById(String id) {
        if(id == null){
            return;
        }
        userMap.remove(id);
    }

    @Override
    public long count() {
        return userMap.values().stream().count();
    }
}
