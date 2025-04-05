public class LinkedList<E> {

    // Inner class

    private class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    // Instance variables

    private Node<E> head;
    private int size;

    // Constructors

    public LinkedList() {
        head = null;
        size = 0;
    }

    // Methods

    /**
     * @return int size of linked list
     */
    public int size() {
        return size;
    }

    /**
     * @return boolean true if linked list is empty
     */
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        } else
        return false;
    }

    /**
     * Removes all data from linked list
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Adds new node at specified index and increments the size of the linked list
     * @param index index you wish to add new node
     * @param newData data associated to new node
     */
    public void add(int index, E newData) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = new Node<E>(newData, head);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index -1; i++) {
                current = current.next;
            }
            current.next = new Node<E>(newData, current.next);
        }
        size++;
    }

    /**
     * Adds new node to the end of the linked list
     * @param newData data associated to new node
     */
    public void add(E newData) {
        add(size, newData);
    }

    public boolean contains(Object o) {
        // May need to write equals method as 1st step
        // Must be careful with nulls and compare values by equality
    }


}
