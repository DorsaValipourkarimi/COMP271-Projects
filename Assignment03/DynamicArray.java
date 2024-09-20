/**
 * A simple class to demonstrate dynamic behavior with arrays. Objects of this
 * class store strings in an array that grows to match the demand for storage.
 * 
 * The class is based on an underlying string array. Objects can be initialized
 * to any size; otherwise they'll be initialized to the default size. For
 * example,
 * 
 * DynamicArray da1 = new DynamicArray(10);
 * 
 * will have initially room for 10 strings, while
 * 
 * DynamicArray da2 = new DynamicArray();
 * 
 * will have initially room for 4 strings.
 */
public class DynamicArray {

    /** Default size for underlying array */
    private static final int DEFAULT_SIZE = 4;

    /** The underlying array for this class */
    private String[] foundation;

    /** Measures how many places in the array are in use */
    private int occupancy;

    /**
     * Full constructor. Initializes the underlying array to the specified size. The
     * size must be a positive, non zero value. Otherwise the constructor uses the
     * default size value.
     */
    public DynamicArray(int size) {
        // If size <= 0 use default -- this is a good time to demo ternary operator
        size = (size > 0) ? size : DEFAULT_SIZE;
        this.foundation = new String[size];
        this.occupancy = 0;
    } // full constructor

    /**
     * Array-based constructor -- used for testing.
     * 
     * WARNING: SHALLOW ARRAY COPY
     * 
     * @param data
     */
    public DynamicArray(String[] data) {
        this(DEFAULT_SIZE);
        if (data != null) {
            this.foundation = data;
            this.occupancy = data.length;
        }
    } // array-based constructor

