package com.company.exceptionshelpers;

import com.company.Client;
import com.company.Podcast;
import com.company.Producer;
import com.company.Song;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler {
    public static void saveCurrentStatusOnAFile(List<Song> songs, String fileNameSongs, List<Client> clients, String fileNameClients, List<Podcast> podcasts, String fileNamePodcasts) throws IOException {
        File fileS = new File(fileNameSongs);
        File fileC = new File(fileNameClients);
        File fileP = new File(fileNamePodcasts);

        try (FileOutputStream fos = new FileOutputStream(fileS);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            //se guarda elemento por elemento, se lee de forma diferente
            for (Song song : songs) {
                oos.writeObject(song);
            }
        }
        System.out.println("\nSongs.dat has been created successfully.");

        try (FileOutputStream fos = new FileOutputStream(fileP);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            //se guarda elemento por elemento, se lee de forma diferente
            for (Podcast podcast : podcasts) {
                oos.writeObject(podcast);
            }
        }
        System.out.println("\nPodcasts.dat has been created successfully.");

        try (FileOutputStream fos = new FileOutputStream(fileC);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            for (Client client : clients){
                oos.writeObject(client);
            }
        }
        System.out.println("\nClients.dat has been created successfully.");
    }

    public static void saveCurrentStatusOnAFile(String fileNameProducers, Map<Long, Producer> producers) throws IOException {
        File fileP = new File(fileNameProducers);

        try (FileOutputStream fos = new FileOutputStream(fileP);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(Map.Entry<Long, Producer> map : producers.entrySet()){
                oos.writeObject(map.getValue());
            }
        }
        System.out.println("\nProducersDB.dat has been created successfully.");
    }


    public static  List<Song> readSongsFromFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        List<Song> songs = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while(true) {
                Song song = (Song) ois.readObject();
                songs.add(song);
            }
        }catch (EOFException e) {
            return songs;
        }
    }
    public static  List<Podcast> readPodcastsFromFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        List<Podcast> podcasts = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while(true) {
                Podcast podcast = (Podcast) ois.readObject();
                podcasts.add(podcast);
            }
        }catch (EOFException e) {
            return podcasts;
        }
    }

    public static  List<Client> readClientsFromFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        List<Client> clients = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while(true) {
                Client client = (Client) ois.readObject();
                clients.add(client);
            }
        }catch (EOFException e) {
            return clients;
        }
    }

    public static List <Producer> readProducersFromFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        List<Producer> producers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                Producer producer = (Producer) ois.readObject();
                producers.add(producer);
            }
        } catch (EOFException e) {
            return producers;
        }
    }

    public static Map<Long, Producer> readFileProducers(String fileName) throws IOException,  ClassCastException{
        File file = new File(fileName);
        List<String> lines = Files.readAllLines(file.toPath());
        Producer auxProducer;
        Map<Long, Producer> producers = new HashMap<>();
        for(String line : lines) {
            String[] producerData = line.split(";");
            String name = producerData[0];
            String nickName = producerData[1];

            auxProducer = new Producer(name,nickName);
            producers.put(auxProducer.getId(),auxProducer);
        }
        return producers;
    }
}
