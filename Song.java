package playList;

import java.time.LocalDate;

public class Song {
    private String songName;
    private String artist;
    private int duration;
    private String releaseDate;

    public Song(String songName, String artist, int duration){
        this.songName = songName;
        this.artist = artist;
        this.duration = duration;
        this.releaseDate = String.valueOf(LocalDate.now());
    }

    public String getSongName(){return songName;}

    public String toString(){
        return songName+"\t--\t"+ artist+"\t--\t"+
            duration/60 +":"+duration%60+ "\t--\t"+ releaseDate;
    }

}
