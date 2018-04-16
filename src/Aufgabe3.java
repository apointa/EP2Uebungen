import java.util.Random;

/*
    Aufgabe 3)
*/
public class Aufgabe3 {
    
    public static void main(String[] args) {
        //
        //TEST UE3
        //
        // Test Double Linked List
        //SampleData
        Playlist2 list = new Playlist2();
        for (int i = 0; i<=99; i++) {
            list.add(new Song("BestSongNo " + i, "Tenacious D", (100*i % 1000) + 10*(i/10)));
        }

        //Test
        System.out.println("\nTest Aufgabe");
        list.print();
        System.out.println(list.getLaenge());

        //Test
        System.out.println("\nTest Aufgabe lookup");
        String lookFor = "BestSongNo " + (int)(Math.random()*99);
        System.out.println("Look for Song with Title: " + lookFor);
        list.lookupTitle(lookFor).print();
        lookFor = "Not in List";
        System.out.println("Look for Song with Title: " + lookFor);
        System.out.println(list.lookupTitle(lookFor));

        //Test
        System.out.println("\nTest Aufgabe addBefore/addAfter");
        for (int i = 0; i<=4; i++) {
            Song afterSong = new Song(String.valueOf((int)(Math.random()*100)), "SongAfter", i);
            Song beforeSong = new Song(String.valueOf((int)(Math.random()*100)), "SongBefore", i);
            list.addAfter("BestSongNo " + afterSong.getTitel(), afterSong);
            list.addBefore("BestSongNo " + beforeSong.getTitel(), beforeSong);
        }
        list.addAfter("Not in List", new Song("Not in List After","AddAfter", 123));
        list.addBefore("Not in List", new Song("Not in List Before","AddBefore", 123));
        list.printDebug();



        // Test Tree
        System.out.println("\nTest Aufgabe Songtree");
        SongTree tree = new SongTree();
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
        }
        tree.print();
        System.out.println(tree.getLaenge());
        tree.lookupTitle(newSong.getTitel()).print();

    }
}
