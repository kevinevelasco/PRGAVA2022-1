package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Producer implements Serializable {
    private static Long nextId = 0L; //variable estática que se va a usar en un método de clase - que también es estática.

    protected Long id;
    protected String name;
    protected String nickName;
    //private List<Song> producedSongs;
    //private List<Podcast> producedPodcasts;

    public Producer(String name, String nickName) {
        this.id = getNextId();
        this.name = name;
        this.nickName = nickName;
        //this.producedSongs = new ArrayList<Song>();
    }

    private static Long getNextId() { //se declara un método estático o de clase llamado  getNextId, que va a incrementar en 1 el id
        Long next = ++nextId;
        return next;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /*public void addSong(Song song) {
        this.producedSongs.add(song);
    }

    public void addPodcast(Podcast podcast) {
        this.producedPodcasts.add(podcast);
    }*/

    @Override
    public String toString() {
        return "Producer: " +
                "ID = " + id +
                ", Name = '" + name + '\'' +
                ", Nickname = '" + nickName + '\'' +
                '}';
    }
}
