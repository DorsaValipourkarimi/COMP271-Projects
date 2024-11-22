/**
 * This class implements Huffman Encoding using a MinHeap data structure. It provides functionality to:
 * - Calculate symbol frequencies
 * - Build a Huffman tree
 * - Generate Huffman codes
 * - Encode a message and report compression efficiency.
 * 
 * The class is designed for efficient text compression and can be used to visualize and analyze 
 * the resulting Huffman codes and their compression effectiveness.
 */
public class HuffmanEncodingWithHeap {

    private static final int ASCII8 = 256; // Total number of possible ASCII characters (256 characters)
    private static final char LEFT = '0'; // Character representing the left branch of the Huffman tree
    private static final char RIGHT = '1'; // Character representing the right branch of the Huffman tree
    private static final String EMPTY = ""; // Empty string for initializing the Huffman code
    private static final int BITS_PER_BYTE = 8; // The number of bits in a byte

    /**
     * Parses a string and produces a frequency count for each of its symbols.
     * 
     * @param message String to parse
     * @return Array of frequency counts for each ASCII value
     */
    static public int[] countFrequency(String message) {
        int[] frequencies = new int[ASCII8]; // Array to hold frequency of each ASCII character
        if (message != null) { // if message is not empty
            for (int i = 0; i < message.length(); i++) { // Loop through each character in the message
                frequencies[(int) message.charAt(i)]++; // Increment the frequency of the character at the ASCII index
            }
        }
        return frequencies; // Return the frequency array
    } // method countFrequency

    /**
     * Build a MinHeap from the frequencies of the symbols in the string.
     * 
     * @param frequencies Array of frequency counts for each ASCII symbol
     * @return MinHeap of HuffmanNodes
     */
    public static MinHeap<HuffmanNode> buildForest(int[] frequencies) {
        MinHeap<HuffmanNode> heap = new MinHeap<>(); // Create an empty MinHeap to hold the Huffman nodes
        for (int asciiCode = 0; asciiCode < frequencies.length; asciiCode++) { // Loop through all possible ASCII values
            if (frequencies[asciiCode] > 0) { // If the character with this ASCII code has a non-zero frequency
                // Create a new HuffmanNode for each symbol with a frequency greater than zero
                heap.insert(new HuffmanNode((char) asciiCode, frequencies[asciiCode])); // Insert the node into the heap
            }
        }
        return heap; // Return the MinHeap
    } // method buildForest

    /**
     * Constructs the Huffman tree by applying Huffman's algorithm (repeatedly
     * combining the two nodes with the smallest frequencies).
     * 
     * @param heap MinHeap of HuffmanNodes
     * @return The root node of the Huffman tree
     */
    public static HuffmanNode buildTree(MinHeap<HuffmanNode> heap) {
        // While there's more than one node in the heap, continue building thew tree
        // (keep combining the two smallest)
        while (heap.size() > 1) {
            // Remove the two nodes with the smallest frequencies
            HuffmanNode t1 = heap.removeMin(); // Remove the node with the smallest frequency
            HuffmanNode t2 = heap.removeMin(); // Remove the next smallest node

            // Combine these two nodes into a new internal node with no symbol
            // The frequency of the new node is the sum of the frequencies of the two nodes
            HuffmanNode combined = new HuffmanNode(t1.getFrequency() + t2.getFrequency());

            // Set the left child of the new node to be the first node and right child to be
            // the second node
            combined.setLeft(t1);
            combined.setRight(t2);

            // Insert the combined node back into the heap
            heap.insert(combined);
        }

        // After combining all nodes, the only remaining node is the root of the Huffman
        // tree
        return heap.getMin(); // Return the root node of the Huffman tree
    } // method buildTree

    /**
     * Recursive method to create the encoding table from the Huffman tree.
     * 
     * @param node  The current Huffman node
     * @param code  The code so far
     * @param codes Array to store the Huffman codes
     */
    public static void createEncodingTable(HuffmanNode node, String code, String[] codes) {
        if (node != null) { // If the current node is not null
            if ((int) node.getSymbol() != 0) { // If the node has a valid symbol (not an internal node)
                codes[(int) node.getSymbol()] = code; // Store the code for the symbol in the encoding table
            } else {
                // If the node is an internal node (not a leaf), recursively visit the left and
                // right children
                createEncodingTable(node.getLeft(), code + LEFT, codes); // Go left, append '0' to the code
                createEncodingTable(node.getRight(), code + RIGHT, codes); // Go right, append '1' to the code
            }
        }
    } // method createEncodingTable

    /**
     * Helper method to initiate the encoding table creation.
     * 
     * @param node The root of the Huffman tree
     * @return Array of Huffman codes indexed by ASCII values
     */
    public static String[] createEncodingTable(HuffmanNode node) {
        String[] codes = new String[ASCII8]; // Initialize an array to store Huffman codes for all ASCII characters
        createEncodingTable(node, EMPTY, codes); // Start the recursion with an empty code string
        return codes; // Return the array of Huffman codes
    } // method createEncodingTable

    /**
     * Prints out the Huffman codes for each ASCII symbol in the message.
     * 
     * @param codes Array of Huffman codes
     */
    public static void displayCodes(String[] codes) {
        for (int i = 0; i < codes.length; i++) { // Loop through all possible ASCII values
            if (codes[i] != null) { // If a Huffman code exists for this symbol
                System.out.printf("\n '%s' --> %-10s", (char) i, codes[i]); // Print the symbol and its Huffman code
            }
        }
    } // method displayCodes

    /**
     * Computes the length of the compressed message in bits.
     * 
     * @param message The original message
     * @param codes   Huffman codes for compression
     * @return The number of bits required for compression
     */
    public static int computeCompressionLength(String message, String[] codes) {
        int compressionLength = 0; // Variable to store the total number of bits
        for (int i = 0; i < message.length(); i++) { // Loop through the message
            compressionLength += codes[(int) message.charAt(i)].length(); // Add the length of the Huffman code for each
                                                                          // character
        }
        return compressionLength; // Return the total number of bits required for the compressed message
    } // method computeCompressionLength

    /**
     * Prints a report of the compression efficiency.
     * 
     * @param message The original message
     * @param codes   Huffman codes for compression
     */
    public static void reportEfficiency(String message, String[] codes) {
        // Print the comparison of the original message size (in bits) vs the compressed
        // message size
        System.out.printf("\nCompressed message requires %d bits versus %d bits for ASCII encoding.\n",
                computeCompressionLength(message, codes), message.length() * BITS_PER_BYTE);
    } // method reportEfficiency

    /**
     * Encodes a message using Huffman encoding.
     * 
     * @param message The message to encode
     */
    static void encode(String message) {
        int[] frequencies = countFrequency(message); // Get the frequency of each symbol in the message
        MinHeap<HuffmanNode> heap = buildForest(frequencies); // Build the MinHeap of Huffman nodes from frequencies
        HuffmanNode huffmanTreeRoot = buildTree(heap); // Build the Huffman tree from the heap
        String[] codes = createEncodingTable(huffmanTreeRoot); // Create the Huffman encoding table
        displayCodes(codes); // Display the Huffman codes for each symbol
        reportEfficiency(message, codes); // Report the efficiency of the compression
    } // method encode

    /** Driver code */
    public static void main(String[] args) {
        String message = "now is the winter of our discontent made glorious by this son of york and all the clouds that lour'd over our house in the deep bosom of the ocean lay";
        encode(message);
    } // method main

} // class HuffmanEncodingWithHeap
