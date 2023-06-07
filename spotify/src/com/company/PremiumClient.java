package com.company;

import java.util.ArrayList;
import java.util.List;

public class PremiumClient extends Client{
    private List<PlayList> playList;

    public PremiumClient(Long id, String user, String password, String name, String lastName, Integer age) {
        super(id, user, password, name, lastName, age);
        PlayList playlist1 = new PlayList(0L, "premium");
        this.playList = new ArrayList<>();
        playList.add(playlist1);

    }

    public boolean addContentToPlayList(Content content, String name){
        for (PlayList list : playList) {
            if (list.getName().equalsIgnoreCase(name)) {
                list.addContent(content);
            }
        }
        return true;
    }

    public List<PlayList> getPlayList() {
        return playList;
    }

    public void setPlayList(List<PlayList> playList) {
        this.playList = playList;
    }
    public void addNewPlayList(String name){
        PlayList auxPlay;
        auxPlay = new PlayList(1L, name);
        playList.add(auxPlay);
    }


}
