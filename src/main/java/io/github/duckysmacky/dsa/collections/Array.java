package io.github.duckysmacky.dsa.collections;

import java.util.Arrays;

/// An immutable heap-allocated sequence of elements. The most basic of the data structures, which has a **fixed**
/// size (capacity) and cannot grow. This class also provides basic methods to interact with the array, like reversing.
///
/// This is basically just a wrapper above Java's default `[]` array type. It is almost identical to the basic Java
/// array, but allows for generic type initialization, which is useful when using arrays in the other generic data
/// structures as an internal structure.
///
/// #### Example
/// ```
/// T[] array = new T[5];
/// ```
/// The above **is not** allowed in Java and will lead to a **compiler error**. The only solution to this is to use a
/// `Object[]` instead, which will require cast type checks. This is what this `Array` type uses internally.
///
/// Meanwhile, the following code **is** allowed and provides a better user experience when dealing with arrays of
/// generic types, since you don't need to check cast type from `Object` to `T` every time:
/// ```
/// Array<T> array = new Array(5);
/// ```
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
        for (int i = 0; i < size; i++)
            if (inner[i] != null) return false;
        return true;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++)
            if (inner[i].equals(element)) return false;
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            inner[i] = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        return (E[]) inner;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(index);

        return (E) inner[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E getFirst() {
        return (E) inner[0];
    }

    @SuppressWarnings("unchecked")
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
        if (index < 0 || index >= size)
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

    @Override
    public String toString() {
        return Arrays.toString(inner);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(inner);
    }
}
