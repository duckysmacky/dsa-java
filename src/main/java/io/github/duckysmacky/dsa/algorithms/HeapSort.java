package io.github.duckysmacky.dsa.algorithms;

import io.github.duckysmacky.dsa.collections.heap.Heap;

import java.util.ArrayList;

/// Heapsort algorithm implementation
public final class HeapSort {

    private HeapSort() {}

    /// Sorts provided values in ascending order and returns a new sorted array
    @SafeVarargs
    public static <T extends Comparable<? super T>> ArrayList<T> sortAscending(T... unsorted) {
        Heap<T> minHeap = Heap.minHeapify(unsorted);
        ArrayList<T> sorted = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            sorted.add(minHeap.remove());
        }

        return sorted;
    }

    /// Sorts provided values in descending order and returns a new sorted array
    @SafeVarargs
    public static <T extends Comparable<? super T>> ArrayList<T> sortDescending(T... unsorted) {
        Heap<T> minHeap = Heap.maxHeapify(unsorted);
        ArrayList<T> sorted = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            sorted.add(minHeap.remove());
            System.out.println(minHeap);
        }

        return sorted;
    }
}