    /**
     * Default constructor
     */
    public DynamicArray() {
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * Checks if the specified string is present in the dynamic array.
     * 
     * @param target The string to search for in the array
     * @return true if the string is found, false otherwise
     */
    public boolean contains(String target) {
        boolean found = false;
        /*
         * Before introducing this.occupancy in the object, the method traversed
         * this.foundation through its entire length, i.e., the while loop allowed its
         * index i to reach this.foundation.length. As we saw, however, not every
         * element in this.foundation may be used. this.occupancy tells us what is the
         * last used element in this.foundation. There is no point searching after that
         * element, as all values are going to be null. So, for the while loop here we
         * change the condition from while(i<this.foundation length &...) to
         * while(i<this.occupancy &&...)
         */
        if (target != null && this.foundation != null) {
            int i = 0;
            // No need to guard against occupancy==0, because if array is empty, loop will
            // not even run.
            while (i < this.occupancy && !found) {
                found = this.foundation[i] != null && this.foundation[i].equals(target);
                i++;
            }
        }
        return found;
    } // method contains

    /**
     * Retrieves the string at the specified index in the array.
     * 
     * @param index The index of the string to retrieve
     * @return The string at the specified index, or null if the index is invalid
     */
    public String get(int index) {
        String string = null;
        // No need to guard against occupancy==0, because if array is empty, the method
        // will return null anyway
        if (index >= 0 && this.foundation != null && index < this.foundation.length) {
            string = this.foundation[index];
        }
        return string;
    } // method get

    /**
     * Removes the string at the specified index in the array and sets its position
     * to null. Then it moves every element to the right of the removed element, one
     * position to the left. The position of the last element to be copied to the
     * left is then emptied out (null).
     * 
     * @param index The index of the string to remove
     * @return The string that was removed, or null if the index is invalid
     */
    public String remove(int index) {
        String removed = null;
        // We check occupancy, because there is no reason to perform this in an empty
        // array
        if (this.occupancy > 0 && index >= 0 && index < this.foundation.length) {
            removed = this.foundation[index];
            this.foundation[index] = null;
            // Shift things after the removed string, one position to the left
            for (int i = index; i < occupancy - 1; i++) {
                this.foundation[i] = this.foundation[i + 1];
            }
            // Previously last occupied cell, now empty
            this.foundation[occupancy - 1] = null;
            // update occupancy
            this.occupancy--;
        }
        return removed;
    } // method remove

    /**
     * Deletes the string at the specified index in the array.
     * 
     * This method uses this.remove and simply ignores the returned string.
     * 
     * @param index The index of the string to delete
     */
    public void delete(int index) {
        remove(index);
    } // method delete

    /**
     * Resizes the underlying array by increasing its capacity by 1.
     * 
     * This method is called internally when the current array reaches its capacity
     * and a new element needs to be inserted.
     */
    private void resize() {
        String[] temp = new String[this.foundation.length + 1];
        /*
         * Instead of:
         * for (int i = 0; i < this.foundation.length; i++) {
         * we can write
         * for (int i = 0; i < this.occupancy; i++) {
         * since there is no reason to copy null values from one array to another.
         */
        for (int i = 0; i < this.occupancy; i++) {
            temp[i] = this.foundation[i];
        }
        this.foundation = temp;
    } // method resize

    /**
     * Inserts a new string into the dynamic array.
     * 
     * If the string is not null and the array is full, it will be resized to
     * accommodate the new element.
     * 
     * @param string The string to insert into the array
     */
    public void insert(String string) {
        // Guard against null argument
        if (string != null) {
            // If there is no room left in underlying array, resize it first
            if (this.occupancy == this.foundation.length) {
                this.resize();
            }
            // Room in underlying array assured
            this.foundation[this.occupancy] = string;
            this.occupancy++;
        }
    } // method insert

    /**
     * Creates a textual representation of the dynamic array.
     * This method only includes the occupied elements of the array, and it skips
     * over
     * any unoccupied (null) values.
     * 
     * @return A string representing the contents of the dynamic array.
     */
    @Override
    public String toString() {
        // Start with the initial part of the returning string
        String result = "DynamicArray: [";

        // Loop through the occupied elements of the array
        for (int i = 0; i < this.occupancy; i++) {
            // Add each element to the string
            result += this.foundation[i];

            // Add a comma and space between elements, but not after the last element
            if (i < this.occupancy - 1) {
                result += ", ";
            }
        }

        // Close the array representation
        // result += "]";
        return result + "]"; // Return the final string
    }

    /**
     * Returns the index of the first occurrence of the specified string in the
     * dynamic array.
     * If the string is not found, it returns -1.
     * 
     * @param string The string to search for
     * @return The index of the string, or -1 if not found
     */
    public int index(String string) {
        int temp = -1; // initialized to -1 (in case we fail to find it)
        boolean target = false; // Used to keep track of whether of bnot the string has occured, since we only
                                // want the index of the first occurance
        for (int i = 0; i < this.occupancy; i++) {
            if (this.foundation[i] != null && this.foundation[i].equals(string) && !target) {
                temp = i;
                target = true;
            }
        }
        return temp;
    } // method index

    /**
     * Returns the percentage of non-null elements in the dynamic array.
     * The result is rounded to two decimal places.
     * 
     * @return The usage percentage as a double
     */
    public double usage() {
        double result = 0.0;
        if (this.foundation.length != 0) {
            double usage = (double) this.occupancy / this.foundation.length;
            result = Math.round(usage * 100.0) / 100.0; // Round to 2 decimal places
        }
        return result;
    } // method usage

    /** Driver/test code */
    public static void main(String[] args) {
        final String PASS = "Pass";
        final String FAIL = "Fail";
        final String NON_EXISTING = "COBOL";
        // Test data

        String[] testData = { "Java", "Python", "C", "C++", "Fortran", "C" };
        DynamicArray test = new DynamicArray(testData);
        DynamicArray emptyTest = new DynamicArray(3);
        System.out.println(emptyTest.usage());
        emptyTest.insert("Java");
        System.out.println(emptyTest.usage());
        System.out.println(emptyTest.toString());
        System.out.println(test.toString());
        System.out.println(test.index("C"));
        System.out.println(test.usage());
    } // method main

} // class DynamicArray