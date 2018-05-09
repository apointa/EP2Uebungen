import java.util.Random;

/*
    Aufgabe 4)
*/
public class Aufgabe4 {

    public static void main(String[] args) {
        // Test Tree
        System.out.println("\nTest Aufgabe Songtree");
        SongTree tree = new SongTree();
        SongTree1 tree1 = new SongTree1();
        /*
        for (int i = 0; i<=99; i++) {
            tree.add(new Song("BestSongNo " + i, "Tenacious D", (100*i % 1000) + 10*(i/10)));
        }
        */
        Song newSong = null;
        Random r = new Random();
        for (int i = 0; i<=99; i++) {
            newSong = new Song(((char) (r.nextInt(26) + 'a')) + "-" + ((char) (r.nextInt(26) + 'a')) + "Song" + i,
                    "Tenacious D", (100 * i % 1000) + 10 * (i / 10));
            tree.add(newSong);
            tree1.add(newSong);
        }
        System.out.println("\nAusgabe Orginal-Baum");
        tree.print();
        System.out.println("\nAusgabe Interface-Baum-A1");
        tree1.print_A1();
        System.out.println("\nAusgabe Interface-Baum-A1");
        tree1.print();
        System.out.println("\nAusgabe Interface-Baum-Hash");
        System.out.println("\nAusgabe Interface-Baum-A1");

    }


}



