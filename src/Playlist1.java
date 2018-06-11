/*
    Playlist1 _ UE2
*/
public class Playlist1 implements SongIterable {

    private LinkedListSong songlist;

    public Playlist1() {
        songlist = new LinkedListSong();
    }

    public Playlist1(String filname) {
        songlist = new LinkedListSong();
        new SongIOHandler().readSongsExt(songlist, filname);
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
    //Return Wert ist 0 oder positiv
    long getLaenge() {
        long laenge = 0;
        for (Song song : songlist) {
            assert laenge >= 0;
            if (song != null)
                laenge += song.getLeange();
            assert laenge >= 0;
        }
        assert laenge >= 0;
        return laenge;
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

    void save(String filename) {
        new SongIOHandler().saveSongsExt(songlist, filename);
    }

    @Override
    public String toString() {
        return songlist.toString();
    }

    @Override
    public SongIterator iterator() {
        return songlist.iterator();
    }

}
