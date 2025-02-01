package playList;

import java.util.*;

public class MainFile {
    private static LinkedList<Song> playList = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while(true){
            System.out.println("Enter your choice: \n1. Add song\n2. Display" +
                    "\n3. Remove song\n4. playSongs\n5. Shuffle\n6. Navigate Song");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch){
                case 1: addSong();
                    break;
                case 2: displayPlayList();
                    break;
                case 3: removeSong();
                    break;
                case 4: playSongs();
                    break;
                case 5: shuffle();
                    break;
                case 6: navigate();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            }
        }
    public static void addSong(){
        System.out.println("Enter the song name: ");
        String name = sc.nextLine();
        System.out.println("Enter artist name: ");
        String artist = sc.nextLine();
        System.out.println("Enter song duration in seconds: ");
        int dur = sc.nextInt();
        sc.nextLine();
        playList.add(new Song(name,artist,dur));
        System.out.println("Song Added successfully");
    }

    public static void displayPlayList(){
        if(playList.isEmpty()){
            System.out.println("List is Empty!!!");
            return;
        }
        int i=0;
        System.out.println("No.\tTITLE\t--\tARTIST\t--\tDURATION\t--\tDATE");
        for(Song song: playList){
            System.out.println((++i) +". "+ song.toString());
        }
    }

    public static void removeSong(){
        if(playList.isEmpty()){
            System.out.println("List is Empty!!!");
            return;
        }
        System.out.println("Enter song name to be removed: ");
        String name = sc.nextLine();
        Song songToRemove = null;
        for(Song song: playList){
            if(song.getSongName().equalsIgnoreCase(name)){
                songToRemove = song;
                break;
            }
        }
        if(songToRemove != null){
            playList.remove(songToRemove);
            System.out.println("Song removed!!");
        } else{
            System.out.println("Song do not exist!!");
        }
    }

    public static void playSongs(){
        if(playList.isEmpty()){
            System.out.println("Playlist is empty!");
            return;
        }
        System.out.println("Playing all songs:");
        for(Song song: playList){
            System.out.println("Now playing: " + song.toString());
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void shuffle(){
        if (playList.isEmpty()) {
            System.out.println("Playlist is empty!");
            return;
        }
        Collections.shuffle(playList);
        System.out.println("Playlist shuffled!");
    }

    public static void navigate() {
        if(playList.isEmpty()) {
            System.out.println("Playlist is empty!");
            return;
        }

        ListIterator<Song> iterator = playList.listIterator();
        boolean forward = true;

        while(true) {
            System.out.println("\nOptions:\n1. Next\n2. Previous\n3. Replay Current\n4. Exit Navigation");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    if(!forward) {  // Switching direction
                        if(iterator.hasNext()) {
                            iterator.next();  // Skip current song
                        }
                        forward = true;
                    }
                    if (iterator.hasNext())
                        System.out.println("Now playing: " + iterator.next().toString());
                    else
                        System.out.println("Reached end of playlist");
                    break;

                case 2:
                    if(forward) {  // Switching direction
                        if(iterator.hasPrevious()) {
                            iterator.previous();  // Skip current song
                        }
                        forward = false;
                    }
                    if (iterator.hasPrevious())
                        System.out.println("Now playing: " + iterator.previous().toString());
                    else
                        System.out.println("Reached start of playlist");
                    break;

                case 3:
                    if (forward && iterator.hasPrevious())
                        System.out.println("Replaying: " + iterator.previous().toString());
                    else if (!forward && iterator.hasNext())
                        System.out.println("Replaying: " + iterator.next().toString());
                    break;

                case 4:
                    return;
            }
        }
    }
}

