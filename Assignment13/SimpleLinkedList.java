/**
 * A simple linked list object. Its nodes are defined as an inner class.
 * 
 * COMP 271 FINAL ASSIGNMENT
 * 
 * Review class SimpleLinkedList below. It's a linked list object, quite similar
 * to our TrainLine, only instead of TrainStations it uses Nodes. The nodes are
 * defined as a class-within-the-class -- this is called an inner class and it's
 * a useful coding practice for simple situations like this one here.
 * 
 * Your job is to complete TWO methods in the SimpleLinkedList. You may NOT
 * modify any part of the class and you may not import ANYTHING. Your code
 * should be focused only within the two methods you are asked to write.
 * 
 * 1) Write method findMiddle() that finds and returns the middle node of
 * a SimpleListList. For example, if the SimpleLinkedList object is:
 * 
 * A -> null ... the middle node is A
 * 
 * When the SimpleLinkedList object is:
 * 
 * A -> B --> null ... the middle node is also A
 * 
 * When the SimpleLinkedList object is:
 * 
 * A -> B --> C --> null ... the middle node is B
 * 
 * When the SimpleLinkedList object is:
 * 
 * A -> B --> C --> D --> null ... the middle node is also B
 * 
 * When the SimpleLinkedList object is:
 * 
 * A -> B --> C --> D --> E --> null ... the middle node is C, etc
 * 
 * 
 * 2) Write a method called invert that returns the inverted version of the
 * present SimpleLinkedList object. For example, if the current object is
 * 
 * A --> B --> C --> D --> E --> null
 * 
 * method invert should return the object
 * 
 * E --> D --> C --> B --> A --> null.
 * 
 */

public class SimpleLinkedList {

    /**
     * Inner class for Node. Node fields can be accessed directly, for simplicity of
     * code. This is an intentional violation of the Pact.
     */
    class Node {
        String data;
        Node next;

        /** Simple string representation for Node */
        public String toString() {
            return this.data;
        } // method Node.toString
    } // inner class Node

    /** The only field in class SimpleLinkedList */
    Node head;

    /** Add a new node to the linked list */
    public void add(String data) {
        Node newNode = new Node();
        newNode.data = data;
        if (this.head == null) {
            this.head = newNode;
        } else {
            // Traverse the list to find the last node
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            // current is now the last node in the list
            current.next = newNode;
        }
    } // method add

    /**
     * Finds and returns the middle node of the linked list.
     * If the list has an even number of nodes, it returns the first of the two
     * middle nodes.
     * The method uses a two-pointer approach to traverse the list in one go.
     * 
     * @return the middle node of the linked list, or null if the list is empty.
     */
    public Node findMiddle() {
        Node result = null; // Holds the final middle node to be returned
        Node slowPointer = this.head; // Pointer that moves one step at a time to find the middle
        Node fastPointer = this.head; // Pointer that moves two steps at a time to speed through the list

        // Traverse the list as long as there are nodes for fastPointer to jump ahead
        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next; // Move fastPointer two steps forward
            if (fastPointer != null) { // Only move slowPointer if fastPointer has not reached the end
                slowPointer = slowPointer.next; // Move slowPointer one step forward
            }
        }

        // After traversal, slowPointer points to the middle node
        result = slowPointer; // Assign the middle node to result

        return result; // Return the middle node or null if the list is empty
    } // method SimpleLinkedList.findMiddle

    /**
     * Reverses the linked list by reversing the direction of links.
     * Traverses the list only once, using three pointers to manage the reversal.
     * 
     * @return a new SimpleLinkedList object with nodes in reverse order.
     */
    public SimpleLinkedList invert() {
        SimpleLinkedList reversedList = new SimpleLinkedList(); // New list to hold the reversed nodes
        Node newHead = null; // Will eventually point to the last node of the original list (new head)
        Node currentNode = this.head; // Start from the head of the original list

        // Traverse and reverse the links
        while (currentNode != null) {
            Node nextNode = currentNode.next; // Temporarily store the next node
            currentNode.next = newHead; // Reverse the current node's link to point to the new head, 
            newHead = currentNode; // Update the new head to the current node
            currentNode = nextNode; // Move to the next node in the original list
        }

        // Set the reversed list's head to the new head
        reversedList.head = newHead;
        return reversedList; // Return the reversed list
    } // method SimpleLinkedList.invert

    /** String representation for the simple linked list */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.head != null) {
            Node current = this.head;
            while (current != null) {
                sb.append(String.format("%s", current.data));
                current = current.next;
            }
        }
        return sb.toString();
    } // method SimpleLinkedList.toString

    /**
     * Driver/test code. This is some of the most embarassing code I've ever written
     * but it works. Learn from it and do not write code that bad! :-)
     */
    public static void main(String[] args) {
        SimpleLinkedList demo = new SimpleLinkedList();
        boolean test1 = demo.findMiddle() == null;
        demo.add("A");
        boolean test2 = demo.findMiddle().data.equals("A");
        demo.add("B");
        boolean test3 = demo.findMiddle().data.equals("A");
        demo.add("C");
        boolean test4 = demo.findMiddle().data.equals("B");
        demo.add("D");
        demo.add("E");
        boolean test5 = demo.findMiddle().data.equals("C");
        boolean success = test1 && test2 && test3 && test4 && test5;
        if (success) {
            System.out.println("Method findMiddle works as specified.");
        } else {
            System.out.println("Method findMiddle not working as specified.");
        }
        String left = demo.toString();
        String right = demo.invert().toString();
        boolean test10 = left.length() == right.length();
        for (int i = 0; i < left.length(); i++) {
            test10 = test10
                    && left.charAt(i) == right.charAt(right.length() - 1 - i);
        }
        if (test10) {
            System.out.println("Method invert works as specified.");
        } else {
            System.out.println("Method invert not working as specified.");
        }
    } // method SimpleLinkedList.main

} // class SimpleLinkedList