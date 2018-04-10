/*
    Aufgabe 2)
*/
public class Aufgabe2 {
    
    public static void main(String[] args) {
        //
        //TEST UE2
        //
        //SampleData
        Playlist1 list = new Playlist1(100);
        for (int i = 0; i<=99; i++) {
            list.add(new Song("BestSongNo " + i, "Tenacious D", (100*i % 1000) + 10*(i/10)));
        }

        //Test Aufgabe1)
        System.out.println("\nTest Aufgabe1");
        list.print();
        System.out.println(list.getLaenge());

        //Test Aufgabe2)
        System.out.println("\nTest Aufgabe2");
        String lookFor = "BestSongNo " + (int)(Math.random()*99);
        System.out.println("Look for Song with Title: " + lookFor);
        list.lookupTitle(lookFor).print();
        lookFor = "Not in List";
        System.out.println("Look for Song with Title: " + lookFor);
        System.out.println(list.lookupTitle(lookFor));

        //Test Aufgabe3)
        System.out.println("\nTest Aufgabe3");
        long minTime = 300;
        long maxTime = 400;
        Playlist1 testList = new Playlist1(list, minTime, maxTime);
        System.out.println("Playlist with songs between " + minTime + "s and " + maxTime + "s");
        testList.print();

        //Test Aufgabe4 + 5)
        System.out.println("\nTest Aufgabe4+5");
        for (int i = 0; i<=4; i++) {
            Song afterSong = new Song(String.valueOf((int)(Math.random()*100)), "SongAfter", i);
            Song beforeSong = new Song(String.valueOf((int)(Math.random()*100)), "SongBefore", i);
            list.addAfter("BestSongNo " + afterSong.getTitel(), afterSong);
            list.addBefore("BestSongNo " + beforeSong.getTitel(), beforeSong);
        }
        list.addAfter("Not in List", new Song("Not in List After","AddAfter", 123));
        list.addBefore("Not in List", new Song("Not in List Before","AddBefore", 123));
        list.print();
    }
}
