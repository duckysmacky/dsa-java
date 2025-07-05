package io.github.duckysmacky.dsa.data;

/// A linked list which consists of nodes. Each node is connected to the next one, with the last out being connected to
/// nothing. The `head` node is the beginning is the linked list.
///
/// @param <E> the type of elements in a linked list
public class LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int length;

    /// Constructs an empty `Linked List`
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    /// Construct a `Linked List` from predefined elements
    @SafeVarargs
    public LinkedList(E... elements) {
        this.head = new Node<>(elements[0]);

        Node<E> currentNode = this.head;
        for (int i = 1; i < elements.length; i++) {
            Node<E> node = new Node<>(elements[i]);
            currentNode.next = node;
            currentNode = node;
        }

        this.tail = currentNode;
        this.length = elements.length;
    }

    /// Adds data to the start of the list
    ///
    /// This is a `O(1)` operation
    public void addStart(E data) {
        Node<E> newNode = new Node<>(data);

        if (this.head != null)
            newNode.next = this.head;
        if (this.tail == null)
            this.tail = newNode;

        this.head = newNode;
        this.length++;
    }

    /// Adds data to the end of the list
    ///
    /// This is a `O(1)` operation
    public void addEnd(E data) {
        Node<E> newNode = new Node<>(data);

        if (this.tail != null)
            this.tail.next = newNode;
        if (this.head == null)
            this.head = newNode;

        this.tail = newNode;
        this.length++;
    }

    /// Adds data at the specified index
    ///
    /// This is a `O(n)` operation
    public void addAt(int index, E data) {
        if (index == 0)
            addStart(data);
        if (index == length - 1)
            addEnd(data);

        Node<E> newNode = new Node<>(data);

        Node<E> currentNode = this.head;
        for (int i = 0; i < this.length; i++) {
            if (i == index - 1) {
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                break;
            }

            currentNode = currentNode.next;
        }

        this.length++;
    }

    /// A shortcut for the `addEnd` method
    ///
    /// This is a `O(1)` operation
    public void append(E data) {
        addEnd(data);
    }

    /// Removes the node at the beginning of the list and returns its data
    ///
    /// This is a `O(1)` operation
    public E removeStart() {
        Node<E> removedNode = this.head;
        if (removedNode == null) return null;

        this.head = removedNode.next;
        this.length--;
        return removedNode.data;
    }

    /// Removes the node at the end of the list and returns its data
    ///
    /// This is a `O(n)` operation
    public E removeEnd() {
        if (this.head == null) return null;

        Node<E> currentNode = this.head;
        while (!currentNode.isTail()) {
            if (currentNode.next.isTail()) {
                this.tail = currentNode;
                currentNode.next = null;
                break;
            }

            currentNode = currentNode.next;
        }

        this.length--;
        return currentNode.data;
    }

    /// Removes the node at the specified index and returns its data
    ///
    /// This is a `O(n)` operation
    public E removeAt(int index) {
        if (index == 0)
            return removeStart();
        if (index == length - 1)
            return removeEnd();

        Node<E> removedNode = null;
        Node<E> currentNode = this.head;
        for (int i = 0; i < this.length; i++) {
            if (i == index - 1) {
                removedNode = currentNode.next;
                currentNode.next = currentNode.next.next;
                break;
            }

            currentNode = currentNode.next;
        }

        if (removedNode == null) return null;

        this.length--;
        return removedNode.data;
    }

    /// A shortcut for the `removeEnd` method
    public E pop() {
        return removeEnd();
    }

    /// Sets the data at the start of the list
    ///
    /// This is a `O(1)` operation
    public void setStart(E data) {
        this.head.data = data;
    }

    /// Sets the data at the end of the list
    ///
    /// This is a `O(1)` operation
    public void setEnd(E data) {
        this.tail.data = data;
    }

    /// Sets the data at the specified index
    ///
    /// This is a `O(n)` operation
    public void setAt(int index, E data) {
        Node<E> currentNode = this.head;

        for (int i = 0; i < this.length; i++) {
            if (i == index) {
                currentNode.data = data;
                break;
            }

            currentNode = currentNode.next;
        }
    }

    /// Returns the length of the list
    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<E> currentNode = this.head;

        for (int i = 0; i < this.length; i++) {
            builder.append(currentNode.data);
            if (i < this.length - 1)
                builder.append(" -> ");

            currentNode = currentNode.next;
        }

        builder.append("]");
        return builder.toString();
    }

    /// The inner `Node` structure which contains the data and a reference to the next node in the list
    ///
    /// @param <T> the type of contained data
    private static class Node<T> {
        private T data;
        private Node<T> next;

        /// Constructs a new `Node` with no `next` node
        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public boolean isTail() {
            return next == null;
        }

        @Override
        public String toString() {
            String next = this.next == null ? "null" : this.next.data.toString();
            return String.format("{data=%s, next=%s}", data.toString(), next);
        }
    }
}
