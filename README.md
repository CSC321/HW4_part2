# HW4: Linked Structures (part 2)

For this assignment, you will build upon your SpliceString class from HW3. In particular, you will add methods that enable extracting from and splicing into the middle of strings:

`public char charAt(int index)`

Returns the character at the specified index. If the index is out of bounds, the method should throw an IndexOutOfBoundsException. This method should be O(L), where L is the number of nodes in the underlying linked structure.

`public String substring(int startInclusive, int endExclusive)`

Returns the substring bounded by the specified indexes. If either index is out of bounds, or if startInclusive is greater than endExclusive, the method should throw an IndexOutOfBoundsException. This method should be O(L+N), where L is the number of nodes in the underlying linked structure and N is the size of the substring.

`public void splice(int index, SpliceString other)`

Splices the other SpliceString into this SpliceString object, starting at the specified index. Note: this will involve traversing the linked list to find the node containing the insertion point, splitting that node if the insertion point appears in the middle of the node's string, and then reconnecting references to splice in the other SpliceString. If the index is negative or greater than the current number of characters stored, the method should throw an IndexOutOfBoundsException. This method should be O(L+S), where L is the number of nodes in the underlying linked structure and S is the maximum string size from a single node.

`public void splice(int index, String other)`

Splices the other String into this SpliceString object, starting at the specified index. If the index is negative or greater than the current number of characters stored, the method should throw an IndexOutOfBoundsException. This method should be O(L+S), where L is the number of nodes in the underlying linked structure and S is the maximum string size from a single node.
Since the point of the SpliceString class is to allow for fast operations due to structure sharing, you must be careful to meet the efficiency requirements for each method. For example, implementing charAt or substring by first converting the SpliceString object to a String and then calling the corresponding String method will not receive credit. As before, include a simple driver program that tests each of these methods.

For extra credit, you may implement other SpliceString methods, such as indexOf, contains, and remove.
