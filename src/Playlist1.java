import java.util.LinkedList;

/*
    Playlist1 _ UE2
*/
public class Playlist1 {

    private LinkedListSong songlist;

    public Playlist1(int n) {
        songlist = new LinkedListSong();
    }

    //Creates a Playlist of the songs of myPlaylist which are in a specified length range
    public Playlist1(Playlist1 myPlaylist, long length_min, long length_max) {
        this.songlist = new LinkedListSong();
        for (Song song : myPlaylist.songlist) {
            if (song != null && song.getLeange() >= length_min && song.getLeange() < length_max)
                this.songlist.add(song);
        }
    }

    //Add song at end of playlist
    void add(Song song) {
        songlist.add(song);
    }

    //Get Length in seconds of complete Playlist
    long getLaenge() {
        long leange = 0;
        for (Song song : songlist) {
            if (song != null)
                leange += song.getLeange();
        }
        return leange;
    }

    Song lookupTitle(String title) {
        for (Song song : songlist) {
            if (song != null && song.getTitel().equals(title))
                return song;
        }

        /*Implementierung für letzten Song (Zusatzfrage)
        Song selectedSong = null;
        for (Song song : songlist) {
            if (song != null && song.getTitel().equals(title))
                selectedSong = song;
        }
        return selectedSong;
        */

        return null;
    }

    //Add Song after a specified song in playlist, If specified song is not existing add it to the end
    void addAfter(String title, Song song) {
        Song selectedSong = lookupTitle(title);
        if (selectedSong != null) {
            int index = songlist.indexOf(selectedSong);
            if (index != -1)
                songlist.add(index+1, song);
            else
                songlist.add(song);
        }
        else
            songlist.add(song);
    }
    //Add Song before a specified song in playlist, If specified song is not existing add it to the end
    void addBefore(String title, Song song) {
        Song selectedSong = lookupTitle(title);
        if (selectedSong != null) {
            int index = songlist.indexOf(selectedSong);
            if (index != -1)
                songlist.add(index, song);
            else
                songlist.add(song);
        }
        else
            songlist.add(song);
    }

    void print() {
        for (Song song : songlist) {
            if (song != null)
                song.print();
        }
    }

}
