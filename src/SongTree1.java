public class SongTree1 {

    private SongTreeNodable root;

    public SongTree1() {
        root = new SongTree1Null();
    }

    //Add a song to the tree
    void add (Song song) {
        root = root.add(song);
    }

    //Print the values of the whole tree
    void print() {
        System.out.print(this.toString());
    }
    void print_A1() {
        root.print();
    }

    @Override
    public String toString() {
        return root.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != SongTree1.class) return false;

        SongTree1 o = (SongTree1) obj;
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return root.hashCode();
    }

}
