package io.github.duckysmacky.dsa.collections.heap;

import io.github.duckysmacky.dsa.collections.Collection;
import io.github.duckysmacky.dsa.collections.list.ArrayList;
import io.github.duckysmacky.dsa.collections.list.List;

import java.util.Comparator;

/// Heap is a data structure which is used to manage comparable information. It stores the most preferred element at the
/// top (heap's root), with elements further down being less preferred. There are two type of the Heap: Max and Min.
/// They differ on how they compare and prioritize elements, with max-heap prioritizing the biggest elements, while
/// min-heap prioritizes smallest elements.
///
/// This allows for a `O(1)` constant time access to the most prioritized element at any point, as well as fast delete,
/// insert and search times of `O(log n)`, since the heap's structure resembles a `Binary (Search) Tree`
///
/// @param <E> type of element in heap
public class Heap<E extends Comparable<? super E>> implements Collection<E> {
    private final List<E> innerHeap;
    private final Comparator<E> comparator;

    /// Private constructor so user can only choose between max-heap and min-heap
    private Heap(Comparator<E> comparator, List<E> elements) {
        this.innerHeap = elements;
        this.comparator = comparator;

        for (int i = (innerHeap.size() / 2) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    /// Heapifies the provided elements, creating a new Min-Heap. A Min-Heap will prioritize the smallest elements to be
    /// on top
    @SafeVarargs
    public static <T extends Comparable<? super T>> Heap<T> minHeapify(T... elements) {
        return new Heap<>(Comparator.naturalOrder(), ArrayList.of(elements));
    }

    /// Heapifies the provided elements, creating a new Min-Heap. A Min-Heap will prioritize the smallest elements to be
    /// on top
    public static <T extends Comparable<? super T>> Heap<T> minHeapify(List<T> elements) {
        return new Heap<>(Comparator.naturalOrder(), elements);
    }

    /// Heapifies the provided elements, creating a new Max-Heap. A Max-Heap will prioritize the smallest elements to be
    /// on top
    @SafeVarargs
    public static <T extends Comparable<? super T>> Heap<T> maxHeapify(T... elements) {
        return new Heap<>(Comparator.reverseOrder(), ArrayList.of(elements));
    }

    /// Heapifies the provided elements, creating a new Max-Heap. A Max-Heap will prioritize the smallest elements to be
    /// on top
    public static <T extends Comparable<? super T>> Heap<T> maxHeapify(List<T> elements) {
        return new Heap<>(Comparator.reverseOrder(), elements);
    }

    /// Recursively heapifies downwards starting from the given index, comparing the node's value with its children's
    /// values. This is used to preserve the heap property and will compare the values differently depending on the heap
    /// type (min/max)
    private void heapifyDown(int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        int best = index;

        if (index >= innerHeap.size())
            return;

        if (left < innerHeap.size() && comparator.compare(innerHeap.get(left), innerHeap.get(best)) < 0) {
            best = left;
        }

        if (right < innerHeap.size() && comparator.compare(innerHeap.get(right), innerHeap.get(best)) < 0) {
            best = right;
        }

        if (best == index) return;

        E temp = innerHeap.get(index);
        innerHeap.set(index, innerHeap.get(best));
        innerHeap.set(best, temp);

        heapifyDown(best);
    }

    /// Recursively heapifies upwards starting from the given index, comparing the node's value with its parent's value.
    /// This is used to preserve the heap property and will compare the values differently depending on the heap type
    /// (min/max)
    private void heapifyUp(int index) {
        int parent = getParentIndex(index);
        int best = parent;

        if (index < innerHeap.size() && comparator.compare(innerHeap.get(index), innerHeap.get(best)) < 0) {
            best = index;
        }

        if (best == parent) return;

        E temp = innerHeap.get(parent);
        innerHeap.set(parent, innerHeap.get(best));
        innerHeap.set(best, temp);

        heapifyUp(parent);
    }

    /// Returns the index in the heap array of current node's left child
    private int getLeftChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 1;
    }

    /// Returns the index in the heap array of current node's right child
    private int getRightChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 2;
    }

    /// Returns the index in the heap array of current node's parent
    private int getParentIndex(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    /// Add a new element to the heap. Will automatically assign it to the correct spot to maintain the heap property
    public void add(E element) {
        innerHeap.add(element);
        heapifyUp(innerHeap.size() - 1);
    }

    /// Get the element at the top of the heap. Depending on the type, this will be the biggest/smallest element in the
    /// heap
    public E peek() {
        return innerHeap.get(0);
    }

    /// Remove and return the element at the top of the heap. Depending on the type, this will be the biggest/smallest
    /// element in the heap
    public E remove() {
        E removed = innerHeap.get(0);

        if (innerHeap.size() > 1) {
            innerHeap.set(0, innerHeap.remove(innerHeap.size() - 1));
            heapifyDown(0);
        } else {
            innerHeap.set(0, null);
        }

        return removed;
    }

    @Override
    public int size() {
        return innerHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return innerHeap.isEmpty();
    }

    @Override
    public boolean contains(E element) {
        // TODO: apply comparison-based search (like in a binary tree) to reduce search time complexity
        return innerHeap.contains(element);
    }

    @Override
    public void clear() {
        innerHeap.clear();
    }

    @Override
    public String toString() {
        return innerHeap.toString();
    }
}
