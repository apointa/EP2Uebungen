public class SongTree1Binary implements SongTreeNodable {

    private Song song;
    SongTreeNodable left;
    SongTreeNodable right;

    public SongTree1Binary(Song song) {
        this.song = song;
        this.left = new SongTree1Null();
        this.right = new SongTree1Null();;
    }

    @Override
    public SongTreeNodable add(Song song) {
        if (this.song.compareTo(song) > 0)
            left = left.add(song);
        else
            right = right.add(song);

        return this;
    }

    @Override
    public void print() {
        left.print();
        song.print();
        right.print();
    }

    @Override
    public String toString() {
        return left.toString() + "\n" + song.toString() + right.toString();
    }

    @Override
    public int hashCode() {
        return song.hashCode() + left.hashCode() + right.hashCode();
    }
}
