package io.github.duckysmacky.dsa.collections.stack;

/// A `Stack` implementation which has a **fixed** size (capacity). It is based on array and cannot grow in size
///
/// @param <E> type of elements in the stack
public class ArrayStack<E> implements Stack<E> {
    private final Object[] innerArray;
    private final int capacity;
    private int size;
    private int topIndex;

    /// Initiate a new `Sized Stack` of specified capacity
    public ArrayStack(int capacity) {
        this.innerArray = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.topIndex = -1;
    }

    @Override
    public void push(E element) {
        if (size == capacity)
            throw new IllegalStateException("Stack is full, cannot add new element");

        topIndex++;
        size++;
        innerArray[topIndex] = element;
    }

    @Override
    public E peak() {
        if (topIndex == -1)
            return null;
        return (E) innerArray[topIndex];
    }

    @Override
    public E pop() {
        if (size == 0)
            throw new IllegalStateException("Stack is empty, nothing to remove");

        E removedElement = (E) innerArray[topIndex];
        topIndex--;
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
        topIndex = -1;
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
