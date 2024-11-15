/**
 * A simple binary search tree
 */
public class BST {
    /** The entry point to the tree */
    private TreeNode root;
    /** Count of nodes in the tree */
    private int numberOfNodes;
    /** Longest and shortest words stored in the tree */
    private String longest;
    private String shortest;

    // Constant for the message to display when the tree is empty
    private static final String EMPTY_TREE_MESSAGE = "The tree is empty.\n";
    // Constant for the default value when the longest or shortest word is null
    private static final String DEFAULT_WORD_VALUE = "None";

    /** Default constructor */
    public BST() {
        this.root = null;
        this.numberOfNodes = 0;
        this.shortest = null;
        this.longest = null;
    } // default constructor

    /**
     * Overloaded add to take a string, wrap it into a TreeNode object, and invoke
     * the principal method that adds a note to the tree.
     * 
     * @param word String to add, as a node, to the tree
     * 
     */
    public void add(String word) {
        this.add(new TreeNode(word));
    } // method add

    /**
     * Insert a new node into the tree; the method takes no action if a node with
     * the same payload already exists in the tree.
     * 
     * @param newNode node to insert
     */
    public void add(TreeNode newNode) {
        if (this.root == null) {
            this.root = newNode;
            this.numberOfNodes = 1;
            this.shortest = newNode.getWord();
            this.longest = newNode.getWord();
        } else {
            TreeNode cursor = this.root;
            TreeNode parent = null;
            boolean duplicate = false;
            while (cursor != null && !duplicate) {
                parent = cursor;
                duplicate = newNode.compareTo(cursor) == 0;
                if (newNode.compareTo(cursor) < 0) {
                    cursor = cursor.getLeft();
                } else {
                    cursor = cursor.getRight();
                }
            }
            // The while loop ends when it finds a spot for the new node or when discovering
            // a duplicate entry. If there is a duplicate entry, there will be no insertion.
            if (!duplicate) {
                if (newNode.compareTo(parent) < 0) {
                    parent.setLeft(newNode);
                } else {
                    parent.setRight(newNode);
                }
                // Update the number of nodes in the tree
                this.numberOfNodes++;
                // Check if new node contains a string longer than the longest string
                if (newNode.getWord().length() > this.longest.length()) {
                    this.longest = newNode.getWord();
                }
                // Check if new node has a string shorter than the shortest string
                if (newNode.getWord().length() < this.shortest.length()) {
                    this.shortest = newNode.getWord();
                }
            }
        }
    } // method add

