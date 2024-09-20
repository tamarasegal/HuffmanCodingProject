import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class HuffmanReceiver {
    private File encodedFile;
    private Scanner scan;

    private HuffmanTree ht;
    private Map<String, Character> decodeMap;
    private StringBuilder sb;

    public HuffmanReceiver(String encodedFileName) {
        this.ht = new HuffmanTree();
        this.decodeMap = ht.getHuffmanDecodeMap();
        this.encodedFile = new File(encodedFileName);
        try {
            this.scan = new Scanner(this.encodedFile);
        } catch (Exception e) {
            System.out.println("Could not open encoded file:" + encodedFileName);
            System.exit(1);
        }
    }

    public void run() {
        // debug
        System.out.println("Decoding Map:" + decodeMap);

        // encode each line of the input file
        while (scan.hasNext()) {
            String nextLine = scan.nextLine();
            this.sb = new StringBuilder();
            //System.out.println("encoded line is:" + nextLine); // debug

            for (int i=0; i < nextLine.length(); ++i) {
                while (!decodeMap.containsKey(this.sb.toString())) {
                    this.sb.append(nextLine.charAt(i++));
                    //System.out.print("\nsb =" + sb.toString()); // debug
                }
                --i;
                System.out.print(decodeMap.get(this.sb.toString())); // write decoded sequence to console
                this.sb = new StringBuilder(); // reset for next character
            }

            //System.out.println("Original" + nextLine); // turn this line on for your debugging
            System.out.println(); // output is processed line by line
        }
    }
}
