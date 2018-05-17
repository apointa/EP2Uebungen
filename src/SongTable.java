import java.util.Iterator;

public class SongTable implements SongIterable{

    private LinkedListSong[] listTable;

    public SongTable(int size) {
        listTable = new LinkedListSong[size];
    }

    void add(Song song) {
        if (song != null) {
            int slot = genCustHash(song.getTitel());
            if (listTable[slot] == null)
                listTable[slot] = new LinkedListSong();

            listTable[slot].addFirst(song);
        }
    }

    Song lookupTitle(String title) {
        int slot = genCustHash(title);
        if (listTable[slot] != null) {
            for (Song s : listTable[slot]) {
                if (s.getTitel().equals(title))
                    return s;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (LinkedListSong list : listTable) {
            if (list != null)
                s += list.toString();
        }
        return s;
    }

    void print() {
        System.out.print(toString());
    }

    //special hashvalue which only uses songtitle
    private int genCustHash(String title) {
        return Math.abs(title.hashCode()) % (listTable.length - 1);
    }

    @Override
    public SongIterator iterator() {
        return new TableIterator();
    }

    private class TableIterator implements SongIterator{
        int actSlot;
        SongIterator actIterator;

        public TableIterator() {
            for (int actSlot = 0; actSlot < listTable.length; actSlot++) {
                if (listTable[actSlot] != null && listTable[actSlot].getSize() > 0) {
                    actIterator = listTable[actSlot].iterator();
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            if (actIterator == null)
                return false;

            if (actIterator.hasNext())
                return true;
            else {
                for (actSlot = actSlot + 1; actSlot < listTable.length; actSlot++) {
                    if (listTable[actSlot] != null && listTable[actSlot].getSize() > 0) {
                        actIterator = listTable[actSlot].iterator();
                        if (actIterator.hasNext())
                            return true;
                    }
                }
                return false;
            }
        }

        @Override
        public Song next() {
            return actIterator.next();
        }
    }
}
