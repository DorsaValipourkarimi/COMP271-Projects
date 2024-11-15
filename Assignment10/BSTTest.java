public class BSTTest {
    public static void main(String[] args) {
        BST tree = new BST();

        // Test the add method and check the state of the tree
        System.out.println("Adding 'apple', 'banana', 'kiwi', 'cherry', 'date' to the tree.");
        tree.add("apple");
        tree.add("banana");
        tree.add("kiwi");
        tree.add("cherry");
        tree.add("date");

        // Print the tree using toString to check the longest and shortest word logic
        System.out.println("Tree state after adding words:");
        System.out.println(tree.toString());

        // Test contains method
        System.out.println("Contains 'banana': " + tree.contains("banana")); // Should return true
        System.out.println("Contains 'grape': " + tree.contains("grape"));   // Should return false

        // Test remove method: Remove a node that exists
        System.out.println("Removing 'kiwi' from the tree.");
        tree.remove("kiwi");
        System.out.println("Tree state after removing 'kiwi':");
        System.out.println(tree.toString());

        // Test remove method: Try removing a node that doesn't exist
        System.out.println("Removing 'grape' (non-existent) from the tree.");
        tree.remove("grape");  // Should output a message indicating the node does not exist
        System.out.println("Tree state after attempting to remove 'grape':");
        System.out.println(tree.toString());

        // Test remove method: Remove root node 'apple'
        System.out.println("Removing root 'apple' from the tree.");
        tree.remove("apple");
        System.out.println("Tree state after removing 'apple' (root):");
        System.out.println(tree.toString());

        // Test toString when only one node is left
        System.out.println("Removing 'banana' (last node) from the tree.");
        tree.remove("banana");
        System.out.println("Tree state after removing 'banana' (last node):");
        System.out.println(tree.toString()); // Tree should be empty now

        // Test the tree when empty
        System.out.println("Attempting to remove from an empty tree.");
        tree.remove("cherry"); // Should indicate the tree is empty
        System.out.println("Tree state after attempting to remove 'cherry' from empty tree:");
        System.out.println(tree.toString());

        // Test contains method on an empty tree
        System.out.println("Contains 'date' on an empty tree: " + tree.contains("date")); // Should return false

        // Add new nodes again to see if everything works after emptying the tree
        System.out.println("Adding 'fig', 'grape', and 'elderberry' to the tree.");
        tree.add("fig");
        tree.add("grape");
        tree.add("elderberry");

        // Print the tree state after adding new words
        System.out.println("Tree state after adding new words:");
        System.out.println(tree.toString());

        // Test contains method on new words
        System.out.println("Contains 'fig': " + tree.contains("fig")); // Should return true
        System.out.println("Contains 'apple': " + tree.contains("apple")); // Should return false since it was removed

        // Test remove method again: Remove 'grape' (middle node)
        System.out.println("Removing 'grape' from the tree.");
        tree.remove("grape");
        System.out.println("Tree state after removing 'grape':");
        System.out.println(tree.toString());
        
        // Remove root node ('fig')
        System.out.println("Removing 'fig' (root) from the tree.");
        tree.remove("fig");
        System.out.println("Tree state after removing root 'fig':");
        System.out.println(tree.toString());

        // Final state of the tree
        System.out.println("Final tree state:");
        System.out.println(tree.toString());
    }
}
