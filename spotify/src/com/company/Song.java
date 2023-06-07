package com.company;

import java.io.Serializable;

public class Song extends Content implements Serializable { //la clase hija -> song; extiende la clase papá -> content. Herencia.
    private String artist;
    private String genre;           //los atributos siempre serán private.
    private String album;
    //private Integer releaseYear;


    public Song(Long id, String name, String artist, String genre, Integer duration, String album) {
        super(id, name, duration);
        this.artist = artist;
        this.genre = genre;
        this.album = album;
    }

    @Override
    public void play() {
        System.out.println("Playing song " + getName());
    }

    public void addProducer(Producer producer) {
        this.producers.add(producer);
       // producer.addSong(this);

    }

    public String showCredits(){
        return "\nProducers: " + this.producers.toString() + " .\n" +
                "Artist: " + getArtist();
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(Integer numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }


}
