public class SongTree1Null implements SongTreeNodable {
    @Override
    public SongTreeNodable add(Song song) {
        return new SongTree1Binary(song);
    }

    @Override
    public void print() {

    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
