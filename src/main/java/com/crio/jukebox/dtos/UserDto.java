package com.crio.jukebox.dtos;

import com.crio.jukebox.entities.User;

public class UserDto {
    public String name;
    public String id;

    public UserDto(String id,String name){
        this.id=id;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public UserDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", name=" + name + "]";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
