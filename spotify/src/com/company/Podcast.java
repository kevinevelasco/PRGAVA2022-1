package com.company;

import java.io.Serializable;

public class Podcast extends Content implements Serializable {
    private String author;
    private String category;

    public Podcast(Long id, String name, Integer duration, String author, String category) {
        super(id, name, duration);
        this.author = author;
        this.category = category;
    }

    public void addProducer(Producer producer) {
        this.producers.add(producer);
        //producer.addPodcast(this);

    }

    public String showCredits(){
        return "\nProducers: " + this.producers.toString() + " .\n" +
                "Author: " + getAuthor();
    }

    public void play() {
        System.out.println("Playing podcast " + getName());
    }

    public void addDownload() {
        this.numberOfDownloads++;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
