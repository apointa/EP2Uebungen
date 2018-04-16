/*
    UE3
    easy implementation of a binary search tree for Songs
*/
public class SongTree {

    private TreeNode root;
    private int size;

    public SongTree() {
        root = null;
        size = 0;
    }

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

    long getLaenge() {
        if (root != null)
            return root.getLengthAll();
        else
            return 0;
    }

    void print() {
        if (root != null)
            root.printAll();
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

}
