package io.github.duckysmacky.dsa.collections.list;

import io.github.duckysmacky.dsa.collections.queue.Deque;

/// A singly linked list which consists of nodes. Each node is connected to the next one, with the last one being
/// connected to nothing. The `head` node is the beginning of the list, while the `tail` is the end
///
/// @param <E> the type of elements in a linked list
public class LinkedList<E> implements List<E>, Deque<E> {
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

    /// Adds an element to the beginning of the list
    ///
    /// This is a `O(1)` operation
    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.head != null)
            newNode.next = this.head;
        if (this.tail == null)
            this.tail = newNode;

        this.head = newNode;
        this.size++;
    }

    /// Adds an element to the end of the list
    ///
    /// This is a `O(1)` operation
    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.tail != null)
            this.tail.next = newNode;
        if (this.head == null)
            this.head = newNode;

        this.tail = newNode;
        this.size++;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    /// Adds an element at the specified index. The value will be inserted right before the previous element at the
    /// provided index
    ///
    /// This is a `O(n)` operation
    @Override
    public void add(int index, E element) {
        if (index == 0) {
            addLast(element);
            size++;
            return;
        }

        Node<E> newNode = new Node<>(element);

        Node<E> currentNode = this.head;
        for (int i = 0; i < this.size; i++) {
            if (i == index - 1) {
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                this.size++;
                return;
            }

            currentNode = currentNode.next;
        }
    }

    @Override
    public boolean offerFirst(E element) {
        addFirst(element);
        return true;
    }

    @Override
    public boolean offerLast(E element) {
        addLast(element);
        return true;
    }

    /// Removes the element at the beginning of the list and returns it
    ///
    /// This is a `O(1)` operation
    ///
    /// @return removed element
    @Override
    public E removeFirst() {
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
    @Override
    public E removeLast() {
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

    @Override
    public E remove() {
        return removeLast();
    }

    /// Removes the element at the specified index and returns it
    ///
    /// This is a `O(n)` operation
    ///
    /// @return removed element
    @Override
    public E remove(int index) {
        if (index == 0)
            return removeFirst();
        if (index == size - 1)
            return removeLast();

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

    @Override
    public E pollFirst() {
        return removeFirst();
    }

    @Override
    public E pollLast() {
        return removeLast();
    }

    @Override
    public void clear() {
        // TODO
    }

    /// Replaces the first element of the list to the provided element
    ///
    /// This is a `O(1)` operation
    @Override
    public void setFirst(E element) {
        this.head.data = element;
    }

    /// Replaces the last element of the list to the provided element
    ///
    /// This is a `O(1)` operation
    @Override
    public void setLast(E element) {
        this.tail.data = element;
    }

    /// Replaces the element at the specified index the provided element
    ///
    /// This is a `O(n)` operation
    @Override
    public void set(int index, E element) {
        Node<E> currentNode = this.head;

        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                currentNode.data = element;
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
    @Override
    public E getFirst() {
        if (this.head == null) return null;
        return this.head.data;
    }

    /// Get the element at the end of the list
    ///
    /// This is a `O(1)` operation
    ///
    /// @return last element of the list
    @Override
    public E getLast() {
        if (this.tail == null) return null;
        return this.tail.data;
    }

    /// Get the element at the specified index
    ///
    /// This is a `O(n)` operation
    ///
    /// @return element at the specified index
    @Override
    public E get(int index) {
        Node<E> currentNode = this.head;

        for (int i = 0; i < this.size; i++) {
            if (i == index)
                return currentNode.data;

            currentNode = currentNode.next;
        }

        return null;
    }

    @Override
    public int find(E element) {
        // TODO
        return -1;
    }

    @Override
    public boolean contains(E element) {
        // TODO
        return false;
    }

    /// Reverses the list in-place
    ///
    /// This is a `O(n)` operation
    @Override
    public void reverse() {
        if (this.head == null) return;

        Node<E> previous = null;
        Node<E> current = this.head;

        while (current.next != null) {
            Node<E> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        this.tail = this.head;
        this.head = current;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /// Returns the size (length) of the list
    ///
    /// This is a `O(1)` operation
    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        // TODO
        return null;
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
