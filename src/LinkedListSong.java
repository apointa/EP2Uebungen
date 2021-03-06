import java.util.AbstractCollection;
import java.util.NoSuchElementException;

/*
    UE2
    easy implementation of a singly linked list for Songs
*/
public class LinkedListSong extends AbstractCollection<Song> implements SongIterable {

    private ListNodeSong head;
    private ListNodeSong tail;
    private int size;

    public LinkedListSong() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean add(Song song) {
        addLast(song);
        return true;
    }
    //Add Song element at the specific position
    public void add(int index, Song song) {
        if (index <= 0) {
            addFirst(song);
        }
        else if (index >= size) {
            addLast(song);
        }

        int i = 1;
        ListNodeSong prevNode = head;
        while (prevNode != null){
            if (i == index) {
                ListNodeSong newNode = new ListNodeSong(song);
                newNode.next = prevNode.next;
                prevNode.next = newNode;
                size++;
                return;
            }
            prevNode = prevNode.next;
            i++;
        }
    }
    //Add Song element at the back position
    public void addLast(Song song) {
        ListNodeSong newNode = new ListNodeSong(song);

        //If its the first Song in List
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    //Add Song element at the front position
    public void addFirst(Song song){
        ListNodeSong newNode = new ListNodeSong(song);

        //If its the first Song in List
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    //Return the Index of a song inside the list, if not existing return -1
    public int indexOf(Song song) {
        int i = 0;
        ListNodeSong actNode = head;
        while (actNode != null){
            if (actNode.value == song)
                return i;

            actNode = actNode.next;
            i++;
        }
        return -1;
    }
    public int getSize() {
        return size;
    }

    @Override
    public int size() {
        return getSize();
    }

    @Override
    public String toString() {
        String s = "";
        for(Song song : this) {
            if (song != null)
                s += song.toString() + "\n";
        }
        return s;
    }

    @Override
    public SongIterator iterator() {
        return new LinkedListSongIterator();
    }

    private class ListNodeSong {
        private Song value;
        private ListNodeSong next;

        public ListNodeSong(Song value) {
            this.value = value;
        }
    }

    private class LinkedListSongIterator implements SongIterator {
        private ListNodeSong nextNode;
        private String minTitle;
        private long maxLength;

        public LinkedListSongIterator() {
            nextNode = head;
        }

        public LinkedListSongIterator(String minTitle, long maxLength) {
            this.minTitle = minTitle;
            this.maxLength = maxLength;
            nextNode = head;
            while (nextNode != null) {
                if (nextValid())
                    break;
                nextNode = nextNode.next;
            }
        }

        @Override
        public boolean hasNext() {
            return (nextNode != null);
        }

        @Override
        public Song next() {
            if (!hasNext())
                throw new NoSuchElementException();
            else {
                ListNodeSong returnNode = nextNode;
                nextNode = nextNode.next;
                while (nextNode != null) {
                    if (nextValid())
                        break;
                    nextNode = nextNode.next;
                }
                return returnNode.value;
            }
        }

        public boolean nextValid() {
            if (minTitle == null)
                return true;

            return nextNode.value.getTitel().compareTo(minTitle) < 1 && nextNode.value.getLeange() < maxLength;
        }
    }

}

