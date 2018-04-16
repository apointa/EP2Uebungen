import java.util.Iterator;
import java.util.NoSuchElementException;
/*
    UE3
    easy implementation of a double linked list for Songs
*/
public class DoubleLinkedListSong implements Iterable<Song> {
    private ListNodeSong head;
    private ListNodeSong tail;
    private int size;

    public DoubleLinkedListSong() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(Song song) {
        addLast(song);
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
                newNode.prev = prevNode;
                prevNode.next.prev = newNode;
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
            newNode.prev = tail;
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
            head.prev = newNode;
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
    public Iterator<Song> iterator() {
        return new LinkedListSongIterator();
    }

    private class ListNodeSong {
        private Song value;
        private ListNodeSong next;
        private ListNodeSong prev;

        public ListNodeSong(Song value) {
            this.value = value;
        }
    }

    private class LinkedListSongIterator implements Iterator<Song> {
        private ListNodeSong nextNode;
        public LinkedListSongIterator() {
            nextNode = head;
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
                return returnNode.value;
            }
        }
    }

    protected void printDebug() {
        ListNodeSong actNode = head;
        while (actNode != null) {
            System.out.println(actNode.value.getTitel() +
                    " Prev: " + (actNode.prev != null ?  actNode.prev.value.getTitel() : "null") +
                    " Next: " + (actNode.next != null ?  actNode.next.value.getTitel() : "null"));
            actNode = actNode.next;
        }
    }
}
