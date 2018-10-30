package linkedstructures;

import java.io.IOException;

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
    /** 
    * HW4 Part 2 
    * @author Kikki Beltz
    * @version October 2018
    */
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
        Node stepper = this.front;
        int counter = 0;
        char ch = 0;
        while(stepper != null) { 
            String nodeData = (String) stepper.getData();
            if((counter + nodeData.length() - 1) >= index) {
                int newIndex = index - counter;
                ch = nodeData.charAt(newIndex);
                break;
            }
            counter += stepper.getData().toString().length();
            stepper = stepper.getNext();  
        }
        return ch;
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
        int counter = -1;
        int prevCounter = -1;
        String substring = "";
        if(startInclusive<0 || startInclusive>=this.length() ||
        endExclusive<0 || endExclusive>this.length() ||
        startInclusive>endExclusive) {
            throw new IndexOutOfBoundsException();
        } else {
            Node stepper = this.front;
            while(stepper != null) {
                if(prevCounter == -1) {
                    prevCounter = 0;
                } else {
                    prevCounter = counter + 1;
                }
                counter += stepper.getData().toString().length();
                    if(prevCounter<=startInclusive && counter+1>=endExclusive) {
                        for(int i=(startInclusive-prevCounter); i<(endExclusive-prevCounter); i++) {
                            substring += stepper.getData().toString().charAt(i);
                        }
                    } 
                stepper = stepper.getNext();
            }
        }
        return substring;
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
        int counter = -1;
        int prevCounter = -1;
        if(index<0 || index>this.length()) {
            throw new IndexOutOfBoundsException();
        } else {
            Node stepper = this.front;
            Node prev = null;
            while(stepper != null) {
                if(prevCounter == -1) {
                    prevCounter = 0;
                } else {
                   prevCounter = counter+1; 
                }
                counter += stepper.getData().toString().length();
                if(prevCounter < index && counter > index) {
                    Node<String> last = new Node<String>(this.substring(index+1, counter), stepper.getNext());
                    Node<String> middle = new Node<String>(other.toString(), last);
                    Node<String> first = new Node<String>(this.substring(prevCounter, index), middle);
                    prev.setNext(first);
                } else if(prevCounter == index) {
                    Node<String> last = new Node<String>(this.substring(index+1, counter), stepper.getNext());
                    Node<String> middle = new Node<String>(other.toString(), last);
                    if(index == 0) {
                        this.front.setNext(middle);
                    } else {
                        prev.setNext(middle);
                    }
                } else if((counter) == index) {
                    Node<String> newNode = new Node<String>(other.toString(), stepper.getNext());
                    stepper.setNext(newNode);
                } 
                prev = stepper;
                stepper = stepper.getNext();
            }
        }
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
        SpliceString spliceString = new SpliceString(other);
        this.splice(index, spliceString);
    }
    
    /**
     * Returns the lowest index of the character
     * @param ch
     * @return 
     * @throws java.io.IOException 
     */
    public int indexOf(Character ch) throws IOException {
        int index = 0;
        if(this.contains(ch.toString())) {
            Node<String> stepper = this.front;
            while(stepper != null) {
                if(stepper.getData().contains(ch.toString())) {
                    for(int i=0;i<stepper.getData().length();i++) {
                        if(stepper.getData().charAt(i) == ch) {
                            return index += i;
                        }
                    }
                }
                index += stepper.getData().length();
                stepper = stepper.getNext(); 
            }
            return index;
        } else {
            throw new IOException();
        }
    }
    
    /**
     * 
     * @param str
     * @return 
     */
    public boolean contains(String str) {
        //doesn't take into account spanning nodes
        boolean flag = false;
        Node stepper = this.front;
        while(stepper != null) {
           if(stepper.getData().toString().contains(str)) {
               flag = true;
           }
           stepper = stepper.getNext();
        }
        return flag;
    }
    
    /**
     * 
     * @param str 
     */
    public void remove(String str) {
        //not functional
    }
    
    ////////////////////////////////////////////////////////////////////////////

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        SpliceString sstr3 = new SpliceString("ABCD");
        sstr3.splice("EFGGH");
        sstr3.splice("IJKL");
        SpliceString sstr4 = new SpliceString("QQQ");
//        Test charAt
        for(int i=0; i<sstr3.length(); i++) {
            System.out.println(sstr3.charAt(i));
        }
//        Test substring
//        mid-node
        System.out.println(sstr3.substring(1, 2));
//        pre-node
        System.out.println(sstr3.substring(4, 6));
//        post-node
        System.out.println(sstr3.substring(6, 9));
//        Test splice SpliceString
//        mid-node
        sstr3.splice(6, sstr4);
        System.out.println(sstr3.toString());
//        pre-node
        sstr3.splice(4, sstr4);
        System.out.println(sstr3.toString());
//        post-node
        sstr3.splice(9, sstr4);
        System.out.println(sstr3.toString());
//        Test splice string
        SpliceString sstr6 = new SpliceString("ABCD");
        sstr6.splice("EFGGH");
        sstr6.splice("IJKL");
//        mid-node
        sstr6.splice(6, "RRR");
        System.out.println(sstr6.toString());
//        pre-node
        sstr6.splice(4, "RRR");
        System.out.println(sstr6.toString());
//        post-node
        sstr6.splice(9, "RRR");
        System.out.println(sstr6.toString());
//        Test indexOf
        SpliceString sstr5 = new SpliceString("ABCD");
        sstr5.splice("EFGGH");
        sstr5.splice("IJKL");
//        mid-node
        System.out.println(sstr5.indexOf('F'));
//        pre-node
        System.out.println(sstr5.indexOf('A'));
//        post-node
        System.out.println(sstr5.indexOf('L'));
//        Test contains
        System.out.println(sstr3.contains("BC"));
//        pre-node
        System.out.println(sstr3.contains("EFG"));
//        post-node
        System.out.println(sstr3.contains("JKL"));
//        span-node
        System.out.println(sstr3.contains("DEFG"));
//        whole-node
        System.out.println(sstr3.contains("EFGGH"));
    }
    
}
