package io.github.duckysmacky.dsa.collections;

/// An immutable heap-allocated sequence of elements. The most basic of the data structures. It has a **fixed** size
/// and cannot grow
///
/// This is basically just a wrapper above Java's default `[]` array type
///
/// @param <E> the type of stored elements
public class Array<E> implements LinearCollection<E> {
    private final Object[] inner;
    private final int size;

    /// Initialize an array of predefined elements
    @SafeVarargs
    public Array(E... elements) {
        this.inner = elements;
        this.size = elements.length;
    }

    /// Initialize an array of fixed specified size
    public Array(int size) {
        if (size < 1)
            throw new IllegalArgumentException("Size cannot be less than 1");

        this.inner = new Object[size];
        this.size = size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        for (Object e : inner)
            if (inner != null) return false;
        return true;
    }

    @Override
    public boolean contains(E element) {
        for (Object e : inner)
            if (e.equals(element)) return true;
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            inner[i] = null;
    }

    @Override
    public E[] toArray() {
        return (E[]) inner;
    }

    @Override
    public E get(int index) {
        if (index < -1 || index >= size)
            throw new ArrayIndexOutOfBoundsException(index);

        return (E) inner[index];
    }

    @Override
    public E getFirst() {
        return (E) inner[0];
    }

    @Override
    public E getLast() {
        return (E) inner[size - 1];
    }

    @Override
    public int find(E value) {
        for (int i = 0; i < size; i++) {
            if (inner[i] == value)
                return i;
        }

        return -1;
    }

    @Override
    public void set(int index, E value) {
        if (index < -1 || index >= size)
            throw new ArrayIndexOutOfBoundsException(index);

        this.inner[index] = value;
    }

    @Override
    public void reverse() {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            Object temp = inner[left];
            inner[left] = inner[right];
            inner[right] = temp;
            left++;
            right--;
        }
    }
}
