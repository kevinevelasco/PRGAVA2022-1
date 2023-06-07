package com.company;

public class StandardClient extends Client {
    private PlayList playList;
    public StandardClient(Long id, String user, String password, String name, String lastName, Integer age) {
        super(id, user, password, name, lastName, age);
        this.playList = new PlayList(1l, "Default");
    }

    public boolean addContentToPlayList(Song song){
        playList.addContent(song);
        return true;
    }

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }
}
