/*
    Aufgabe 1)
*/
public class Aufgabe1{

    public static void main(String[] args) {

        //
        //TEST UE1
        //
        Song song = new Song("When I’m Sixty-Four", "Beatles", 157);
        song.print();
        /*
        Playlist list = new Playlist(100);
        for (int i = 0; i<=200; i++) {
            list.add(new Song("BestSongNo " + i, "Tenacious D", 292));

            if (i%10 == 0)
                list.print();
        }
        */
        Playlist list_optimize_test = new Playlist(8);
        list_optimize_test.add(new Song("BestSongNo 1", "Tenacious D", 20));
        list_optimize_test.add(new Song("BestSongNo 2", "Tenacious D", 43));
        list_optimize_test.add(new Song("BestSongNo 3", "Tenacious D", 87));
        list_optimize_test.add(new Song("BestSongNo 4", "Tenacious D", 42));
        list_optimize_test.add(new Song("BestSongNo 5", "Tenacious D", 27));
        list_optimize_test.add(new Song("BestSongNo 6", "Tenacious D", 11));
        list_optimize_test.add(new Song("BestSongNo 7", "Tenacious D", 45));
        list_optimize_test.add(new Song("BestSongNo 8", "Tenacious D", 33));

        Playlist list_optimize_result = new Playlist(list_optimize_test, 104);
        list_optimize_result.print();
        System.out.print(list_optimize_result.getLaenge());
    }
}



