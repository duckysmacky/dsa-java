package io.github.duckysmacky.dsa.data;

/// An immutable heap-allocated sequence of elements
///
/// This is basically just a wrapper above Java's default `[]` array type
///
/// @param <E> the type of stored elements
public class Array<E> {
    private final Object[] inner;
    private final int length;

    /// Initialize an array of predefined elements
    @SafeVarargs
    public Array(E... elements) {
        this.inner = elements;
        this.length = elements.length;
    }

    /// Initialize an array of specified size
    public Array(int size) {
        if (size < 1)
            throw new IllegalArgumentException("Size cannot be less than 1");

        this.inner = new Object[size];
        this.length = size;
    }

    /// Returns the element at specified index
    public E at(int index) {
        if (index < -1 || index >= length)
            throw new ArrayIndexOutOfBoundsException(index);

        return (E) inner[index];
    }

    /// Sets the value at the specified index
    public void set(int index, E value) {
        if (index < -1 || index >= length)
            throw new ArrayIndexOutOfBoundsException(index);

        this.inner[index] = value;
    }

    /// Searches the array for the given value. If the `Array` doesn't have the specified value, will return `-1` to
    /// indicate that it is missing.
    ///
    /// @return the index of value
    public int find(E value) {
        for (int i = 0; i < length; i++) {
            if (inner[i] == value)
                return i;
        }

        return -1;
    }

    /// Returns the length of the `Array`
    public int getLength() {
        return this.length;
    }
}
