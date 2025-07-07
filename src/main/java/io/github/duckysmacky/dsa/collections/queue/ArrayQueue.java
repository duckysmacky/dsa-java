package io.github.duckysmacky.dsa.collections.queue;

import java.util.NoSuchElementException;

/// A `Queue` implementation which has a **fixed** size (capacity). It is based on array and cannot grow in size
///
/// @param <E> type of elements in the queue
public class ArrayQueue<E> implements Queue<E> {
    private final int capacity;
    private final Object[] innerArray;
    private int size;
    private int start;
    private int end;

    /// Initiate a new `Sized Stack` of specified capacity
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.innerArray = new Object[capacity];
        this.size = 0;
        this.start = -1;
        this.end = -1;
    }

    @Override
    public void add(E element) {
        if (size == capacity)
            throw new IllegalStateException("Queue is full, cannot add a new element");

        end = (end + 1) % capacity;
        innerArray[end] = element;

        if (start == -1)
            start = end;

        size++;
    }

    @Override
    public boolean offer(E element) {
        if (size == capacity)
            return false;

        end = (end + 1) % capacity;
        innerArray[end] = element;

        if (start == -1)
            start = end;

        size++;
        return true;
    }

    @Override
    public E peek() {
        if (size == 0)
            return null;

        return (E) innerArray[start];
    }

    @Override
    public E remove() {
        if (size == 0)
            throw new NoSuchElementException("Queue is empty, nothing to remove");

        E removedElement = (E) innerArray[start];
        start = (start + 1) % capacity;
        size--;

        if (size == 0) {
            start = -1;
            end = -1;
        }

        return removedElement;
    }

    @Override
    public E poll() {
        if (size == 0)
            return null;

        E removedElement = (E) innerArray[start];
        start = (start + 1) % capacity;
        size--;

        if (size == 0) {
            start = -1;
            end = -1;
        }

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
        start = -1;
        end = -1;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        int j = start;
        for (int i = 0; i < this.size; i++) {
            builder.append(innerArray[j]);

            if (i < this.size - 1)
                builder.append(" < ");

            j = (j + 1) % capacity;
        }

        builder.append("]");
        return builder.toString();
    }
}
