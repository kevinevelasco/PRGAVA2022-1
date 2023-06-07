//Proyecto 3. Kevin Estiven Velasco Pinto.
package com.company;
import com.company.exceptions.*;
import com.company.exceptionshelpers.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Integer songPosition = 0;
    private static Integer podcastPosition = 0;
    private static Integer clientPosition = 0;
    private static Integer producerPosition = 0;
    private static Integer contentPosition = 0;


    public static void main (String[] args) throws IOException, ClassNotFoundException {

        List <Song> songList = new ArrayList<>();
        List <Podcast> podcastList = new ArrayList<>();
        List <Client> clientList = new ArrayList<>();
        List <String> userList = new ArrayList<>();
        List<Content> contentList = new ArrayList<>();
        Map<Long,Producer> producerMap = new HashMap<>();

        Scanner scanner = new Scanner (System.in);

        Integer input = 0, inputTwo = 0;
        InputHelper in = new InputHelper();
        UsernameHelper u = new UsernameHelper();
        String newInput, newInputTwo;

        do{

            System.out.println("\n\nWELCOME TO SPOTIFY: ");
            System.out.println("1. Add new content (songs or podcasts) to DB.");
            System.out.println("2. Add new user to DB.");
            System.out.println("3. List songs by album.");
            System.out.println("4. List songs by artist.");
            System.out.println("5. Download a song by a registered user, adding it to your\n" +
                    "   own PlayList of downloaded songs and increasing the number of\n" +
                    "   downloads of the song.");
            System.out.println("6. Search a song giving a keyword.");
            System.out.println("7. Delete a song from the DB by giving the ID.");
            System.out.println("8. Save current Clients and Content DB status both of them on a .dat file. ");
            System.out.println("9. Load previous Clients and Content DB status. ");
            System.out.println("10. Add a new producer to DB. ");
            System.out.println("11. Show the credits from a song or a podcast. ");
            System.out.println("12. Show producers. ");
            System.out.println("13. Save current producers on a binary file. ");
            System.out.println("14. Load previous Producer DB. ");
            System.out.println("15. Do you want to listen a song? :)");

            System.out.println("16. EXIT");

            System.out.println("Select your option: ");
            newInput = scanner.nextLine();

            do{
                try{
                    in.validarEntrada(newInput);
                    input = Integer.parseInt(newInput);
                    break;
                }catch(WordInputInNumberInputException e){
                    System.out.println("\nThe option given is wrong because: "
                            +e.getMessage());
                    System.out.println("Please type your new option");
                    newInputTwo = scanner.nextLine();
                    newInput = newInputTwo;
                }
            }while(true);


            switch(input){
                case 1:
                    if (addNewContentToDB(songList, podcastList, producerMap, contentList)){
                        System.out.println("Content was added to DB\n");
                        System.out.println("******************************************");
                        System.out.println("Updated database content...");
                        System.out.println("******************************************");
                        displayContent(contentList);

                    }
                    else{
                        System.out.println("\nContent was not added to DB\n");
                    }
                    break;
                case 2:
                    if (addNewUserToDB(clientList, userList)){
                        System.out.println("\nClient was added to DB!\n");
                    }
                    else{
                        System.out.println("Client was not added to DB");
                    }
                    break;
                case 3:
                {
                    System.out.println("\nPlease type the album from where you want to list the songs");

                    String album = scanner.nextLine();
                    album = stringsExceptions(album);

                    System.out.println("\nThe songs of the album " + album + " are:");
                    ArrayList <String> song = listSongsByAlbum(album, songList);
                    displayList(song);
                }
                break;

                case 4:
                {
                    System.out.println("\nPlease type the artist from where you want to list the songs");
                    String artist = scanner.nextLine();
                    artist = stringsExceptions(artist);

                    System.out.println("\nThe songs of the artist " + artist + " are:");
                    ArrayList <String> song = listSongsByArtist(artist, songList);
                    displayList(song);

                }
                break;

                case 5:
                {
                    System.out.println("Please type your username (already registered) and the content (song or podcast) ID\n" +
                            "from the content already registered that you want to add to your list. \n");
                    System.out.println("Username ");
                    String username = scanner.nextLine();

                    for (int b = 0; b < clientPosition; b++){
                        if(username.equals(clientList.get(b).getUser())){
                            if (clientList.get(b).getClass().getSimpleName().equals("StandardClient")){
                                System.out.println("\nBecause you are a Standard Client you only have the chance to add songs on ONE playlist. \n");
                                System.out.println("\n" +
                                        "Song ID ");

                                Long id;
                                String idAux, idAuxTwo;
                                idAux = scanner.nextLine();
                                do{
                                    try{
                                        in.validarEntrada(idAux);
                                        id = Long.parseLong(idAux);
                                        break;
                                    }catch(WordInputInNumberInputException e){
                                        System.out.println("\nThe option given is wrong because: "
                                                +e.getMessage());
                                        System.out.println("Please type your new option");
                                        idAuxTwo = scanner.nextLine();
                                        idAux = idAuxTwo;
                                    }
                                }while(true);

                                if (addContentToStandardPlayList(username, id, clientList, songList)){
                                    System.out.println("The song with the ID " + id + " was successfully added to "+ username +"'s list");
                                    System.out.println("\nSongs in the playlist of the client selected: ");
                                    for (int i = 0; i < clientPosition; i++){
                                        if (clientList.get(i).getUser().equals(username)){
                                            System.out.println("\nPlaylist: " +  ((StandardClient) clientList.get(i)).getPlayList().getName());
                                            for( int m = 0; m < songPosition; m++){
                                                if (clientList.get(i).getClass().getSimpleName().equals("StandardClient")){
                                                    for(int j = 0; j < ((StandardClient) clientList.get(i)).getPlayList().getContentList().size(); j++) {
                                                        if (((StandardClient) clientList.get(i)).getPlayList().getContentList().get(j).getId().equals(songList.get(m).getId())) {
                                                            System.out.println("ID: "+ ((StandardClient) clientList.get(i)).getPlayList().getContentList().get(j).getId() + ". Name: " + ((StandardClient) clientList.get(i)).getPlayList().getContentList().get(j).getName() + ". Number of downloads: " + ((StandardClient) clientList.get(i)).getPlayList().getContentList().get(j).getNumberOfDownloads());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                else System.out.println("The song was not added to the list. Try again.");
                            } else if (clientList.get(b).getClass().getSimpleName().equals("PremiumClient")){
                                System.out.println("\nBecause you are a Premium client, you have the chance to add or a podcast or a song in as many playlists as you want.");

                                    System.out.println("\n" +
                                            "Content ID ");

                                    Long id;
                                    String idAux, idAuxTwo;
                                    idAux = scanner.nextLine();
                                    do{
                                        try{
                                            in.validarEntrada(idAux);
                                            id = Long.parseLong(idAux);
                                            break;
                                        }catch(WordInputInNumberInputException e){
                                            System.out.println("\nThe option given is wrong because: "
                                                    +e.getMessage());
                                            System.out.println("Please type your new option");
                                            idAuxTwo = scanner.nextLine();
                                            idAux = idAuxTwo;
                                        }
                                    }while(true);

                                    if (addContentToPlayList(username, id, clientList, contentList)){
                                        System.out.println("The content with the ID " + id + " was successfully added to "+ username +"'s list");
                                        System.out.println("\nContent in the list of the client selected: ");
                                        for (int i = 0; i < clientPosition; i++){
                                            if (clientList.get(i).getUser().equals(username)){
                                                    if (clientList.get(i).getClass().getSimpleName().equals("PremiumClient")) {
                                                        for (int j = 0; j < ((PremiumClient) clientList.get(i)).getPlayList().size(); j++) {
                                                            System.out.println("\nPlaylist: " +  ((PremiumClient) clientList.get(i)).getPlayList().get(j).getName());
                                                            for (int k = 0; k < ((PremiumClient) clientList.get(i)).getPlayList().get(j).getContentList().size(); k++) {
                                                                    System.out.println("ID: " + ((PremiumClient) clientList.get(i)).getPlayList().get(j).getContentList().get(k).getId() + ". Name: " + ((PremiumClient) clientList.get(i)).getPlayList().get(j).getContentList().get(k).getName() + ". Number of downloads: " + ((PremiumClient) clientList.get(i)).getPlayList().get(j).getContentList().get(k).getNumberOfDownloads());
                                                            }
                                                        }
                                                    }
                                            }
                                        }
                                    } else System.out.println("Content was not added to the playlist. Try again.");
                            }
                        } //condición general del usuario
                    } //cierre del for del clientList
                }
                printClient(clientList);
                break;

                case 6: {
                    System.out.println("Please type the keyword from which you want to search the song: ");
                    String keyword = scanner.nextLine();

                    do {

                        System.out.println("\nPlease choose the way how do you want to search the song:");
                        System.out.println("1. Search only the names.");
                        System.out.println("2. Search only the artists.");
                        System.out.println("3. Search only the genres.");
                        System.out.println("4. Search only the albums.");
                        System.out.println("5. Search a mix of all.");
                        System.out.println("6. Go back to the menu.");

                        System.out.println("Select your option: ");

                        String inputTwoAux, inputTwoAuxTwo;
                        inputTwoAux = scanner.nextLine();

                        do{
                            try{
                                in.validarEntrada(inputTwoAux);
                                inputTwo = Integer.parseInt(inputTwoAux);
                                break;
                            }catch(WordInputInNumberInputException e){
                                System.out.println("\nThe option given is wrong because: "
                                        +e.getMessage());
                                System.out.println("Please type your new option");
                                inputTwoAuxTwo = scanner.nextLine();
                                inputTwoAux = inputTwoAuxTwo;
                            }
                        }while(true);

                        switch (inputTwo){

                            case 1: { //que devuelva un array con las canciones que encontró
                                String forma = "Names";
                                List <String> search = searchByGivenKeyword(keyword, songList, forma); //revisar que se hace en caso de que devuelva un vector vacío -> no encontró nada
                                System.out.println("\nBecause you chose Name parameter to search.");
                                System.out.println("Names found by your given keyword: \n");
                                displayList(search);
                            }
                            break;

                            case 2: {
                                String forma = "Artists";
                                List <String> search = searchByGivenKeyword(keyword, songList, forma);
                                System.out.println("\nBecause you chose Artist parameter to search.");
                                System.out.println("Artists found by your given keyword: \n");
                                displayList(search);
                            }
                            break;

                            case 3: {
                                String forma = "Genres";
                                System.out.println("\nBecause you chose Genre parameter to search.");
                                System.out.println("Genres found by your given keyword: \n");
                                List <String> search = searchByGivenKeyword(keyword, songList, forma);
                                displayList(search);
                            }
                            break;

                            case 4: {
                                String forma = "Albums";
                                List <String> search = searchByGivenKeyword(keyword, songList, forma);
                                System.out.println("\nBecause you chose Album parameter to search.");
                                System.out.println("Albums found by your given keyword: \n");
                                displayList(search);
                            }
                            break;

                            case 5: {
                                String forma = "All";
                                System.out.println("\nBecause you chose Mix of All parameter to search.");
                                System.out.println("Mix of All found by your given keyword: ");
                                searchAllByGivenKeyword(keyword, songList, forma);

                            }
                            break;

                            default: if (inputTwo == 6){
                                System.out.println("\nGoing back to the menu...\n");
                                break;
                            }
                                if (inputTwo >= 7){
                                    System.out.println("\nThe option given does not exist. Try again.");
                                    break;
                                }

                        }

                    } while(inputTwo != 6);
                }
                break;

                case 7: {
                    if(deleteSongFromDB(contentList, songList, podcastList)){
                        System.out.println("\nContent was deleted from DB\n");
                        displayContent(contentList);
                    } else{
                        System.out.println("\nContent was not deleted from DB\n");
                    }
                }
                break;

                case 8: {
                    String fileNameSongs = "Songs.dat";
                    String fileNameClients = "Clients.dat";
                    String fileNamePodcasts = "Podcasts.dat";

                    //Writing the podcasts and the clients both to files
                    try {
                        FileHandler.saveCurrentStatusOnAFile(songList,fileNameSongs,clientList,fileNameClients,podcastList,fileNamePodcasts);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

                case 9: {
                    String fileNameSongs = "Songs.dat";
                    String fileNameClients = "Clients.dat";
                    String fileNamePodcasts = "Podcasts.dat";

                    try {
                        List<Song> songsFromFile = FileHandler.readSongsFromFile(fileNameSongs);
                        for(int i = 0; i < songsFromFile.size(); i++){
                            songsFromFile.get(i).setId((long)contentPosition+1L);
                            contentPosition++;
                            songPosition++;
                        }
                        songList.addAll(songsFromFile);
                        contentList.addAll(songsFromFile);
                        System.out.println("\nThe Song DB was uploaded successfully \n");


                        List<Podcast> podcastsFromFile = FileHandler.readPodcastsFromFile(fileNamePodcasts);
                        for(int i = 0; i < podcastsFromFile.size(); i++){
                            podcastsFromFile.get(i).setId((long)contentPosition+1L);
                            contentPosition++;
                            podcastPosition++;
                        }
                        podcastList.addAll(podcastsFromFile);
                        contentList.addAll(podcastsFromFile);
                        System.out.println("\nThe Podcast DB was uploaded successfully \n");

                        List<Client> clientsFromFile = FileHandler.readClientsFromFile(fileNameClients);
                        clientList.addAll(clientsFromFile);
                        clientPosition += clientsFromFile.size();
                        for (int i = 0; i < clientsFromFile.size(); i++){
                            String user = clientsFromFile.get(i).getUser();
                            do{
                                try{
                                    u.validarUsername(user, "^[A-Za-z][A-Za-z0-9]*(?:_[A-Za-z0-9]+)*$", userList, clientPosition-clientsFromFile.size());
                                    clientsFromFile.get(i).setUser(user); //se cambia ese usuario directamente en la base de datos.
                                    userList.add(user); //se le van a agregar los usuarios a la lista de usuarios, para que la función de agregar usuarios no falle.
                                    break;
                                } catch(IncorrectUsernameException e){
                                    System.out.println("The user " + user + " from the DB cannot be registered because: "
                                            +e.getMessage());
                                    System.out.println("Please change that username: ");
                                    //System.out.println("Would you like to change the rest of the attributes from that client? (y/n): "); -> porque se cambiará el usuario.
                                    String newUser = scanner.nextLine();
                                    user = newUser;
                                }
                            }while(true);
                        }
                        System.out.println("\nThe Client DB was uploaded successfully.");
                        printClient(clientList);
                    }
                    catch (IOException | ClassNotFoundException e){
                        e.printStackTrace();
                    }
                    System.out.println("******************************************");
                    System.out.println("Updated database content...");
                    System.out.println("******************************************");
                    displayContent(contentList);
                }
                break;

                case 10: {
                    if (addNewProducerToDB(producerMap)){
                        System.out.println("Producer was added to DB\n");
                    }
                    else{
                        System.out.println("\nProducer was not added to DB\n");
                    }
                }
                break;

                case 11: {
                    Long id;
                    System.out.println("\nPlease type the id from which you want to see the credits: ");
                    String idAux = scanner.nextLine();
                    do{
                        try{
                            in.validarEntrada(idAux);
                            id = Long.parseLong(idAux);
                            break;
                        }catch(WordInputInNumberInputException e){
                            System.out.println("\nThe option given is wrong because: "
                                    +e.getMessage());
                            System.out.println("Please type your new option");
                            idAux = scanner.nextLine();
                        }
                    }while(true);

                    showCredits(id,contentList);
                }
                break;

                case 12:{
                    System.out.println("\nPrinting producers...");
                    for (Map.Entry<Long, Producer> map : producerMap.entrySet()) {
                        // Printing keys
                        System.out.println("ID: " + map.getKey() + ". "
                                +"\nName: "   + map.getValue().getName() + ". "
                                +"\nNickname: " + map.getValue().getNickName() + ". \n") ;

                    }
                }
                break;

                case 13: {
                    String fileNameProducers = "ProducersDB.dat";
                    //Writing the Producers DB into a .dat
                    try {
                        FileHandler.saveCurrentStatusOnAFile(fileNameProducers, producerMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

                case 14: {
                    String fileNameProducers = "ProducersDB.dat"; //it has written the object
                    String fileNameProducersWithDelimiter = "Producers.csv"; //it has written the producers separated by ";"
                    System.out.println("\nDo you want to load the producers from the file separated by ';'? Type y/n.");
                    String answer = scanner.nextLine();

                    if (answer.equalsIgnoreCase("n")){
                        System.out.println("So you want to load the producers by the binary file.");
                        Integer pos = producerPosition + 1;
                        List<Producer> producersFromFile = FileHandler.readProducersFromFile(fileNameProducers);
                        if(producerMap.size() == 0){
                            Map<Long, Producer> mapa = producersFromFile.stream().collect(Collectors.toMap(x -> x.getId(), x -> x));
                            producerMap.putAll(mapa);
                        } else {
                            for(int i = 0; i < producersFromFile.size(); i++){
                               producersFromFile.get(i).setId((long) pos);
                               pos++;
                            }
                            Map<Long, Producer> mapa = producersFromFile.stream().collect(Collectors.toMap(x -> x.getId(), x -> x));
                            producerMap.putAll(mapa);
                        }
                        producerPosition = producerMap.size();

                        System.out.println("\nThe Producer DB was uploaded successfully \n");
                        for (Map.Entry<Long, Producer> e : producerMap.entrySet()){
                            // Printing key-value pairs
                            producerMap.get(e.getKey()).setId(producerMap.get(e.getKey()).getId());
                        }
                    } else if(answer.equalsIgnoreCase("y")){
                        System.out.println("So you want to load the producers by the text file with delimiter ';'.");
                        producerMap.putAll(FileHandler.readFileProducers(fileNameProducersWithDelimiter));
                        producerPosition = producerMap.size();
                    } else{
                        System.out.println("\nWrong answer. Try again.");
                    }
                    for (Map.Entry<Long, Producer> map : producerMap.entrySet()) {
                            System.out.println("ID: " + map.getKey() + ". "
                                    +"\nName: "   + map.getValue().getName() + ". "
                                    +"\nNickname: " + map.getValue().getNickName() + ". \n") ;
                        }
                    }
                break;

                case 15: {
                    displayContent(contentList);
                    System.out.println("Please type the content ID from you want to listen to: ");
                    Long id;
                    String idAux, idAuxTwo;
                    idAux = scanner.nextLine();
                    do{
                        try{
                            in.validarEntrada(idAux);
                            id = Long.parseLong(idAux);
                            break;
                        }catch(WordInputInNumberInputException e){
                            System.out.println("\nThe option given is wrong because: "
                                    +e.getMessage());
                            System.out.println("Please type your new option");
                            idAuxTwo = scanner.nextLine();
                            idAux = idAuxTwo;
                        }
                    }while(true);

                    for(Content content: contentList){
                        if(content.getId().equals(id)){
                            content.play();
                        }
                    }
                }

                default: if (input == 16){
                    System.out.println("\nGoodbye, have a good day!");
                    break;
                }
                    if (input >= 17){
                        System.out.println("\nThe option given does not exist. Try again.");
                        break;
                    }

            }
        } while(input != 16);
    }
    private static boolean addNewContentToDB(List<Song> songsDB, List<Podcast> podcastsDB, Map<Long,Producer> producerMap, List<Content> contentDB) {
        Scanner scanner = new Scanner(System.in);
        Song auxSong;
        Podcast auxPodcast;
        InputHelper in = new InputHelper();

        String name, artist, genre, album, author, category, input;
        Integer duration;
        String durationAux, durationAuxTwo;
        Long id;
        String idAux, idAuxTwo;

        System.out.println("If you want to add a podcast, please type 'podcast', but if you want to add a song, please type 'song': ");
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("song")){
            System.out.println("Please enter all the values of " +
                    "the song you want to add: ");
            System.out.println("Type the name of your song: ");
            name = scanner.nextLine();
            name = stringsExceptions(name);

            System.out.println("Type the artist of your song: ");
            artist = scanner.nextLine();
            artist = stringsExceptions(artist);

            System.out.println("Type the genre of your song: ");
            genre = scanner.nextLine();
            genre = stringsExceptions(genre);

            System.out.println("Type the duration of your song (in seconds): ");
            durationAux = scanner.nextLine();
            do{
                try{
                    in.validarEntrada(durationAux);
                    duration = Integer.parseInt(durationAux);
                    break;
                }catch(WordInputInNumberInputException e){
                    System.out.println("\nThe option given is wrong because: "
                            +e.getMessage());
                    System.out.println("Please type your new option");
                    durationAuxTwo = scanner.nextLine();
                    durationAux = durationAuxTwo;
                }
            }while(true);

            System.out.println("Type the name of the album of your song: ");
            album = scanner.nextLine();
            album = stringsExceptions(album);

            auxSong = new Song(0L, name, artist, genre, duration, album);
            auxSong.setId(contentPosition+1L);
            songsDB.add(auxSong);
            songPosition++;
            contentDB.add(auxSong);
            contentPosition++;

            String answer;
            do {
                System.out.println("Please type Producer ID");
                Long producerId = Long.parseLong(scanner.nextLine());
                if (producerMap.containsKey(producerId)) {
                    auxSong.addProducer(producerMap.get(producerId));
                    System.out.println("would you like to add another producer? (y/n)");
                    answer = scanner.nextLine();

                } else {
                    System.out.println("Producer not found");
                    answer = "y";
                }

            } while (answer.equalsIgnoreCase("y"));
            return true;
        } else if (input.equalsIgnoreCase("podcast")){
            System.out.println("Please enter all the values of " +
                    "the podcast you want to add: ");
            System.out.println("Type the name of the podcast: ");
            name = scanner.nextLine();
            name = stringsExceptions(name);

            System.out.println("Type the author of your podcast: ");
            author = scanner.nextLine();
            author = stringsExceptions(author);


            System.out.println("Type the duration of your podcast (in seconds): ");
            durationAux = scanner.nextLine();
            do{
                try{
                    in.validarEntrada(durationAux);
                    duration = Integer.parseInt(durationAux);
                    break;
                }catch(WordInputInNumberInputException e){
                    System.out.println("\nThe option given is wrong because: "
                            +e.getMessage());
                    System.out.println("Please type your new option");
                    durationAuxTwo = scanner.nextLine();
                    durationAux = durationAuxTwo;
                }
            }while(true);

            System.out.println("Type the name of the category of your podcast: ");
            category = scanner.nextLine();
            category = stringsExceptions(category);
            auxPodcast = new Podcast(0L, name, duration, author, category);

            auxPodcast.setId(contentPosition+1L);
            podcastsDB.add(auxPodcast);
            podcastPosition++;
            contentDB.add(auxPodcast);
            contentPosition++;


            String answer;
            do {
                System.out.println("Please type Producer ID");
                Long producerId = Long.parseLong(scanner.nextLine());
                if (producerMap.containsKey(producerId)) {
                    auxPodcast.addProducer(producerMap.get(producerId));
                    System.out.println("would you like to add another producer? (y/n)");
                    answer = scanner.nextLine();

                } else {
                    System.out.println("Producer not found");
                    answer = "y";
                }

            } while (answer.equalsIgnoreCase("y"));
            return true;
        }
        else {
            return false;
        }
    }
    private static boolean addNewUserToDB(List<Client> clientsDB, List<String> users){
        Scanner scanner = new Scanner(System.in);
        Client auxClient;
        String pattern = "^[A-Za-z][A-Za-z0-9]*(?:_[A-Za-z0-9]+)*$";

        UsernameHelper u = new UsernameHelper();
        InputHelper in = new InputHelper();
        PasswordHelper p = new PasswordHelper();
        AgeHelper a = new AgeHelper();

        String user, newUser;
        String password, passwordAux;
        String name;
        String lastName;
        Integer age;
        String ageAux, ageAuxTwo;

        System.out.println("\nDo you want to be a Standard client or a Premium client?");
        System.out.println("Remember, a Premium client has a songs list and a podcasts list, but a Standard client only has a songs list.");
        System.out.println("Type Standard or Premium: ");
        String type = scanner.nextLine();
        if (type.equalsIgnoreCase("standard")){
            System.out.println("\nPlease enter all the values of " +
                    "the client information: ");
            System.out.println("Type your username: ");
            user = scanner.nextLine();

            do{
                try{
                    u.validarUsername(user, pattern, users, clientPosition);
                    break;
                } catch(IncorrectUsernameException e){
                    System.out.println("The user cannot be registered because: "
                            +e.getMessage());
                    System.out.println("Please type your new username");
                    newUser = scanner.nextLine();
                    user = newUser;
                }
            }while(true);

            System.out.println("\nRemember, the password given must contain \n" +
                    "- at least 8 characters\n" +
                    "- at least 1 uppercase letter, 1 lowercase letter, and 1 number\n" +
                    "- Can contain special characters\n");
            System.out.println("Type your password: ");

            password = scanner.nextLine();
            do{
                try{
                    p.validarEntrada(password);
                    break;
                }catch(PasswordExceptions e){
                    System.out.println("\nThe option given is wrong because: "
                            +e.getMessage());
                    System.out.println("Please type your new option");
                    passwordAux = scanner.nextLine();
                    password = passwordAux;
                }
            }while(true);

            System.out.println("Type your name(s): ");
            name = scanner.nextLine();
            name = namesAndLastNamesExceptions(name);

            System.out.println("Type your last names: ");
            lastName = scanner.nextLine();
            lastName = namesAndLastNamesExceptions(lastName);

            System.out.println("Type your age: ");
            ageAux = scanner.nextLine();
            do{
                try{
                    in.validarEntrada(ageAux);
                    age = Integer.parseInt(ageAux);
                    a.validarEntrada(age); //para revisar si es mayor de 13 años para crear la cuenta.
                    break;
                }catch(WordInputInNumberInputException e){
                    System.out.println("\nThe option given is wrong because: "
                            +e.getMessage());
                    System.out.println("Please type your new option");
                    ageAuxTwo = scanner.nextLine();
                    ageAux = ageAuxTwo;
                } catch (IncorrectAgeException e) {
                    System.out.println("\nThe option given is wrong because: "
                            +e.getMessage());
                    System.out.println("Please type your new option");
                    ageAuxTwo = scanner.nextLine();
                    ageAux = ageAuxTwo;
                }
            }while(true);
            auxClient = new StandardClient(0L, users.get(clientPosition), password, name, lastName, age);
            clientsDB.add(clientPosition, auxClient);
            clientPosition++;
            System.out.println("\nSo you are a Standard Client now!\n");
        } else if (type.equalsIgnoreCase("premium")){
            System.out.println("\nPlease enter all the values of " +
                    "the client information: ");
            System.out.println("Type your username: ");
            user = scanner.nextLine();

            do{
                try{
                    u.validarUsername(user, pattern, users, clientPosition);
                    break;
                } catch(IncorrectUsernameException e){
                    System.out.println("The user cannot be registered because: "
                            +e.getMessage());
                    System.out.println("Please type your new username");
                    newUser = scanner.nextLine();
                    user = newUser;
                }
            }while(true);

            System.out.println("\nRemember, the password given must contain \n" +
                    "- at least 8 characters\n" +
                    "- at least 1 uppercase letter, 1 lowercase letter, and 1 number\n" +
                    "- Can contain special characters\n");
            System.out.println("Type your password: ");

            password = scanner.nextLine();
            do{
                try{
                    p.validarEntrada(password);
                    break;
                }catch(PasswordExceptions e){
                    System.out.println("\nThe option given is wrong because: "
                            +e.getMessage());
                    System.out.println("Please type your new option");
                    passwordAux = scanner.nextLine();
                    password = passwordAux;
                }
            }while(true);

            System.out.println("Type your name(s): ");
            name = scanner.nextLine();
            name = namesAndLastNamesExceptions(name);

            System.out.println("Type your last names: ");
            lastName = scanner.nextLine();
            lastName = namesAndLastNamesExceptions(lastName);

            System.out.println("Type your age: ");
            ageAux = scanner.nextLine();
            do{
                try{
                    in.validarEntrada(ageAux);
                    age = Integer.parseInt(ageAux);
                    a.validarEntrada(age); //para revisar si es mayor de 13 años para crear la cuenta.
                    break;
                }catch(WordInputInNumberInputException e){
                    System.out.println("\nThe option given is wrong because: "
                            +e.getMessage());
                    System.out.println("Please type your new option");
                    ageAuxTwo = scanner.nextLine();
                    ageAux = ageAuxTwo;
                } catch (IncorrectAgeException e) {
                    System.out.println("\nThe option given is wrong because: "
                            +e.getMessage());
                    System.out.println("Please type your new option");
                    ageAuxTwo = scanner.nextLine();
                    ageAux = ageAuxTwo;
                }
            }while(true);
            auxClient = new PremiumClient(0L, users.get(clientPosition), password, name, lastName, age);
            clientsDB.add(clientPosition, auxClient);
            clientPosition++;
            System.out.println("\nSo you are a Premium Client now!\n");
        } else{
            return false;
        }
        printClient(clientsDB);
        return true;
    }

    private static ArrayList<String> listSongsByAlbum(String album, List<Song> songsDB) {
        ArrayList <String> array = new ArrayList<>();

        for(int i = 0; i < songPosition; i++){
            if (songsDB.get(i).getAlbum().equalsIgnoreCase(album)){
                array.add(songsDB.get(i).getName());
            }
        }
        return array;
    }

    private static ArrayList<String> listSongsByArtist(String artist, List<Song> songsDB) {
        ArrayList <String> array = new ArrayList<>();

        for(int i = 0; i < songPosition; i++){
            if (songsDB.get(i).getArtist().equalsIgnoreCase(artist)){
                array.add(songsDB.get(i).getName());
            }
        }
        return array;
    }

    private static void displayList (List<String> array){
        for (String s : array) {
            System.out.println(s);
        }
    }

    private static void printSong (List <Song> array){
        Iterator<Song> iterate = array.iterator();

        // loop through ArrayList till it has all elements
        // Use methods of Iterator to access elements
        while(iterate.hasNext()){

            // access element and display the attributes of the song and access index of each element and change it because new songs are being added.
            Song element = iterate.next();
            //array.get(array.indexOf(element)).setId(array.get(array.indexOf(element)).getId() + 1L);
            System.out.println("ID: " + element.getId() + ". "
                    +"\nName: "   + element.getName() + ". "
                    +"\nArtist: " + element.getArtist() + ". "
                    +"\nGenre: "  + element.getGenre() + ". "
                    +"\nDuration: " + element.getDuration() + ". "
                    +"\nAlbum: " + element.getAlbum() + ". "
                    +"\nDownloads: " + element.getNumberOfDownloads() + ". "
                    +"\n" + element.getProducers().toString() + ". \n") ;
        }
    }


    private static void printPodcast (List <Podcast> array){
        Iterator<Podcast> iterate = array.iterator();

        // loop through ArrayList till it has all elements
        // Use methods of Iterator to access elements
        while(iterate.hasNext()){

            // access element and display the attributes of the song and access index of each element and change it because new songs are being added.
            Podcast element = iterate.next();
            //array.get(array.indexOf(element)).setId((long) array.indexOf(element) + 1L);
            System.out.println("ID: " + element.getId() + ". "
                    +"\nName: "   + element.getName() + ". "
                    +"\nAuthor: " + element.getAuthor() + ". "
                    +"\nDuration: " + element.getDuration() + ". "
                    +"\nCategory: " + element.getCategory() + ". "
                    +"\nDownloads: " + element.getNumberOfDownloads() + ". \n") ;
        }
    }

    private static void displayContent (List<Content> content){
        for(int i = 0; i < content.size(); i++){
            if(content.get(i).getClass().getSimpleName().equals("Song")){
                System.out.println(" - " + content.get(i).getClass().getSimpleName()  +
                        "\nID: " + ((Song) content.get(i)).getId()  + ". "
                        +"\nName: "   + ((Song) content.get(i)).getName() + ". "
                        +"\nArtist: " + ((Song) content.get(i)).getArtist() + ". "
                        +"\nGenre: "  + ((Song) content.get(i)).getGenre() + ". "
                        +"\nDuration: " + ((Song) content.get(i)).getDuration() + ". "
                        +"\nAlbum: " + ((Song) content.get(i)).getAlbum() + ". "
                        +"\nDownloads: " + ((Song) content.get(i)).getNumberOfDownloads() + ". "
                        +"\n" + ((Song) content.get(i)).getProducers().toString() + ". \n") ;
            } else{
                System.out.println(" - " + content.get(i).getClass().getSimpleName() +
                        "\nID: " + ((Podcast) content.get(i)).getId() + ". "
                        +"\nName: "   + ((Podcast) content.get(i)).getName()+ ". "
                        +"\nAuthor: " + ((Podcast) content.get(i)).getAuthor() + ". "
                        +"\nDuration: " + ((Podcast) content.get(i)).getDuration() + ". "
                        +"\nCategory: " + ((Podcast) content.get(i)).getCategory()+ ". "
                        +"\nDownloads: " + ((Podcast) content.get(i)).getNumberOfDownloads()+ ". "
                        +"\n" + ((Podcast) content.get(i)).getProducers().toString() + ". \n");
            }
        }
    }

    private static void printClient (List <Client> array){
        Iterator<Client> iterate = array.iterator();

        // loop through ArrayList till it has all elements
        // Use methods of Iterator to access elements
        while(iterate.hasNext()){

            // access element and display the attributes of the client and access index of each element and change it because new clients are being added.
            Client element = iterate.next();
            array.get(array.indexOf(element)).setId((long) array.indexOf(element) + 1L);
            System.out.println("\n\nID: " + element.getId() + ". "
                    +"\nUser: "   + element.getUser() + ". "
                    +"\nName: " + element.getName() + ". "
                    +"\nLast Name: "  + element.getLastName() + ". "
                    +"\nAge: " + element.getAge() + ". "
                    +"\nClient type: " + element.getClass().getSimpleName()); //mostrar el tipo de cliente y las playlists que tiene

            if(array.get(array.indexOf(element)).getClass().getSimpleName().equals("StandardClient")){
                System.out.print("Song playlist name: ");
                System.out.println(((StandardClient) element).getPlayList().getName());
                for(int i = 0; i < ((StandardClient) element).getPlayList().getContentList().size(); i++){
                    System.out.println(" - " + ((StandardClient) element).getPlayList().getContentList().get(i).getId() + ", " + ((StandardClient) element).getPlayList().getContentList().get(i).getName());
                    }
                }
            else if(array.get(array.indexOf(element)).getClass().getSimpleName().equals("PremiumClient")){
                System.out.println("Playlists: ");
                for(int j = 0; j < ((PremiumClient) element).getPlayList().size(); j++) {
                    System.out.println(((PremiumClient) element).getPlayList().get(j).getName());
                    for (int k = 0; k < ((PremiumClient) element).getPlayList().get(j).getContentList().size(); k++) {
                        System.out.println(" - " + ((PremiumClient) element).getPlayList().get(j).getContentList().get(k).getId() + ", " + ((PremiumClient) element).getPlayList().get(j).getContentList().get(k).getName());
                    }
                }
                System.out.println("\n");
            }
        }
    }
    private static boolean addContentToStandardPlayList(String username, Long id, List<Client> clients, List<Song> songs){ //just songs
        Scanner scanner = new Scanner(System.in);
        boolean bandera1 = false, bandera2 = false, bandera3 = false;
        String password;

        for(int i = 0; i < clientPosition; i++) {
            if (username.equals(clients.get(i).getUser())) {
                bandera1 = true;
                System.out.println("Type your password:");
                password = scanner.nextLine();
                if (password.equals(clients.get(i).getPassword())) {
                    bandera2 = true;
                    System.out.println("\nYou have successfully logged in.");
                    System.out.println("The ID from the song you chose to add to the list is " + id);

                    for (int j = 0; j < songPosition; j++){  //agregar la descarga
                        if (id.equals(songs.get(j).getId())){
                            songs.get(j).addDownload();
                        }
                    }
                }
            }
        }
        String answer;
        for(int a = 0; a < clientPosition; a++) {  //agregar la canción a la lista del usuario
            if (username.equals(clients.get(a).getUser()) && bandera1) {
                for (int j = 0; j < songPosition; j++) {
                    if (id.equals(songs.get(j).getId())) {
                        bandera3 = true;
                        if (clients.get(a).getClass().getSimpleName().equals("StandardClient")){
                            ((StandardClient)clients.get(a)).addContentToPlayList(songs.get(j));
                            if(((StandardClient) clients.get(a)).getPlayList().getName().equals("Default")){
                                System.out.println("\nPlease type the name of the playlist: ");
                                answer = scanner.nextLine();
                                ((StandardClient) clients.get(a)).getPlayList().setName(answer);
                            }
                        }
                    }
                }
            }

        }
            if (!bandera3){
                System.out.println("Some field from the previous fields was given incorrectly.");
                return false;
            }
            if (!bandera2){
                System.out.println("Some field from the previous fields was given incorrectly..\n");
                return false;
            }
            if (!bandera1) {
                System.out.println("\nSome field from the previous fields was given incorrectly.. \n");
                return false;
            }

            return true;
    }

    private static boolean addContentToPlayList(String username, Long id, List<Client> clients, List<Content> content) { //songs and podcasts
        Scanner scanner = new Scanner(System.in);
        boolean bandera1 = false, bandera2 = false, bandera3 = false;
        String password;

        String answer;
        String answerTwo;
        String answerThree;
        for (int i = 0; i < clientPosition; i++) {
            if (clients.get(i).getUser().equals(username)) {
                for (int j = 0; j < ((PremiumClient) clients.get(i)).getPlayList().size(); j++) { //tamaño de la lista de playlists que tiene
                    if (((PremiumClient) clients.get(i)).getPlayList().size() == 1) { //esto es solo para cambiar el nombre de la primera playlist
                        if (((PremiumClient) clients.get(i)).getPlayList().get(j).getName().equals("premium")) {//for (int k = 0; k < ((PremiumClient) clientList.get(i)).getPlayList().get(j).getContentList().size(); k++) { //tamaño de la playlist en específico
                            System.out.println("Please type the name of your playlist: ");
                            answer = scanner.nextLine();
                            ((PremiumClient) clients.get(i)).getPlayList().get(j).setName(answer);
                            //en el caso de que el cliente Premium quiera agregar canción
                                if (addContent(clients, username, id, content, answer)) {
                                    return true;
                                }
                        } else {
                            System.out.println("\nPlease type 'y' if you want to add the content on a existing playlist, or 'n' if you want to create a new one: ");
                            answerTwo = scanner.nextLine();
                            if (answerTwo.equalsIgnoreCase("y")) {
                                System.out.println("So you decided to add your content on a EXISTING playlist");
                                System.out.println("\nThis are the available playlists: ");
                                System.out.println(((PremiumClient) clients.get(i)).getPlayList().get(j).getName());
                                System.out.println("\nType the name of the playlist you want to add your content: ");
                                answerThree = scanner.nextLine();
                                if (answerThree.equalsIgnoreCase(((PremiumClient) clients.get(i)).getPlayList().get(j).getName())) {
                                   //en el caso de que el cliente Premium quiera agregar canción
                                        if (addContent(clients, username, id, content, answerThree)) {
                                            return true;
                                        }

                                }
                            } else if (answerTwo.equalsIgnoreCase("n")) {
                                System.out.println("So you decided to add your content on a NEW playlist");
                                System.out.println("\nType the name of the playlist you want to add your content: ");
                                answerThree = scanner.nextLine();
                                ((PremiumClient) clients.get(i)).addNewPlayList(answerThree);
                                //en el caso de que el cliente Premium quiera agregar canción
                                    if (addContent(clients, username, id, content, answerThree)) {
                                        return true;
                                    }

                            }
                        }
                    } else if (((PremiumClient) clients.get(i)).getPlayList().size() >= 2) {
                        System.out.println("\nPlease type 'y' if you want to add the content on a existing playlist, or 'n' if you want to create a new one: ");
                        answerTwo = scanner.nextLine();
                            if (answerTwo.equalsIgnoreCase("y")) {
                                System.out.println("So you decided to add your content on a EXISTING playlist");
                                System.out.println("\nThis are the available playlists: ");
                                for (PlayList list : ((PremiumClient) clients.get(i)).getPlayList()) {
                                    System.out.println(list.getName());
                                }

                                System.out.println("\nType the name of the playlist you want to add your content: ");
                                answerThree = scanner.nextLine();
                                for(int k = 0; k < ((PremiumClient) clients.get(i)).getPlayList().size(); k++) {
                                    if (answerThree.equalsIgnoreCase(((PremiumClient) clients.get(i)).getPlayList().get(k).getName())) {
                                            if (addContent(clients, username, id, content, answerThree)) {
                                                return true;
                                            }

                                    } else System.out.println("");
                                }
                            } else if (answerTwo.equalsIgnoreCase("n")) {
                                System.out.println("So you decided to add your content on a NEW playlist");
                                System.out.println("\nType the name of the playlist you want to add your content: ");
                                answerThree = scanner.nextLine();
                                ((PremiumClient) clients.get(i)).addNewPlayList(answerThree);
                              //en el caso de que el cliente Premium quiera agregar canción
                                    if (addContent(clients, username, id, content, answerThree)) {
                                        return true;
                                    }
                            }
                    }
                }
            }
        }
        return true;
    }
    private static boolean addContent (List<Client> clients, String username, Long id, List<Content> content, String answerThree){
        Scanner scanner = new Scanner(System.in);
        boolean bandera1 = false, bandera2 = false, bandera3 = false;
        String password;

        for (int a = 0; a < clientPosition; a++) {
            if (username.equals(clients.get(a).getUser())) {
                bandera1 = true;
                System.out.println("Type your password:");
                password = scanner.nextLine();
                if (password.equals(clients.get(a).getPassword())) {
                    bandera2 = true;
                    System.out.println("\nYou have successfully logged in.");
                    System.out.println("The ID from the song you chose to add to your playlist is " + id);

                    for (int b = 0; b < contentPosition; b++) {  //agregar la descarga de la canción
                        if (id.equals(content.get(b).getId())) {
                            content.get(b).addDownload();
                        }
                    }
                }
            }
        }
        for (int k = 0; k < clientPosition; k++) {  //agregar la canción a la lista del usuario
            if (username.equals(clients.get(k).getUser()) && bandera1) {
                for (int l = 0; l < contentPosition; l++) {
                    if (id.equals(content.get(l).getId())) {
                        bandera3 = true;
                        if (clients.get(k).getClass().getSimpleName().equals("PremiumClient")) { //decidir si quiere que se agregue la cancion en la misma playlist o en otra playlist o si quiere crear otra playlist
                            ((PremiumClient) clients.get(k)).addContentToPlayList(content.get(l), answerThree);
                        }

                    }
                }
            }
        }
        if (!bandera3) {
            System.out.println("Some field from the previous fields was given incorrectly.");
            return false;
        }
        if (!bandera2) {
            System.out.println("Some field from the previous fields was given incorrectly..\n");
            return false;
        }
        if (!bandera1) {
            System.out.println("\nSome field from the previous fields was given incorrectly.. \n");
            return false;
        }
        return true;
    }

    private static List<String> searchByGivenKeyword(String keyword, List<Song> songs, String forma) { //me falta guardarlo en un arreglo si quiero, pero así está bien ya.
        List <String> array = new ArrayList<>();
        if (forma.equals("Names")){
            for(int i = 0; i < songPosition; i++){
                if (songs.get(i).getName().toLowerCase().contains(keyword.toLowerCase())){
                    array.add(songs.get(i).getName());
                }
            }
            return array;
        }
        if (forma.equals("Artists")){
            for(int i = 0; i < songPosition; i++){
                if (songs.get(i).getArtist().toLowerCase().contains(keyword.toLowerCase())){
                    array.add(songs.get(i).getArtist());
                }
            }

            return array;
        }
        if (forma.equals("Albums")){
            for(int i = 0; i < songPosition; i++){
                if (songs.get(i).getAlbum().toLowerCase().contains(keyword.toLowerCase())){
                    array.add(songs.get(i).getAlbum());
                }
            }

            return array;
        }
        if (forma.equals("Genres")){
            for(int i = 0; i < songPosition; i++){
                if (songs.get(i).getGenre().toLowerCase().contains(keyword.toLowerCase())){
                    array.add(songs.get(i).getGenre());
                }
            }
            return array;
        }

        return array;
    }

    private static void searchAllByGivenKeyword(String keyword, List<Song> songs, String forma) {
        List <String> arrayName = new ArrayList<>();
        List <String> arrayArtist = new ArrayList<>();
        List <String> arrayAlbum = new ArrayList<>();
        List <String> arrayGenre = new ArrayList<>();

        System.out.println("\n");

        if (forma.equals("All")){
            for(int i = 0; i < songPosition; i++){
                if (songs.get(i).getName().toLowerCase().contains(keyword.toLowerCase())){
                    arrayName.add(songs.get(i).getName());
                }
            }

            for (String name : arrayName) {
                System.out.println("**** Name found **** " + name);
            }
            System.out.println("\n");
        }
        if (forma.equals("All")){
            for(int i = 0; i < songPosition; i++){
                if (songs.get(i).getArtist().toLowerCase().contains(keyword.toLowerCase())){
                    arrayArtist.add(songs.get(i).getArtist());
                }
            }
            for (String artist : arrayArtist){
                System.out.println("*** Artist found *** " + artist);
            }
            System.out.println("\n");
        }
        if (forma.equals("All")){
            for(int i = 0; i < songPosition; i++){
                if (songs.get(i).getGenre().toLowerCase().contains(keyword.toLowerCase())){
                    arrayGenre.add(songs.get(i).getGenre());
                }
            }
            for (String genre : arrayGenre){
                System.out.println("*** Genre found ***  "+ genre);
            }
            System.out.println("\n");
        }
        if (forma.equals("All")){
            for(int i = 0; i < songPosition; i++){
                if (songs.get(i).getAlbum().toLowerCase().contains(keyword.toLowerCase())){
                    arrayAlbum.add(songs.get(i).getAlbum());
                }
            }
            for (String album : arrayAlbum){
                System.out.println("*** Album found ***  " + album);
            }
            System.out.println("\n");
        }
    }

    private static String stringsExceptions(String s){
        OtherStringsHelper o = new OtherStringsHelper();
        Scanner scanner = new Scanner(System.in);
        do{
            try{
                o.validarEntrada(s);
                break;
            }catch(OtherStringsExceptions e){
                System.out.println("\nThe option given is wrong because: "
                        +e.getMessage());
                System.out.println("Please type your new option");
                String stringAux = scanner.nextLine();
                s = stringAux;
            }
        }while(true);
        return s;
    }

    private static String namesAndLastNamesExceptions(String s){
        NamesAndLastNamesHelper n = new NamesAndLastNamesHelper();
        Scanner scanner = new Scanner(System.in);
        do{
            try{
                n.validarEntrada(s);
                break;
            }catch(NamesAndLastNamesException e){
                System.out.println("\nThe option given is wrong because: "
                        +e.getMessage());
                System.out.println("Please type your new option");
                String stringAux = scanner.nextLine();
                s = stringAux;
            }
        }while(true);
        return s;
    }

    private static boolean deleteSongFromDB (List<Content> contents, List<Song> songs, List<Podcast> podcasts){ //arreglar, lo que hay que borrar es el id como tal
        Scanner scanner = new Scanner(System.in);
        String idAux, idAuxTwo;
        Long id;

        System.out.println("\nPlease give the number ID from the Song that you want to delete (you can only delete 1): ");
        InputHelper in = new InputHelper();
        idAux = scanner.nextLine();
        do {
            try {
                in.validarEntrada(idAux);
                id = Long.parseLong(idAux);
                break;
            } catch (WordInputInNumberInputException e) {
                System.out.println("\nThe option given is wrong because: "
                        + e.getMessage());
                System.out.println("Please type your new option");
                idAuxTwo = scanner.nextLine();
                idAux = idAuxTwo;
            }
        } while (true);

        Long finalId = id;
        if (contents.removeIf(content -> content.getId().equals(finalId))){ //elimina la canción/podcast del content, si no está pues no hace nada
            songs.removeIf(song -> song.getId().equals(finalId)); //elimina la canción en songs si es que está ahí.
            podcasts.removeIf(podcast -> podcast.getId().equals(finalId)); //elimina el podcast en podcasts si es que está ahí.
            return true;
        } else return false;
    }

    private static boolean addNewProducerToDB (Map<Long,Producer> producerMap){
        Scanner scanner = new Scanner(System.in);
        InputHelper in = new InputHelper();
        Producer auxProducer;

        String name, nickname;
        System.out.println("Please enter all the values of " +
                "the producer you want to add: ");
        System.out.println("Type the name of your producer: ");
        name = scanner.nextLine();
        name = stringsExceptions(name);

        System.out.println("Type the nickname of your producer: ");
        nickname = scanner.nextLine();
        nickname = stringsExceptions(nickname);

        auxProducer = new Producer(name, nickname);
        auxProducer.setId((long)producerPosition + 1L);
        producerMap.put(auxProducer.getId(),auxProducer);
        producerPosition++;

        for (Map.Entry<Long, Producer> map : producerMap.entrySet()) {
            // Printing keys
            System.out.println("\nID: " + map.getKey() + ". "
                    +"\nName: "   + map.getValue().getName() + ". "
                    +"\nNickname: " + map.getValue().getNickName() + ". \n") ;

        }
        return true;
    }

    private static void showCredits (Long id, List<Content> contentList){
        for (Content content : contentList) {
            if (id.equals(content.getId())) {
                String credits;
                if(content.getClass().getSimpleName().equals("Song")) {
                    credits = ((Song) content).showCredits();
                } else {
                    credits = ((Podcast) content).showCredits();
                }
                System.out.println(credits);
            }
        }
    }
}


