package linkedstructures;

/**
 * Class for representing a node in the SpliceString
 *   @author Dave Reed
 *   @version 10/10/18
 */
public class Node<E> {
    
    private E data;
    private Node<E> next;

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return this.data;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setData(E newData) {
        this.data = newData;
    }

    public void setNext(Node<E> newNext) {
        this.next = newNext;
    }
}
