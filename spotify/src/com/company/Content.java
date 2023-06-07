package com.company;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Content implements Serializable, Playable {
    private static Long nextId = 0L; //variable estática que se va a usar en un método de clase - que también es estática.
    protected Long id;
    protected String name;
    protected Integer duration;
    protected Integer numberOfDownloads;
    protected Set<Producer> producers;


    public Content (Long id, String name, Integer duration){
        this.id = getNextId();
        this.name = name; //this -> instancia nueva que estoy creando, El objeto nuevo que estoy creando o usando.
        this.duration = duration;
        this.numberOfDownloads = 0;
        this.producers = new HashSet<>();
    }

     public Content(){
    }

    private static Long getNextId() { //se declara un método estático o de clase llamado  getNextId, que va a incrementar en 1 el id
        return ++nextId;
    }

    public void addDownload() {
        this.numberOfDownloads++;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(Integer numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public Set<Producer> getProducers() {
        return producers;
    }

    public void setProducers(Set<Producer> producers) {
        this.producers = producers;
    }
}
