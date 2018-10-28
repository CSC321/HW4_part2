package linkedstructures;

/**
 * Class that represents Strings that can be efficiently spliced together
 * using structure sharing.
 *   @author Dave Reed
 *   @version 10/10/18
 */
public class SpliceString {
    private Node<String> front;
    private Node<String> back;
    private int numChars;
    
    /** 
     * Constructs an empty SpliceString object.
     */
    public SpliceString() {
        this.front = null;
        this.back = null;
        this.numChars = 0;
    }
    
    /**
     * Constructs a SpliceString object that stores a particular string value.
     *   @param str the value to be stored
     */
    public SpliceString(String str) {
        this.front = new Node<String>(str, null);
        this.back = this.front;
        this.numChars = str.length();
    }
    
    /**
     * Determines the length (number of characters) in this SpliceString.
     *   @return the number of characters in the represented string value
     */
    public int length() {
        return this.numChars;
    }
    
    /**
     * Determines if this SpliceString represents the empty string.
     *   @return true if the empty string is stored, else false
     */
    public boolean empty() {
        return this.length() == 0;
    }
    
    /**
     * Splices a string value to the end of this SpliceString object.
     *   @param str the string value to be spliced
     */
    public void splice(String str) {
        this.splice(new SpliceString(str));
    }
    
    /**
     * Splices another SpliceString object to the end of this one.
     *   @param sstr the SpliceString object to be spliced
     */
    public void splice(SpliceString sstr) {
        if (sstr.length() > 0) {
            if (this.empty())
                this.front = sstr.front;
            else {
                this.back.setNext(sstr.front);
            }
            this.back = sstr.back;
            this.numChars += sstr.length();
        }
    }
    
    /**
     * Returns the underlying string value being represented.
     *   @return the string value
     */    
    public String toString() {
        String str = "";
        Node<String> stepper = front;
        while (stepper != null) {
            str += stepper.getData();
            stepper = stepper.getNext();
        }
        return str;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /** HW4 Part 2 **/
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Returns the character at the specified index. If the index is out of 
     * bounds, the method should throw an IndexOutOfBoundsException. This method
     * should be O(L), where L is the number of nodes in the underlying linked 
     * structure.
     * 
     * @param index
     * @return 
     */
    public char charAt(int index) {
        return 'a';
    }

    /**
     * Returns the substring bounded by the specified indexes. If either index 
     * is out of bounds, or if startInclusive is greater than endExclusive, the
     * method should throw an IndexOutOfBoundsException. This method should be 
     * O(L+N), where L is the number of nodes in the underlying linked structure
     * and N is the size of the substring.
     * 
     * @param startInclusive
     * @param endExclusive
     * @return 
     */
    
    public String substring(int startInclusive, int endExclusive) {
        return "return";
    }
    
    /**
     * Splices the other SpliceString into this SpliceString object, starting at
     * the specified index. Note: this will involve traversing the linked list 
     * to find the node containing the insertion point, splitting that node if 
     * the insertion point appears in the middle of the node's string, and then
     * reconnecting references to splice in the other SpliceString. If the index
     * is negative or greater than the current number of characters stored, the 
     * method should throw an IndexOutOfBoundsException. This method should be 
     * O(L+S), where L is the number of nodes in the underlying linked structure
     * and S is the maximum string size from a single node.
     * 
     * @param index
     * @param other 
     */
    public void splice(int index, SpliceString other) {
        
    }
    
    /**
     * Splices the other String into this SpliceString object, starting at the 
     * specified index. If the index is negative or greater than the current 
     * number of characters stored, the method should throw an 
     * IndexOutOfBoundsException. This method should be O(L+S), where L is the 
     * number of nodes in the underlying linked structure and S is the maximum 
     * string size from a single node.
     * 
     * @param index
     * @param other 
     */
    public void splice(int index, String other) {
        
    }
    
    ////////////////////////////////////////////////////////////////////////////

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
