public class HuffmanTestCode
{
    //driver code
    public static void main(String args[])
    {
        String inputFileName = "jabberwocky.txt";
        String encodedFileName = "encoded.txt";

        HuffmanTransmitter xmit = new HuffmanTransmitter(inputFileName, encodedFileName);
        xmit.run();  // encode the input file

        HuffmanReceiver receiver = new HuffmanReceiver(encodedFileName);
        receiver.run();    // decode the encoded file and print to the console

    }
}
