package io.github.duckysmacky.dsa.data;

/// A linked list which consists of nodes. Each node is connected to the next one, with the last one being connected to
/// nothing. The `head` node is the beginning of the list, while the `tail` is the end
///
/// @param <E> the type of elements in a linked list
public class LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /// Constructs an empty `Linked List`
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
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
        this.size = elements.length;
    }

    /// Adds data at the beginning of the list
    ///
    /// This is a `O(1)` operation
    public void addStart(E data) {
        Node<E> newNode = new Node<>(data);

        if (this.head != null)
            newNode.next = this.head;
        if (this.tail == null)
            this.tail = newNode;

        this.head = newNode;
        this.size++;
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
        this.size++;
    }

    /// Adds data at the specified index. The value will be inserted right before the element at the provided index
    ///
    /// This is a `O(n)` operation
    public void addAt(int index, E data) {
        if (index == 0)
            addStart(data);
        if (index == size - 1)
            addEnd(data);

        Node<E> newNode = new Node<>(data);

        Node<E> currentNode = this.head;
        for (int i = 0; i < this.size; i++) {
            if (i == index - 1) {
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                break;
            }

            currentNode = currentNode.next;
        }

        this.size++;
    }

    /// Removes the element at the beginning of the list and returns it
    ///
    /// This is a `O(1)` operation
    ///
    /// @return removed element
    public E removeStart() {
        Node<E> removedNode = this.head;
        if (removedNode == null) return null;

        this.head = removedNode.next;
        this.size--;
        return removedNode.data;
    }

    /// Removes the element at the end of the list and returns it
    ///
    /// This is a `O(n)` operation
    ///
    /// @return removed element
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

        this.size--;
        return currentNode.data;
    }

    /// Removes the element at the specified index and returns it
    ///
    /// This is a `O(n)` operation
    ///
    /// @return removed element
    public E removeAt(int index) {
        if (index == 0)
            return removeStart();
        if (index == size - 1)
            return removeEnd();

        Node<E> removedNode = null;
        Node<E> currentNode = this.head;
        for (int i = 0; i < this.size; i++) {
            if (i == index - 1) {
                removedNode = currentNode.next;
                currentNode.next = currentNode.next.next;
                break;
            }

            currentNode = currentNode.next;
        }

        if (removedNode == null) return null;

        this.size--;
        return removedNode.data;
    }

    /// Sets the value for the first element of the list
    ///
    /// This is a `O(1)` operation
    public void setStart(E value) {
        this.head.data = value;
    }

    /// Sets the data for the last element of the list
    ///
    /// This is a `O(1)` operation
    public void setEnd(E value) {
        this.tail.data = value;
    }

    /// Sets the data for the element at the specified index
    ///
    /// This is a `O(n)` operation
    public void setAt(int index, E value) {
        Node<E> currentNode = this.head;

        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                currentNode.data = value;
                break;
            }

            currentNode = currentNode.next;
        }
    }

    /// Get the element at the beginning of the list
    ///
    /// This is a `O(1)` operation
    ///
    /// @return first element of the list
    public E getFirst() {
        if (this.head == null) return null;
        return this.head.data;
    }

    /// Get the element at the end of the list
    ///
    /// This is a `O(1)` operation
    ///
    /// @return last element of the list
    public E getLast() {
        if (this.tail == null) return null;
        return this.tail.data;
    }

    /// Get the element at the specified index
    ///
    /// This is a `O(n)` operation
    ///
    /// @return element at the specified index
    public E getAt(int index) {
        Node<E> currentNode = this.head;

        for (int i = 0; i < this.size; i++) {
            if (i == index)
                return currentNode.data;

            currentNode = currentNode.next;
        }

        return null;
    }

    /// Returns the size (length) of the list
    ///
    /// This is a `O(1)` operation
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<E> currentNode = this.head;

        for (int i = 0; i < this.size; i++) {
            builder.append(currentNode.data);
            if (i < this.size - 1)
                builder.append(" -> ");

            currentNode = currentNode.next;
        }

        builder.append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoublyLinkedList<?> list)
            return this.toString().equals(list.toString());
        return super.equals(obj);
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
            return String.format("{%s -> %s}", data.toString(), next);
        }
    }
}
