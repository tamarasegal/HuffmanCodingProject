import java.io.File;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class HuffmanTransmitter {
    private File inputFile;
    private Scanner scan;
    private File encodedFile;
    private PrintWriter pw;

    private HuffmanTree ht;
    private Map<Character, String> huffmanMap;

    private StringBuilder sb; // will contain the encoded strings



    public HuffmanTransmitter(String inputFileName, String encodedFileName) {
        this.ht = new HuffmanTree();
        this.huffmanMap = ht.getHuffmanEncodeMap();

        this.inputFile = new File(inputFileName);
        try {
            this.scan = new Scanner(inputFile);
        } catch (Exception e) {
            System.out.println("Cannot file input file:" + inputFileName);
            System.exit(1);
        }

        this.encodedFile = new File(encodedFileName);
        try {
            this.pw = new PrintWriter(encodedFile);
        } catch (Exception e) {
            System.out.println("Could not create output file:" + encodedFileName);
            System.exit(2);
        }
    }

    public void run() {
        // debug
        System.out.println("Encoding Map:" + huffmanMap);

        // encode each line of the input file
        while (scan.hasNext()) {
            String nextLine = scan.nextLine().toLowerCase();
            this.sb = new StringBuilder();
            for (char c: nextLine.toCharArray())
            {
                //prints encoded string by getting characters
                if (huffmanMap.containsKey(c))
                    sb.append(huffmanMap.get(c));
            }
            //pw.println("Original" + nextLine); // turn this line on for your debugging
            pw.println(sb.toString()); // write encoded sequence to output file
        }
        pw.close();  // save output file
    }

}
