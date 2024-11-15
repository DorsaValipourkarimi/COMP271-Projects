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
        System.out.println(tree.toString());

        // Test contains method
        System.out.println("Contains 'banana': " + tree.contains("banana")); // Should return true
        System.out.println("Contains 'grape': " + tree.contains("grape"));   // Should return false

        // Test remove method: Remove a node that exists
        System.out.println("Removing 'kiwi' from the tree.");
        tree.remove("kiwi");
        tree.add("bananaaa");

        System.out.println(tree.toString());

        // Test remove method: Try removing a node that doesn't exist
        System.out.println("Removing 'grape' (non-existent) from the tree.");
        tree.remove("grape");  // Should output a message indicating the node does not exist

        // Test toString after removing a non-existing node
        System.out.println(tree.toString());

        // Test remove method: Remove root node 'apple'
        System.out.println("Removing root 'apple' from the tree.");
        tree.remove("apple");
        System.out.println(tree.toString());

        // Test toString when only one node is left
        System.out.println("Removing 'banana' (last node) from the tree.");
        tree.remove("banana");
        System.out.println(tree.toString()); // Tree should be empty now

        // Test the tree when empty
        System.out.println("Attempting to remove from an empty tree.");
        tree.remove("cherry"); // Should indicate the tree is empty
        System.out.println(tree.toString());

        // Test contains method on an empty tree
        System.out.println("Contains 'date' on an empty tree: " + tree.contains("date")); // Should return false
    }
}
