package io.github.duckysmacky.dsa.collections;

/// This interface implies that the given collection is linear and contains sequenced elements, meaning that each
/// element has its own `index` which is used to identify it.
///
/// @param <E> type of list's element
public interface LinearCollection<E> extends Collection<E> {
    /// Get the element at the specified index
    ///
    /// @return element at the index
    E get(int index);
    /// Get the first element
    ///
    /// @return element at the start of the collection
    E getFirst();
    /// Get the last element
    ///
    /// @return element at the end of the collection
    E getLast();
    /// Searches the collection for the specified element. If the element is found, returns its index, else will return
    /// `-1`
    ///
    /// @return index of element
    int find(E element);
    /// Set the value of the element at the specified index
    void set(int index, E value);
    /// Reverse the order of the elements in the collection in-place
    void reverse();
    /// Returns all the collection's elements as a basic Java array
    ///
    /// @return an array of type `E`
    E[] toArray();
}
