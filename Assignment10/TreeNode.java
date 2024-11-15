public class TreeNode implements Comparable<TreeNode> {

    /** The data payload of the node */
    private String word;
    /** Its left and right pointers */
    private TreeNode left;
    private TreeNode right;

    /**
     * Basic constructor creates a simple node with a payload and two null children.
     * 
     * @param word the data we store in the node.
     */
    public TreeNode(String word) {
        this.word = word;
        this.left = null;
        this.right = null;
    } // basic constructor

    /**
     * Implementation of Comparable using the nodes' content strings as basis for
     * comparison. If the
     */
    public int compareTo(TreeNode other) {
        int result = 1;
        if (other != null)
            result = this.getWord().compareTo(other.getWord());
        return result;
    } // method compareTo

    /**
     * String representation of the TreeNode.
     * 
     * For now it just returns TreeNode.word, so it's identical -- in form -- to
     * getWord(). In future versions we may want to write something more
     * sophisticated, like the contents of the node along with some information
     * about its two children.
     */
    public String toString() {
        return this.word;
    } // method toString

    /**
     * Counts the number of children (left and right) of this node.
     * 
     * @return the number of children (0, 1, or 2).
     */
    public int countChildren() {
        int count = 0;

        // Check if left child exists
        if (this.left != null) {
            count++; // Increment for left child
        }

        // Check if right child exists
        if (this.right != null) {
            count++; // Increment for right child
        }

        return count; // Return the total count
    } //method countChildren

    /** Accesors and mutators */

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public String getWord() {
        return word;
    }

    public TreeNode getRight() {
        return this.right;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public void setLeft(TreeNode node) {
        this.left = node;
    }

    public void setRight(TreeNode node) {
        this.right = node;
    }

    public void setWord(String word) {
        this.word = word;
    }

} // class TreeNode

