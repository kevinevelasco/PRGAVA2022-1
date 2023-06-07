package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayList implements Serializable {
    private static Long nextId = 0L;

    private  Long id;
    private  String name;
    private List<Content> contentList;

    public PlayList(Long id, String name) {
        this.id = getNextId();
        this.name = name;
        this.contentList = new ArrayList<>();
    }

    private static Long getNextId() { //se declara un método estático o de clase llamado  getNextId, que va a incrementar en 1 el id
        Long next = ++nextId;
        return next;
    }
    public PlayList(){

    }

    public void addContent(Song song){
        contentList.add(song);
    }

    public void addContent(Content content){
        contentList.add(content);
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

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }



}
