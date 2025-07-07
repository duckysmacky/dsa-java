package io.github.duckysmacky.dsa.collections.stack;

import java.util.NoSuchElementException;

/// A `Stack` implementation which has a **dynamic** size (capacity). It is based on a `Singly Linked List`, where each
/// element is connected to the next via nodes, which allows it to grow in size without reallocation
///
/// @param <E> type of elements in the stack
public class LinkedStack<E> implements Stack<E> {
    private int size;
    private Node<E> top;

    /// Initiate a new empty `Linked Stack`
    public LinkedStack() {
        this.size = 0;
        this.top = null;
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);

        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public boolean offer(E element) {
        push(element);
        return true;
    }

    @Override
    public E peek() {
        if (top == null)
            return null;
        return top.data;
    }

    @Override
    public E pop() {
        Node<E> removedNode = top;
        if (top == null)
            throw new NoSuchElementException("Stack is empty, nothing to remove");

        top = top.next;
        removedNode.next = null;
        size--;
        return removedNode.data;
    }

    @Override
    public E poll() {
        Node<E> removedNode = top;
        if (top == null)
            return null;

        top = top.next;
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
        Node<E> currentNode = top;

        while (currentNode.next != null) {
            if (currentNode.data.equals(element)) return true;
            currentNode = currentNode.next;
        }

        return false;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        Node<E> currentNode = top;
        for (int i = 0; i < size; i++) {
            builder.insert(1, currentNode.data);

            if (i < this.size - 1) {
                if (i == 0)
                    builder.insert(1, " > ");
                else
                    builder.insert(1, " - ");
            }

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
