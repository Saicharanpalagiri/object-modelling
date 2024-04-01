package com.crio.jukebox.entities;

public class Artist extends BaseEntity {

    private String name;
    public Artist(String name){
        this.name=name;
    }
    @Override
    public String toString() {
        return "Artist [name=" + name + "]";
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Artist other = (Artist) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    
    
}
