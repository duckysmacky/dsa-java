package io.github.duckysmacky.dsa.collections.set;

import io.github.duckysmacky.dsa.collections.Collection;

/// A set is a data structure which contains only unique elements.
///
/// @param <E> type of set's elements
public interface Set<E> extends Collection<E> {
    /// Add a new element to the set. If the element is already present in the set, it won't be added
    ///
    /// This is a `O(1)` operation in the best case, `O(n)` in the worst case
    void add(E element);
    /// Removes an element from the set. If the element is not present in the set, nothing will be done
    ///
    /// This is a `O(1)` operation in the best case, `O(n)` in the worst case
    void remove(E element);
    /// A mathematical operation of set union
    ///
    /// @return a new `Set`, which contains elements that belong to the **original set**, the **other set** or **both sets**
    Set<E> union(Set<E> other);
    /// A mathematical operation of set intersection
    ///
    /// @return a new `Set`, which contains elements that belong **only to both sets**
    Set<E> intersection(Set<E> other);
    /// A mathematical operation of set difference
    ///
    /// @return a new `Set`, which contains element that belong **only to the original set**
    Set<E> difference(Set<E> other);
    /// A mathematical definition of what a subset is. A set is considered a **subset** when **all elements** from the
    /// first set **belong to the second**)
    ///
    /// @return whether the original set is a subset of the other set
    boolean subset(Set<E> other);
}
