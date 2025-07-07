package io.github.duckysmacky.dsa.collections.queue;

import java.util.NoSuchElementException;

/// A `Queue` implementation which has a **dynamic** size (capacity). It is based on a `Singly Linked List`, where each
/// element is connected to the next via nodes, which allows it to grow in size without reallocation
///
/// @param <E> type of elements in the queue
public class LinkedQueue<E> implements Queue<E> {
    private int size;
    private Node<E> start;
    private Node<E> end;

    /// Initiate a new empty `Linked Queue`
    public LinkedQueue() {
        this.size = 0;
        this.start = null;
        this.end = null;
    }

    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element);

        if (end != null) {
            end.next = newNode;
        }
        end = newNode;

        if (start == null) {
            start = newNode;
        }
        size++;
    }

    @Override
    public boolean offer(E element) {
        add(element);
        return true;
    }

    @Override
    public E peek() {
        if (start == null)
            return null;

        return start.data;
    }

    @Override
    public E remove() {
        Node<E> removedNode = start;

        if (removedNode == null)
            throw new NoSuchElementException("Stack is empty, nothing to remove");

        start = start.next;
        removedNode.next = null;
        size--;
        return removedNode.data;
    }

    @Override
    public E poll() {
        Node<E> removedNode = start;
        if (removedNode == null)
            return null;

        start = start.next;
        removedNode.next = null;
        size--;
        return removedNode.data;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        Node<E> currentNode = start;
        while (currentNode.next != null) {
            if (currentNode.data.equals(element)) return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public void clear() {
        start = null;
        end = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        Node<E> currentNode = start;
        if (start == null) return "[]";

        for (int i = 0; i < size; i++) {
            builder.append(currentNode.data);

            if (i < this.size - 1)
                builder.append(" < ");

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
            return String.format("{%s -> %s}", data.toString(), next);
        }
    }
}
