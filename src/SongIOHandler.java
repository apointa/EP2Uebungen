import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SongIOHandler {

    private static final Pattern REC_PAT = Pattern.compile("[^\\n;]+;[^\\n;]+;\\d+");
    //private static final Pattern REC_PAT_ext = Pattern.compile("([^\\n;\"]+|(\"([^\"]|\"\")+\"));([^\\n;\"]+|(\"([^\"]|\"\")+\"));((\"\\d+\")|(\\d+))\n");
    private static final Pattern REC_PAT_EXCEP1 = Pattern.compile("[^;]+;[^;]+;[^;]+");
    private static final Pattern REC_PAT_EXCEP2 = Pattern.compile("[^;]+;[^;]+;\\d+");

    private static final String SEP = Pattern.quote(";");
    private static final String PRINT_PAT = "%s;%s;%d\n";


    public  void saveSongs(Collection<Song> songs, String filename) {
        if (filename == null)
            return;

        try (PrintStream printer = new PrintStream(filename)){
            for (Song song : songs) {
                if (song!= null)
                    printer.printf(PRINT_PAT, song.getTitel(), song.getBand(), song.getLeange());
            }
        }
        catch (IOException e) {
            System.out.println("IO Exception - saveSongs");
        }
    }

    public  void saveSongsExt(Collection<Song> songs, String filename) {
        ArrayList<Song> caputeredList = new ArrayList<>();
        for (Song song : songs) {
            String title = null;
            String band = null;
            if(song.getTitel().contains(";") || song.getTitel().contains("\n")) {
                title = song.getTitel().replace("\"", "\"\"");
                title = "\"" + title + "\"";
            }
            if(song.getBand().contains(";") || song.getBand().contains("\n")) {
                band = song.getBand().replace("\"", "\"\"");
                band = "\"" + band + "\"";
            }

            if (band != null || title != null) {
                Song tmpSong = new Song(title != null ? title : song.getTitel(),
                        band != null ? band : song.getBand(),
                        song.getLeange());
                caputeredList.add(tmpSong);
            }
            else
                caputeredList.add(song);
        }
        saveSongs(caputeredList, filename);
    }

    public  void readSongs(Collection<Song> songColl, String filename) {
        if (filename == null || songColl == null)
            return;

        try (InputStream iStream = new FileInputStream(filename)) {
            Scanner scanner = new Scanner(iStream);
            int lineC = 0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineC++;
                if (!line.isEmpty()) {
                    try {
                        if (REC_PAT.matcher(line).matches()) {
                            String[] sep = line.split(SEP);
                            Song song = new Song(sep[0], sep[1], Integer.parseInt(sep[2]));
                            songColl.add(song);
                        }
                        else {
                            if(REC_PAT_EXCEP1.matcher(line).matches()) {
                                if(REC_PAT_EXCEP2.matcher(line).matches())
                                    throw new InvalidDataException();
                                else
                                    throw new NonNumericLengthException();
                            }
                            else
                                throw new WrongFieldCountException();
                        }
                    }
                    catch (IOException e)
                    { System.out.println("Error in record " + lineC + " :" + e.getMessage());}
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error with inputstream : " + e.getMessage());
        }
    }

    public  void readSongsExt(Collection<Song> songColl, String filename) {
        if (filename == null ||songColl == null)
            return;

        try (InputStream iStream = new FileInputStream(filename)) {
            Scanner scanner = new Scanner(iStream).useDelimiter(Pattern.compile("\\A"));
            ArrayList<ArrayList<String>>records = splitInLines(scanner.next());
            for(ArrayList<String> record : records) {
                songColl.add(new Song(strapCapQuote(record.get(0)), strapCapQuote(record.get(1)), Integer.parseInt(strapCapQuote(record.get(2)))));
            }
        }
        catch (IOException e) {
            System.out.println("Error with inputstream : " + e.getMessage());
        }
    }

    private  ArrayList<ArrayList<String>> splitInLines(String data) {
        ArrayList<ArrayList<String>> recordList = new ArrayList<>();
        ArrayList<String> fieldList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean lastWasCapQuote = false;
        boolean insideQuote = false;
        int actField = 0;
        int actRecord = 0;
        for (int i = 0; i < data.length(); i++) {
            try {
                char c = data.charAt(i);
                if (c == '"') {
                    if (!lastWasCapQuote) {
                        //Quote inside captured field -> ignore direct following quote
                        if (insideQuote && i != data.length() - 1 && data.charAt(i + 1) == '"')
                            lastWasCapQuote = true;

                            //Start Capturing Quote
                        else if (!insideQuote &&
                                (i == 0 || data.charAt(i - 1) == ';' || data.charAt(i - 1) == '\n'))
                            insideQuote = true;

                            //End Capturing Quote
                        else if (insideQuote &&
                                (i == data.length() - 1 || data.charAt(i + 1) == ';' || data.charAt(i + 1) == '\n'))
                            insideQuote = false;

                            //Single
                        else if (insideQuote)
                            throw new UnCapedQuoteException();

                        builder.append(c);
                    } else
                        lastWasCapQuote = false;
                } else if (c == ';') {
                    //Start next field
                    if (!insideQuote) {
                        fieldList.add(builder.toString());
                        builder.setLength(0);
                        actField++;
                        if (actField > 2)
                            throw new WrongFieldCountException();
                    }
                    //Non digit in numfield
                    else {
                        if (actField == 2)
                            throw new NonNumericLengthException();
                        else
                            builder.append(c);
                    }
                } else if (c == '\n') {
                    //Start next record
                    if (!insideQuote) {
                        if (actField == 2) {
                            fieldList.add(builder.toString());
                            recordList.add(fieldList);
                            builder.setLength(0);
                            fieldList = new ArrayList<>();
                            actField = 0;
                            actRecord++;
                        } else
                            throw new WrongFieldCountException();
                    } else {
                        //Non digit in numfield
                        if (actField == 2)
                            throw new NonNumericLengthException();
                        else
                            builder.append(c);
                    }
                } else {
                    //Non digit in numfield
                    if (actField == 2 && !Character.isDigit(c))
                        throw new NonNumericLengthException();
                    else
                        builder.append(c);
                }
            }
            catch (IOException e) {
                System.out.println("Error! Record: " + actRecord + " Field: " +actField + " - " + e.getMessage());
            }

        }
        return recordList;
    }

    private  String strapCapQuote(String text) {
        if(text.startsWith("\"") && text.endsWith("\"") && text.length() >= 2)
            return text.substring(1, text.length()-1);
        else
            return text;
    }

    public  class WrongFieldCountException extends IOException {
        public WrongFieldCountException() {
            super("Wrong number of fields in record");
        }
    }

    public  class NonNumericLengthException extends IOException {
        public NonNumericLengthException() {
            super("Length field is not numeric in record");

        }
    }

    public  class InvalidDataException extends IOException {
        public InvalidDataException() {
            super("Invalid Data in title or band");
        }
    }

    public class UnCapedQuoteException extends IOException {
        public UnCapedQuoteException() {
            super("SingleQuote in QuoteCaps");
        }
    }

}
