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
     * @param target the string to search for in the tree
     * @return true if the target is found, false otherwise
     */
    public boolean contains(String target) {
        boolean resultFound = false; // Initialize resultFound to false
        TreeNode cursor = this.root; // Start from the root

        // Traverse the tree to find the target
        while (cursor != null && !resultFound) { // Continue as long as cursor is not null and resultFound is false
            int comparison = target.compareTo(cursor.getWord()); // Compare target with the current node's word

            // If the target is found, set resultFound to true
            if (comparison == 0) {
                resultFound = true;
            } else if (comparison < 0) {
                cursor = cursor.getLeft(); // Go to left child if target is smaller
            } else {
                cursor = cursor.getRight(); // Go to right child if target is larger
            }
        }

        // Return resultFound, which indicates whether the target was found
        return resultFound;
    } // method contains

    /**
     * Returns a string representation of the Binary Search Tree (BST).
     * If the tree is empty, it indicates that. Otherwise, it includes
     * information about the number of nodes, longest word, and shortest word.
     * 
     * @return A descriptive string of the tree's properties.
     */
    @Override
    public String toString() {
        // Start building the result string
        String result = "Binary Search Tree (BST):\n";

        // Store the number of nodes in the tree
        int numberOfNodes = this.numberOfNodes;

        // Default values for the longest and shortest words
        String longestWord = DEFAULT_WORD_VALUE;
        String shortestWord = DEFAULT_WORD_VALUE;

        // Check if the tree is empty (root is null)
        if (this.root == null) {
            result += EMPTY_TREE_MESSAGE; // Add message for empty tree
        } else {
            // If the tree has nodes, check for the longest and shortest words
            if (this.longest != null) {
                longestWord = this.longest; // Update longest word if available
            }
            if (this.shortest != null) {
                shortestWord = this.shortest; // Update shortest word if available
            }
        }

        // Add the number of nodes, longest word, and shortest word to the result
        result += "Number of Nodes: " + numberOfNodes + "\n";
        result += "Longest Word: " + longestWord + "\n";
        result += "Shortest Word: " + shortestWord + "\n";

        // Return the complete string representation
        return result;
    } // method toString

    /**
     * Removes a node with the specified target word from the tree.
     * This method traverses the tree to locate the target node,
     * and then removes it based on how many children the node has (leaf(none), one
     * child,
     * or two children).
     *
     * @param target    The word to search for and remove from the tree.
     * @param belowNode The starting point (root or subtree) for the search.
     * @return The removed node, or null if the node is not found.
     */
    public TreeNode remove(String target, TreeNode belowNode) {
        TreeNode removed = null; // The node that will be removed
        TreeNode parent = null; // Parent of the current node being checked
        TreeNode current = belowNode; // Starting point for tree traversal

        // Traverse the tree until it finds the target node
        while (current != null) {
            int comparison = target.compareTo(current.getWord()); // Compare the target with the current node's word
            //parent = getParentNode(current, this.root);
            if (comparison == 0) {
                // Target node found, determine how to remove it based on the number of children
                int childrenCount = current.countChildren(); // Use the countChildren() method to get the number of
                                                             // children
                if (childrenCount == 0) {
                    // Case 1: Node has no children (leaf node)
                    removed = removeLeafNode(current, parent);
                } else if (childrenCount == 1) {
                    // Case 2: Node has one child
                    removed = removeNodeWithOneChild(current, parent);
                } else if (childrenCount == 2) {
                    // Case 3: Node has two children
                    removed = removeNodeWithTwoChildren(current, parent);
                }
            } else if (comparison < 0) {
                // Target is smaller than current node, go to the left child
                parent = current;
                current = current.getLeft();
            } else {
                // Target is larger than current node, go to the right child
                parent = current;
                current = current.getRight();
            }
        }

        return removed; // Return the removed node, or null if not found
    }

    /**
     * Helper method to remove a leaf node (node with no children).
     *
     * @param nodeToRemove The node to remove.
     * @param parent       The parent of the node to remove.
     * @return The removed node.
     */
    private TreeNode removeLeafNode(TreeNode nodeToRemove, TreeNode parent) {
        if (nodeToRemove == this.root) {
            // If node is the root, we set the root to null (no root left)
            this.root = null;
        } else {
            // Otherwise, update the parent's left or right pointer to null
            if (parent.getLeft() == nodeToRemove) {
                parent.setLeft(null); // Disconnect the node from its parent (left child)
            } else {
                parent.setRight(null); // Disconnect the node from its parent (right child)
            }
        }

        // Return the removed node
        return nodeToRemove;
    }

    /**
     * Helper method to remove a node with one child.
     * The node is replaced by its only child.
     *
     * @param nodeToRemove The node to remove.
     * @param parent       The parent of the node to remove.
     * @return The removed node.
     */
    private TreeNode removeNodeWithOneChild(TreeNode nodeToRemove, TreeNode parent) {
        // Determine which child (left or right) the node has
        TreeNode child = (nodeToRemove.getLeft() != null) ? nodeToRemove.getLeft() : nodeToRemove.getRight();

        if (nodeToRemove == this.root) {
            // If node is the root, replace it with its only child
            this.root = child;
        } else {
            // Otherwise, update the parent's left or right pointer to the child
            if (parent.getLeft() == nodeToRemove) {
                parent.setLeft(child); // Replace left child with the actual child
            } else {
                parent.setRight(child); // Replace right child with the actual child
            }
        }

        // Return the removed node
        return nodeToRemove;
    }

    /**
     * Helper method to remove a node with two children.
     * The node is replaced by its in-order successor (smallest node in the right
     * subtree).
     *
     * @param nodeToRemove The node to remove.
     * @param parent       The parent of the node to remove.
     * @return The removed successor node.
     */
    private TreeNode removeNodeWithTwoChildren(TreeNode nodeToRemove, TreeNode parent) {
        // Find the in-order successor (smallest node in the right subtree)
        TreeNode successorParent = nodeToRemove;
        TreeNode successor = nodeToRemove.getRight(); // Start from the right child (to find the in-order successor)

        // Traverse left to find the smallest node (in-order successor)
        while (successor.getLeft() != null) {
            successorParent = successor; // Keep track of the successor's parent
            successor = successor.getLeft(); // Go to the left child to find the smallest node
        }

        // Replace the value of nodeToRemove with the successor's value
        nodeToRemove.setWord(successor.getWord());

        // Now we need to remove the successor node (which will have at most one child)
        if (successor.getRight() != null) {
            // If the successor has a right child, link its parent to that child
            if (successorParent.getLeft() == successor) {
                successorParent.setLeft(successor.getRight());
            } else {
                successorParent.setRight(successor.getRight());
            }
        } else {
            // If the successor has no right child, just remove it (disconnect it from its
            // parent)
            if (successorParent.getLeft() == successor) {
                successorParent.setLeft(null);
            } else {
                successorParent.setRight(null);
            }
        }

        // Return the removed successor node
        return successor;
    }

 

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