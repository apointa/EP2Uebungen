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

    @Override
    public String toString() {
        return (band + ": " + titel + " (" + leange + "s)");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != Song.class) return false;

        Song o = (Song) obj;
        if (this.getBand().equals(o.getBand())
                && this.getTitel().equals(o.getTitel())
                && this.getLeange() == this.getLeange())
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        int hash = 7654321;
        hash += getTitel().hashCode();
        hash += getBand().hashCode();
        hash += getLeange() * 10;
        return hash;
    }

}



