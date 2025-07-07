package io.github.duckysmacky.dsa.collections;

/// The root `interface` of each and every other collection type or data structure. A collection represents a group
/// of objects of the same type, which are called elements. This interface defines the most basic and required methods
/// for an object which refers to itself as a some kind of data structure.
///
/// This is a simplified version of Java's base `java.util.Collection` type, used for the similar purposes. It basically
/// just mimics its functionality. The API tries to be as similar as possible to the original `java.util.Collection`
///
/// @param <E> type of collection's element
public interface Collection<E> {
    /// Get own size (length)
    ///
    /// @return size of the collection
    int size();
    /// Checks if the collection is empty, meaning there are no elements in it. An "empty" element can be either `null`
    /// or `undefined`
    ///
    /// @return whether the collection is empty
    boolean isEmpty();
    /// Checks if the collection has the supplied element it in
    ///
    /// @return whether the collection contains the element
    boolean contains(E element);
    /// Clears the collection, removing all element from itself
    void clear();
}
