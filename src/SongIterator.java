import java.util.Iterator;

public interface SongIterator extends Iterator<Song> {

    @Override
    boolean hasNext();
    @Override
    Song next();
}
