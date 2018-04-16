/*
    Song
*/
public class Song implements Comparable<Song>{

    private String titel;
    private String band;
    private long leange;

    public Song(String titel, String band, long leange) {
        this.titel = titel;
        this.band = band;
        this.leange = leange;
    }

    String getTitel() {
        return titel;
    }

    String getBand() {
        return band;
    }

    long getLeange() {
        return leange;
    }

    void print() {
        System.out.println(band + ": " + titel + " (" + leange + "s)");
    }

    @Override
    public int compareTo(Song comp) {
        if (comp != null) {
            return titel.compareTo(comp.getTitel());
        }
        return -1;
    }
}



