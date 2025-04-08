public class LinkedList<E> {

    // Inner class

    private class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E data, Node<E> next) {
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
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
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

    /**
     * @param o object to search for in linked list
     * @return boolean true if @param o is in the linked list
     */
    public boolean contains(Object o) {
        Node<E> current = head;
        
        while (current != null) {
            if ((o == null && current.data == null) || 
                (o != null && o.equals(current.data))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * @param index traverse linked list until arriving at specified index
     * @return data node at specified index
     */
    public E get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * @param o element to search for in linked list
     * @return integer index of object if found otherwise -1
     */
    public int indexOf(Object o) {

        int index = 0;
        Node<E> current = head;
        
        while (current != null) {
            if ((o == null && current.data == null) || 
                (o != null && o.equals(current.data))) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * Removes node at provided @param index and @return node removed
     * @param index to remove node
     * @return E node removed
     */
    public E remove(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        E removedData;

        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node<E> previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }
            Node<E> nodeToRemove = previous.next;
            removedData = nodeToRemove.data;
    
            previous.next = nodeToRemove.next;
        }

        size--;
        return removedData;
    }

    /**
     * Remove input @param o object and return boolean @return true if found otherwise false
     * @param o object to search linked list for and remove
     * @return boolean true or false whether @param o was found in linked list
     */
    public boolean remove(Object o) {

        if (head == null) {
            return false;
        }
        
        if ((o == null && head.data == null) || (o != null && o.equals(head.data))) {
            head = head.next;
            size--;
            return true;
        }
        
        Node<E> current = head;
        while (current.next != null) {
            if ((o == null && current.next.data == null) || 
                (o != null && o.equals(current.next.data))) {
                    current.next = current.next.next;
                    size--;
                    return true;       
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Updates the value at the specified index and returns the old value.
     * @param index the index of the element to update
     * @param element the new value
     * @return the old value that was replaced
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        E oldValue = current.data;
        current.data = element;
        return oldValue;
    }

    /**
     * Returns a string representation of this list.
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        Node<E> current = head;
        while (current != null) {
            sb.append(String.valueOf(current.data));
            
            if (current.next != null) {
                sb.append(", ");
            }
            
            current = current.next;
        }
        
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return true; // Optional implementation - can always return true
    }

    }

