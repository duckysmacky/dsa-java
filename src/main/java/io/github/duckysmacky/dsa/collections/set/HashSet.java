package io.github.duckysmacky.dsa.collections.set;

import io.github.duckysmacky.dsa.collections.Array;

/// A `Hash Set` is implementation of `Set` which uses Hash Functions in order to assert indexes for the data and places
/// them in *buckets*. It is really efficient at adding, fetching and removing elements because of that.
///
/// @param <E> type of element in hash set
public class HashSet<E> implements Set<E> {
    private int capacity;
    private double loadFactor;
    private int size;
    private Array<Bucket<E>> buckets;

    /// Creates an empty `Hash Set` with default `capacity` of `10` and `load factor` of `0.75` (`75%`)
    public HashSet() {
        this.capacity = 10;
        this.loadFactor = 0.75d;
        this.size = 0;
        this.buckets = new Array<>(capacity);
    }

    /// Creates an empty `Hash Set` with specified `capacity` and default `load factor` of `0.75` (`75%`)
    public HashSet(int capacity) {
        this.capacity = capacity;
        this.loadFactor = 0.75d;
        this.size = 0;
        this.buckets = new Array<>(capacity);
    }

    /// Creates an empty `Hash Set` with default `capacity` of `10` and specified `load factor`
    public HashSet(double loadFactor) {
        this.capacity = 10;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.buckets = new Array<>(capacity);
    }

    /// Creates an empty `Hash Set` with specified `capacity` and `load factor`
    public HashSet(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.buckets = new Array<>(capacity);
    }

    /// Computes an index based on the provided hash and own capacity
    private int getIndex(int hash) {
        return hash % this.capacity;
    }

    /// Inserts a new `element` at the specified `index` in the provided `buckets` array of `Bucket`. Will traverse the
    /// linked bucket list for each index in order to find a free space and check if the value hasn't been already
    /// inserted.
    ///
    /// *This is a separate method as it's functionality is being used multiple times*
    private void insertAtIndex(Array<Bucket<E>> buckets, int index, E element) {
        Bucket<E> bucket = buckets.get(index);

        if (bucket == null) {
            buckets.set(index, new Bucket<>(element));
            return;
        }

        while (bucket.next != null) {
            if (bucket.data.equals(element)) return;

            bucket = bucket.next;
        }

        if (bucket.data.equals(element)) return;
        bucket.next = new Bucket<>(element);
    }

    /// Checks if the current size is bigger than the allowed capacity (`capacity * loadFactor`) and resizes with
    /// doubled capacity.
    ///
    /// In other terms, if the set is filled up to the load factor, will create a new inner bucket array of
    /// double the current `capacity`, rehash all the values with new `capacity` in mind and place them in the new
    /// bucket array
    ///
    /// This is so there won't be too many elements in the same bucket making the set operations perform much faster
    /// compared to when you need to iterate over multiple items in the same bucket
    private void checkLoadFactor() {
        if (size < capacity * loadFactor) return;

        int newCapacity = capacity * 2;
        Array<Bucket<E>> newBuckets = new Array<>(newCapacity);

        for (int i = 0; i < this.capacity; i++) {
            Bucket<E> originalBucket = buckets.get(i);

            if (originalBucket == null) continue;

            while (originalBucket.next != null) {
                int newIndex = getIndex(originalBucket.data.hashCode());
                insertAtIndex(newBuckets, newIndex, originalBucket.data);

                originalBucket = originalBucket.next;
            }

            int newIndex = getIndex(originalBucket.data.hashCode());
            insertAtIndex(newBuckets, newIndex, originalBucket.data);
        }

        this.capacity = newCapacity;
        this.buckets = newBuckets;
    }

    @Override
    public void add(E element) {
        int index = getIndex(element.hashCode());
        insertAtIndex(this.buckets, index, element);

        this.size++;
        checkLoadFactor();
    }

    @Override
    public void remove(E element) {
        int index = getIndex(element.hashCode());
        Bucket<E> bucket = buckets.get(index);

        if (bucket == null) return;

        if (bucket.data.equals(element)) {
            if (bucket.next != null) {
                buckets.set(index, bucket.next);
            } else {
                buckets.set(index, null);
            }
            this.size--;
            return;
        }

        Bucket<E> previousBucket = bucket;
        bucket = bucket.next;
        while (bucket.next != null) {
            if (bucket.data.equals(element)) {
                previousBucket.next = bucket.next;
                bucket.next = null;
                this.size--;
                return;
            }

            previousBucket = bucket;
            bucket = bucket.next;
        }

        if (bucket.data.equals(element)) {
            previousBucket.next = null;
            this.size--;
        }
    }

    @Override
    public boolean contains(E element) {
        int index = getIndex(element.hashCode());
        Bucket<E> bucket = buckets.get(index);

        if (bucket == null) return false;

        while (bucket.next != null) {
            if (bucket.data.equals(element)) return true;

            bucket = bucket.next;
        }
        if (bucket.data.equals(element)) return true;

        return false;
    }

    @Override
    public Set<E> union(Set<E> other) {
        // TODO
        return null;
    }

    @Override
    public Set<E> intersection(Set<E> other) {
        // TODO
        return null;
    }

    @Override
    public Set<E> difference(Set<E> other) {
        // TODO
        return null;
    }

    @Override
    public boolean subset(Set<E> other) {
        // TODO
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < this.capacity; i++) {
            if (buckets.get(i) != null) return false;
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.capacity; i++) {
            Bucket<E> bucket = buckets.get(i);

            if (bucket == null) continue;
            if (bucket.next == null) {
                buckets.set(i, null);
            }

            Bucket<E> previousBucket;
            while (bucket.next != null) {
                previousBucket = bucket;
                bucket = bucket.next;
                previousBucket.next = null;
            }
            buckets.set(i, null);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");

        for (int i = 0; i < this.capacity; i++) {
            Bucket<E> bucket = buckets.get(i);

            if (bucket == null) continue;

            while (bucket.next != null) {
                builder.append(bucket.data);
                builder.append(", ");
                bucket = bucket.next;
            }

            builder.append(bucket.data);
            builder.append(", ");
        }

        if (builder.length() > 3)
            builder.delete(builder.length() - 2, builder.length());

        builder.append("}");
        return builder.toString();
    }

    /// A more descriptive string representation with each bucket showing separately
    public String toStringDebug() {
        StringBuilder builder = new StringBuilder("{");

        for (int i = 0; i < this.capacity; i++) {
            Bucket<E> bucket = buckets.get(i);

            if (bucket == null) continue;
            builder.append("(");

            while (bucket.next != null) {
                builder.append(bucket.data);
                builder.append(", ");
                bucket = bucket.next;
            }

            builder.append(bucket.data);
            builder.append(", ");

            if (builder.length() > 3)
                builder.delete(builder.length() - 2, builder.length());

            builder.append("), ");
        }

        if (builder.length() > 3)
            builder.delete(builder.length() - 2, builder.length());

        builder.append("}");
        return builder.toString();
    }

    /// A `Bucket` is an indexed storage location for one or more elements of type `T` in a `Hash Set`. A single bucket
    /// can hold one or more elements, each being linked to each other similarly to a `Linked List`
    ///
    /// @param <T> type of element in a bucket
    private static class Bucket<T> {
        private T data;
        private Bucket<T> next;

        public Bucket(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            String next = this.next == null ? "null" : this.next.data.toString();
            return String.format("{%s -> %s}", data.toString(), next);
        }
    }
}
