package io.github.duckysmacky.dsa.collections.list;

import io.github.duckysmacky.dsa.collections.Array;

/// A mutable and growable version of a basic array. Supports adding and removing elements from it. It is ordered and
/// keeps the correct sequence of added and removed elements.
///
/// This implementation of a `List` is based on `Array`, meaning that if there is no more space for new elements, it
/// will grow in size. Each grow increases own capacity by two, and it is a `O(n)` operation. The starting capacity is
/// `16` (if not set by the user).
///
/// @param <E> type of elements in the array list
public class ArrayList<E> implements List<E> {
    private int capacity;
    private int size;
    private Array<E> innerArray;

    /// Initialize an empty `ArrayList` with default starting capacity of `16`
    public ArrayList() {
        this.capacity = 16;
        this.size = 0;
        this.innerArray = new Array<>(16);
    }

    /// Initialize an empty `ArrayList` of specified starting capacity
    public ArrayList(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException("Capacity cannot be less than 1");

        this.capacity = capacity;
        this.size = 0;
        this.innerArray = new Array<>(capacity);
    }

    /// Initialize an `ArrayList` of predefined elements
    @SafeVarargs
    public static <T> ArrayList<T> of(T... elements) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T e : elements)
            arrayList.add(e);
        return arrayList;
    }

    /// Reallocate the inner array so that the new capacity will be twice as big as the previous
    private void grow() {
        int newCapacity = capacity * 2;
        Array<E> newInner = new Array<>(newCapacity);

        for (int i = 0; i < innerArray.size(); i++) {
            newInner.set(i, innerArray.get(i));
        }

        this.capacity = newCapacity;
        this.innerArray = newInner;
    }

    @Override
    public void add(E element) {
        if (size >= capacity) {
            grow();
            add(element);
            return;
        }

        innerArray.set(size, element);
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(index);

        if (size >= capacity) {
            grow();
            add(index, element);
            return;
        }

        if (index == size) {
            add(element);
            return;
        }

        E previous = element;
        for (int i = index; i < this.size + 1; i++) {
            E current = innerArray.get(i);
            innerArray.set(i, previous);
            previous = current;
        }

        size++;
    }

    @Override
    public void set(int index, E value) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(index);

        innerArray.set(index, value);
    }

    @Override
    public E remove() {
        if (size == 0)
            return null;

        E removed = innerArray.get(size - 1);
        innerArray.set(size - 1, null);

        size--;
        return removed;
    }

    @Override
    public E remove(int index) {
        if (size == 0)
            return null;

        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(index);

        if (index == size)
            return remove();

        E removed = innerArray.get(index);

        E previous = null;
        for (int i = this.size - 1; i >= index; i--) {
            E current = innerArray.get(i);
            innerArray.set(i, previous);
            previous = current;
        }

        size--;
        return removed;
    }

    @Override
    public E get(int index) {
        return innerArray.get(index);
    }

    @Override
    public E getFirst() {
        if (size == 0)
            return null;
        return innerArray.getFirst();
    }

    @Override
    public E getLast() {
        if (size == 0)
            return null;
        return innerArray.getLast();
    }

    @Override
    public int find(E element) {
        return innerArray.find(element);
    }


    @Override
    public void reverse() {
        innerArray.reverse();
    }

    @Override
    public E[] toArray() {
        return innerArray.toArray();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return innerArray.isEmpty();
    }

    @Override
    public boolean contains(E element) {
        return innerArray.contains(element);
    }

    @Override
    public void clear() {
        innerArray.clear();
        size = 0;
    }

    @Override
    public String toString() {
        return innerArray.toString();
    }

    @Override
    public int hashCode() {
        return innerArray.hashCode() + capacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayList<?> list)
            return this.innerArray.equals(list.innerArray);

        return false;
    }
}
