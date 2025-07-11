package io.github.duckysmacky.dsa.collections.list;

import io.github.duckysmacky.dsa.collections.LinearCollection;

/// An interface that suggests that the given collection is a modifiable sequence of indexed elements. This means that
/// a new element can be added to this list or an existing one can be removed. Each element also has its own `index` by
/// which it can be returned or changed
///
/// @param <E> type of list's element
public interface List<E> extends LinearCollection<E> {
    /// Add a new element to the list
    void add(E element);
    /// Add a new element at the specified index of the list
    void add(int index, E element);
    /// Remove an element from the list
    E remove();
    /// Remove the element at the specified location
    E remove(int index);
}
