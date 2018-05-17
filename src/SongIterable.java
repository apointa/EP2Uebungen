public interface SongIterable extends Iterable<Song> {

    @Override
    SongIterator iterator();
}