    /**
     * In order traversal of a tree
     * 
     * @return a String[] with the contents of the tree as they appear
     */
    public void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println(node.getWord());
            traverseInOrder(node.getRight());
        }
    } // method traverseInOrder

    /**
     * Helper method to start in-order traversal
     */
    public void traverseInOrder() {
        if (this.root != null) {
            this.traverseInOrder(this.root);
        }
    } // helper method traverseInOrder

    /**
     * Helper method that initiates removal of a node with a specific string. The
     * method calls an overloaded version of itself to do the actual digging. The
     * overloaded method can focus on the tree itself (starting from this.root) or
     * any subtree thereof.
     * 
     * @param target string contents of node we wish to remove
     * @return the removed node; if no node found, method returns null
     */
    public TreeNode remove(String target) {
        TreeNode removed = null;
        if (target != null && this.root != null) {
            removed = this.remove(target, this.root);
        }
        return removed;
    } // helper method remove

    /**************************************************************************
     * METHOD STUBS FOR ASSIGNMENT DUE 11/15/24. THESE METHODS ARE INCOMPLETE AND,
     * OBVIOUSLY, LACK DOCUMENTATION. AS PART OF THE ASSIGNMENT, YOU'LL PROVIDE THE
     * NECESSARY COMMENTS AND, OF COURSE, SOME AWESOME CODE.
     **************************************************************************/

    /**
     * Checks if a node containing the target string exists in the tree.
     * 
     * The method traverses the tree, comparing the target string with each node's
     * word. If a match is found, it returns true; otherwise, it continues the
     * search
     * until the target is located or the tree has been fully traversed.
     *
     * @param target The string to search for in the tree.
     * @return true if the target is found, false otherwise.
     */
    public boolean contains(String target) {
        boolean resultFound = false; // Flag to indicate whether the target is found
        TreeNode cursor = this.root; // Start traversal from the root

        // Traverse the tree to search for the target node.
        while (cursor != null && !resultFound) {
            int comparison = target.compareTo(cursor.getWord()); // Compare target with current node's word

            if (comparison == 0) {
                // If the target matches the current node's word, set resultFound to true
                resultFound = true;
            } else if (comparison < 0) {
                // If the target is smaller, move to the left child
                cursor = cursor.getLeft();
            } else {
                // If the target is larger, move to the right child
                cursor = cursor.getRight();
            }
        }

        // Return whether the target was found
        return resultFound;
    } //method contains

    /**
     * Returns a string representation of the Binary Search Tree (BST).
     * If the tree is empty, a message indicating this is included. Otherwise,
     * the string includes information about the number of nodes, and the longest
     * and shortest words present in the tree.
     *
     * @return A string describing the tree's properties.
     */
    @Override
    public String toString() {
        String result = "Binary Search Tree (BST):\n"; // Start with the header

        // Store the number of nodes in the tree
        int numberOfNodes = this.numberOfNodes;

        // Set default values for the longest and shortest words
        String longestWord = DEFAULT_WORD_VALUE;
        String shortestWord = DEFAULT_WORD_VALUE;

        // Check if the tree is empty
        if (this.root == null) {
            result += EMPTY_TREE_MESSAGE; // Add message for an empty tree
        } else {
            // Update the longest and shortest words if available
            if (this.longest != null) {
                longestWord = this.longest;
            }
            if (this.shortest != null) {
                shortestWord = this.shortest;
            }
        } //method toString

        // Append information about the number of nodes, and the longest and shortest
        // words
        result += "Number of Nodes: " + numberOfNodes + "\n";
        result += "Longest Word: " + longestWord + "\n";
        result += "Shortest Word: " + shortestWord + "\n";

        return result; // Return the complete string representation
    }

    /**
     * Removes a node with the specified target word from the tree or subtree.
     * The method first checks if the target exists using the {@link contains}
     * method.
     * If the target is found, it proceeds to remove the node by handling different
     * cases depending on the number of children the node has (no children, one
     * child, or two children).
     * 
     * @param target    The word to search for and remove from the tree.
     * @param belowNode The starting node for the search, which could be the root
     *                  or any subtree.
     * @return The removed node if found, or null if the target node does not exist
     *         in the tree.
     */
    public TreeNode remove(String target, TreeNode belowNode) {
        TreeNode removed = null; // The node that will be removed

        // Check if the target exists in the tree or subtree using the contains method
        if (contains(target)) {
            TreeNode current = belowNode; // Start from the given node for traversal
            TreeNode parent = getParentNode(current, this.root); // Get the parent node for tree modification

            // Traverse the tree to find the target node
            while (current != null) {
                int comparison = target.compareTo(current.getWord()); // Compare the target with the current node's word

                if (comparison == 0) {
                    // Once the target node is found, decide how to remove it based on its children
                    int childrenCount = current.countChildren(); // Count how many children the node has

                    if (childrenCount == 0) {
                        // If the node has no children (leaf node), remove it
                        removed = removeLeafNode(current, parent);
                    } else if (childrenCount == 1) {
                        // If the node has one child, remove it by replacing it with the child
                        removed = removeNodeWithOneChild(current, parent);
                    } else if (childrenCount == 2) {
                        // If the node has two children, handle it by finding and removing the in-order
                        // successor
                        removed = removeNodeWithTwoChildren(current, parent);
                    }
                    break; // Exit the loop once the node has been removed
                } else if (comparison < 0) {
                    // If the target is smaller, move to the left child
                    parent = current;
                    current = current.getLeft();
                } else {
                    // If the target is larger, move to the right child
                    parent = current;
                    current = current.getRight();
                }
            }
        } else {
            // If the target does not exist, print a message and return null
            System.out.println("The target word does not exist in the tree.");
        }

        return removed; // Return the removed node, or null if not found
    } //remove method

    /**
     * Removes a leaf node (a node with no children) from the tree.
     *
     * If the node is the root, the root is set to null. Otherwise, the parent
     * node's
     * pointer to the node is set to null, effectively removing the node.
     *
     * @param nodeToRemove The node to remove.
     * @param parent       The parent of the node to remove.
     * @return The removed node.
     */
    private TreeNode removeLeafNode(TreeNode nodeToRemove, TreeNode parent) {
        if (nodeToRemove == this.root) {
            // If the node is the root, set the root to null
            this.root = null;
        } else {
            // Otherwise, update the parent's left or right pointer to null
            if (parent.getLeft() == nodeToRemove) {
                parent.setLeft(null); // Disconnect the node from its parent (left child)
            } else {
                parent.setRight(null); // Disconnect the node from its parent (right child)
            }
        }

        return nodeToRemove; // Return the removed node
    } //helper method removeLeafNode

    /**
     * Removes a node with one child by replacing it with its only child.
     *
     * If the node to remove is the root, it is replaced by its only child. If it is
     * not the root, the parent’s left or right pointer is updated to point to the
     * child.
     *
     * @param nodeToRemove The node to remove.
     * @param parent       The parent of the node to remove.
     * @return The removed node.
     */
    private TreeNode removeNodeWithOneChild(TreeNode nodeToRemove, TreeNode parent) {
        TreeNode child; // Initialize child TreeNode for the nodeToBeRemoved
        // Check if the node has a left child
        if (nodeToRemove.getLeft() != null) {
            // If the node has a left child, set the child to be the left child
            child = nodeToRemove.getLeft();
        } else {
            // If the node does not have a left child, set the child to be the right child
            child = nodeToRemove.getRight();
        }

        if (nodeToRemove == this.root) {
            // If the node is the root, replace it with its only child
            this.root = child;
        } else {
            // Otherwise, update the parent's left or right pointer to the child
            if (parent.getLeft() == nodeToRemove) {
                parent.setLeft(child); // Replace left child with the actual child
            } else {
                parent.setRight(child); // Replace right child with the actual child
            }
        }

        return nodeToRemove; // Return the removed node
    } //helper method removeNodeWithOneChild

    /**
     * Removes a node with two children. The algorithm follows these steps:
     * 1. It finds the node’s in-order successor (the smallest node in the right
     * subtree).
     * 2. The successor’s value is copied to the node to be removed.
     * 3. The successor is removed from the tree, which will either be a leaf node
     * or have one child.
     *
     * @param nodeToRemove The node to remove, which has two children.
     * @param parent       The parent of the node to remove.
     * @return The removed node, which is the in-order successor (since it’s the one
     *         that gets removed last).
     */
    private TreeNode removeNodeWithTwoChildren(TreeNode nodeToRemove, TreeNode parent) {
        // Step 1: Find the in-order successor (the smallest node in the right subtree)
        TreeNode successorParent = nodeToRemove; // Start with the node to remove
        TreeNode successor = nodeToRemove.getRight(); // Begin by looking at the right child

        // Traverse left until the leftmost node (in-order successor) is found
        while (successor.getLeft() != null) {
            successorParent = successor; // Move the parent pointer to the current node
            successor = successor.getLeft(); // Move left to find the smallest node
        }

        // Step 2: Copy the successor’s value to the node to be removed
        nodeToRemove.setWord(successor.getWord()); // Copy successor’s word to the node

        // Step 3: Remove the successor from the tree
        if (successor.getRight() != null) {
            // If the successor has a right child, remove it using removeNodeWithOneChild
            return removeNodeWithOneChild(successor, successorParent);
        } else {
            // If the successor is a leaf node, remove it using removeLeafNode
            return removeLeafNode(successor, successorParent);
        }
    } //helper method removeNodeWithTwoChild

    /**
     * Finds and returns the parent node of a given child node in the tree.
     * The search starts from the provided starting node (root or subtree).
     *
     * @param child     The node whose parent is to be found.
     * @param belowNode The starting node for the search (could be the root or a
     *                  subtree).
     * @return The parent node of the child, or null if no parent is found.
     */
    private TreeNode getParentNode(TreeNode child, TreeNode belowNode) {
        TreeNode parent = null; // Variable to store the parent node, initialized to null
        TreeNode current = belowNode; // Start the search from belowNode

        // Traverse the tree to find the parent node
        while (current != null && child != null) {
            // If the current node's left or right child is the given child, set parent
            if (current.getLeft() == child || current.getRight() == child) {
                parent = current;
            } else if (child.getWord().compareTo(current.getWord()) < 0) {
                // If the child’s word is smaller, move left
                current = current.getLeft();
            } else {
                // Otherwise, move right
                current = current.getRight();
            }
        }

        return parent; // Return the parent, or null if no parent is found
    } //helper method getParentNode

    /******************************* Accessors *******************************/

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    public String getLongest() {
        return longest;
    }

    public String getShortest() {
        return shortest;
    }
} // class BST
