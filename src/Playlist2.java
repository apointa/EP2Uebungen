/*
    Playlist2 _ UE3
*/
public class Playlist2 {

    private DoubleLinkedListSong songlist;

    public Playlist2() {
        songlist = new DoubleLinkedListSong();

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

    void printDebug() {
        songlist.printDebug();
    }

}
