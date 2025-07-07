package io.github.duckysmacky.dsa.collections.stack;

import java.util.NoSuchElementException;

/// A `Stack` implementation which has a **fixed** size (capacity). It is based on array and cannot grow in size
///
/// @param <E> type of elements in the stack
public class ArrayStack<E> implements Stack<E> {
    private final Object[] innerArray;
    private final int capacity;
    private int size;
    private int top;

    /// Initiate a new `Array Stack` of specified capacity
    public ArrayStack(int capacity) {
        this.innerArray = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.top = -1;
    }

    @Override
    public void push(E element) {
        if (size >= capacity)
            throw new IllegalStateException("Stack is full, cannot add a new element");

        top++;
        size++;
        innerArray[top] = element;
    }

    @Override
    public boolean offer(E element) {
        if (size >= capacity)
            return false;

        top++;
        size++;
        innerArray[top] = element;
        return true;
    }

    @Override
    public E peek() {
        if (top == -1)
            return null;
        return (E) innerArray[top];
    }

    @Override
    public E pop() {
        if (size == 0)
            throw new NoSuchElementException("Stack is empty, nothing to remove");

        E removedElement = (E) innerArray[top];
        top--;
        size--;
        return removedElement;
    }

    @Override
    public E poll() {
        if (size == 0)
            return null;

        E removedElement = (E) innerArray[top];
        top--;
        size--;
        return removedElement;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
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
        for (Object e : innerArray)
            if (e.equals(element)) return true;
        return false;
    }

    @Override
    public void clear() {
        top = -1;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < this.size; i++) {
            builder.append(innerArray[i]);

            if (i == this.size - 2)
                builder.append(" > ");
            else if (i < this.size - 2)
                builder.append(" - ");
        }

        builder.append("]");
        return builder.toString();
    }
}
