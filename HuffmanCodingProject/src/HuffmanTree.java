import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HuffmanTree {
    private Map<Character, Integer> freqMap;     // tracks the frequency of each letter
    private Map<Character, String> huffmanMap;   // tracks the 1 and 0 sequence for each letter

    class HuffmanNode implements Comparable<HuffmanNode>
    {

        Character ch;

        //storing frequency in freq variable of type int
        Integer freq;

        //initially both child (left and right) are null
        HuffmanNode left = null;
        HuffmanNode right = null;

        //creating a constructor of the Node class
        HuffmanNode(Character ch, Integer freq)
        {
            this.ch = ch;
            this.freq = freq;
        }

        //creating a constructor of the Node class
        public HuffmanNode(Character ch, Integer freq, HuffmanNode left, HuffmanNode right)
        {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(HuffmanNode o) {
            return this.freq - o.freq;
        }

        @Override public String toString() {
            String s = ch == ' ' ? "BLANK" : "'" + ch.toString() + "'";
            return s + " -> " + this.freq;
        }
    }

    private HuffmanNode root; // root of the huffman tree

    public HuffmanTree() {
        this.freqMap = new TreeMap<>();
        this.populateFreqMap();
        this.huffmanMap = new TreeMap<>();  // use tree map here so letters remain sorted
        this.populateHuffmanTree();
    }

    private void populateFreqMap() {
        //each English letter and its relative frequency in everyday use
        this.freqMap.put('a', 4331);
        this.freqMap.put('b', 1056);
        this.freqMap.put('c', 2313);
        this.freqMap.put('d', 1725);
        this.freqMap.put('e', 5688);
        this.freqMap.put('f', 924);
        this.freqMap.put('g', 1259);
        this.freqMap.put('h', 1531);
        this.freqMap.put('i', 3845);
        this.freqMap.put('j', 100);
        this.freqMap.put('k', 561);
        this.freqMap.put('l', 2798);
        this.freqMap.put('m', 1536);
        this.freqMap.put('n', 3392);
        this.freqMap.put('o', 3651);
        this.freqMap.put('p', 1614);
        this.freqMap.put('q', 100);
        this.freqMap.put('r', 3864);
        this.freqMap.put('s', 2923);
        this.freqMap.put('t', 3543);
        this.freqMap.put('u', 1851);
        this.freqMap.put('v', 513);
        this.freqMap.put('w', 657);
        this.freqMap.put('x', 148);
        this.freqMap.put('y', 906);
        this.freqMap.put('z', 139);
        this.freqMap.put('.', 4000);
        this.freqMap.put(',', 500);
        this.freqMap.put('?', 500);
        this.freqMap.put('"', 100);     // sequence: single quote, double quote, single quote
        this.freqMap.put('\'', 100);    // sequence: single quote, backslash, single quote, single quote
        this.freqMap.put(' ', 10000);  // sequence: single quote, single blank space, single quote
    }

    public static boolean isLeaf(HuffmanNode node)
    {
        //returns true if both conditions return true
        return node.left == null && node.right == null;
    }

    public  void populateHuffmanTree()
    {
        //create a priority queue (min heap) using the standard priority queue class
        // from the java library. It should store HuffmanNode objects.
        /* write some code here */
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        // iterate over the frequency map and create a HuffmanNode for each entry
        // put the HuffmanNodes you create into the priority queue you just created.
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet())
        {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        //while loop runs until there is one node in the priority queue
        while (pq.size() != 1)
        {
            //remove the two nodes having the highest priority (the lowest frequency) from the queue
            // and save them in temporary HuffmanNode variables
            /* write some code here */
            HuffmanNode h1 = pq.poll();
            HuffmanNode h2 = pq.poll();

            //create a new Huffman node
            // add the two above nodes as children to your new Huffman node
            // set the frequency of your new node equal to the sum of both nodes' frequencies.
            // add the new node to the priority queue.
            /* write some code here */
            pq.add(new HuffmanNode(' ', h1.freq + h2.freq, h1, h2));

        }
        //root stores pointer to the root of Huffman Tree
        this.root = pq.peek();
        //trace over the Huffman tree and store the Huffman codes in a map
        encodeData(root, "", huffmanMap);
    }

    // this is the only hard part. the encodeData method uses some recursion to encode the
    // strings that represent each letter. the resulting string is a sequence of 0’s and 1’s.
    public  void encodeData(HuffmanNode node, String str, Map<Character, String> huffmanMap)
    {
        // base case: if the node is null, simply return
        /* write some code here */
        if (node == null)
            return;

        //checks if the node is a leaf node or not
        if (HuffmanTree.isLeaf(node))
        {
            huffmanMap.put(node.ch, str.length() > 0 ? str : "1");
        }
        // recursive call #1: call encode data but replace node with its left child
        // and append a ‘0’ character to the string parameter
        /* write some code here */
        encodeData(node.left, str + "0", huffmanMap);
        // recursive call #2: call encode data but replace node with its right child
        // and append a ‘1’ character to the string parameter
        /* write some code here */
        encodeData(node.right, str + "1", huffmanMap);
    }

    public Map<Character, String> getHuffmanEncodeMap() {
        return this.huffmanMap;
    }

    public Map<String, Character> getHuffmanDecodeMap() {
        Map<String, Character> decodeMap = new TreeMap<>();
        // iterate over the huffmanMap
        // take every entry from the huffmanMap and add a new entry to the decodeMap
        // that is like the huffmanMap entry but the key and the values are swapped.
        for (Map.Entry<Character, String> entry : huffmanMap.entrySet()) {
            // decode map is opposite of encode map
            /* write some code here */
            decodeMap.put(entry.getValue(), entry.getKey());
        }
        return decodeMap;
    }
}
