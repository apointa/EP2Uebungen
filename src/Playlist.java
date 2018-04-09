/*
    Playlist
*/
public class Playlist {

    private Song[] songs;
    private int lastSongPosition;
    private static int counter; //just to analyse optimization algo


    public Playlist(int n) {
        songs = new Song[n];
        lastSongPosition = 0;
    }

    //Creates a Sub-Playlist with a specific length
    public Playlist(Playlist playlist, long laenge) {
        counter = 0;
        Song[] temp = optimizedPlaylist(playlist.songs, laenge, 0).songs;
        //Songs are in wrong order - order them
        this.songs = new Song[temp.length];
        lastSongPosition = 0;
        for (int i = temp.length-1; i >= 0; i--) {
            if (temp[i] != null) {
                songs[lastSongPosition++] = temp[i];
            }
        }
    }

    //Add Song to Playlist, If List is full replace oldest song
    void add(Song song) {
        songs[lastSongPosition++] = song;
        if (lastSongPosition >= songs.length)
            lastSongPosition = 0;
    }

    long getLaenge() {
        long leange = 0;
        for (Song song : songs) {
            if (song != null)
                leange += song.getLeange();
            else
                break;
        }
        return leange;
    }

    void print() {
        for (Song song : songs) {
            if (song != null)
                song.print();
            else
                break;
        }
    }

    //Creates a Playlist which combines song to get as near as possible to the length parameter (no overshoot)
    private Playlist optimizedPlaylist(Song[] songs, long laenge, int actPos) {
        counter++;
        System.out.println(laenge + " - " + actPos + " - " + counter);

        //Last Song in List - Return new list and add song if length is okay
        if (actPos >= songs.length-1 || songs[actPos] == null) {
            Playlist tempList = new Playlist(songs.length);
            if (laenge - songs[actPos].getLeange() >= 0) {
                tempList.add(songs[actPos]);
                return tempList;
            }
            else {
                return tempList;
            }
        }

        //Generate List without act Song
        Playlist listWithout = optimizedPlaylist(songs, laenge, actPos + 1);
        Playlist listWith;

        //Check if act Song is short enough and generate list with song
        if (laenge - songs[actPos].getLeange() >= 0) {
            listWith = optimizedPlaylist(songs, laenge - songs[actPos].getLeange(), actPos + 1);
            listWith.add(songs[actPos]);
        }
        else {
            return listWithout;
        }

        //Check which way is longer (better)
        if (listWithout.getLaenge() > listWith.getLaenge())
            return listWithout;
        else
            return listWith;
    }
}
