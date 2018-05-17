import java.util.Random;

public class Aufgabe5 {

    public static void main(String[] args) {
        SongTable table = new SongTable(300);
        SongTree tree = new SongTree();

        Song newSong = null;
        Random r = new Random();
        for (int i = 0; i<=50; i++) {
            newSong = new Song(((char) (r.nextInt(26) + 'a')) + "-" + ((char) (r.nextInt(26) + 'a')) + "Song" + i,
                    "Tenacious D", (100 * i % 1000) + 10 * (i / 10));
            table.add(newSong);
            tree.add(newSong);
        }

        System.out.println("\nTest Aufgabe Hashtable");
        System.out.println("Test Print");
        table.print();
        System.out.println("\nTest Lookup");
        System.out.println(table.lookupTitle(newSong.getTitel()));
        System.out.println(table.lookupTitle("ASDF"));
        System.out.println("\nTest Iteration");
        for (Song song : table) {
            song.print();
        }


        System.out.println("\n\nTest Aufgabe Songtree");
        System.out.println("Test Iteration");
        for (Song song : tree) {
            song.print();
        }
        System.out.println("\nCompare values");
        tree.print();

    }
}
