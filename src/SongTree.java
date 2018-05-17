import java.math.BigInteger;

/*
    UE3
    easy implementation of a binary search tree for Songs
*/
public class SongTree implements SongIterable{

    private TreeNode root;
    private int size;

    public SongTree() {
        root = null;
        size = 0;
    }

    //Add a song to the tree
    void add (Song song) {
        if (root != null) {
            if (root.add(song))
                size++;
        }
        else {
            root = new TreeNode(song);
            size++;
        }
    }

    //Get a song with a specific title in the tree, returns null if no such song is existing
    Song lookupTitle(String title) {
        TreeNode actNode = root;
        while (actNode!=null) {
            if (actNode.value.getTitel().compareTo(title) > 0)
                actNode = actNode.left;
            else if (actNode.value.getTitel().compareTo(title) < 0)
                actNode = actNode.right;
            else
                return actNode.value;
        }
        return null;
    }

    //Get the sum of the length of all songs in the tree
    long getLaenge() {
        if (root != null)
            return root.getLengthAll();
        else
            return 0;
    }

    //Print the values of the whole tree
    void print() {
        if (root != null)
            root.printAll();
    }

    @Override
    public SongIterator iterator() {
        return new TreeIterator();
    }

    private class TreeNode {
        private Song value;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(Song value) {
            this.value = value;
        }

        private boolean add(Song newSong) {
            if (value.compareTo(newSong) > 0) {
                if (left != null)
                    return left.add(newSong);
                else {
                    left = new TreeNode(newSong);
                    return true;
                }
            }
            else if(value.compareTo(newSong) < 0) {
                if (right != null)
                    return right.add(newSong);
                else {
                    right = new TreeNode(newSong);
                    return true;
                }
            }
            return false;
        }

        private void printAll() {
            if (left != null)
                left.printAll();

            this.print();

            if (right != null)
                right.printAll();

        }
        private void print() {
            value.print();
        }

        private long getLengthAll() {
            return this.getLength() +
                    (left != null ? left.getLengthAll() : 0) +
                    (right != null ? right.getLengthAll() : 0);
        }
        private long getLength() {
            return value.getLeange();
        }
    }

    private class TreeIterator implements SongIterator {
        private int depth;
        private long path;

        public TreeIterator() {
            depth = -1;
            TreeNode node = root;
            path = 0;
            while (node!= null) {
                node = node.left;
                depth++;
            }
        }

        @Override
        public boolean hasNext() {
            return depth != -1;
        }

        @Override
        public Song next() {
            TreeNode actNode = root;
            long mask = 1;
            for (int i = 0; i < depth; i++) {
                if ((path & mask) == 0)
                    actNode = actNode.left;
                else
                    actNode = actNode.right;

                mask = mask << 1;
            }

            //Set path and depth for next node
            //Node has right child
            if (actNode.right != null) {
                path = (path | mask);
                TreeNode nextNode = actNode.right;
                while (nextNode != null) {
                    nextNode = nextNode.left;
                    depth++;

                    //Clear path
                    mask = mask << 1;
                    path = (path & ~mask);
                }
            }
            else {
                depth--;
                mask = mask >> 1;
                //War rechtes Kind-> zurückgehen bis erste Rechtsabzweigung
                while ((path & mask) != 0) {
                    mask = mask >> 1;
                    depth--;
                }
            }

            return actNode.value;
        }
    }

}
