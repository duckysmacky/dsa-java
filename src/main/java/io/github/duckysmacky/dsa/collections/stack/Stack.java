package io.github.duckysmacky.dsa.collections.stack;

import io.github.duckysmacky.dsa.collections.Collection;

/// A `Stack` is a linear data structure that follows the **Last-In-First-Out (LIFO)** principle. The element that is
/// inserted last will come out first, and the element that was inserted first, will come out last. It is interacted
/// via adding or deleting items to/from the top of the `Stack`.
///
/// @param <E> type of elements in the stack
public interface Stack<E> extends Collection<E> {
    /// Add an element on top of the stack
    ///
    /// @throws IllegalStateException if the element cannot be added
    void push(E element);
    /// Gets the element on top of the stack and returns it, but doesn't remove it from the stack. Will return `null`
    /// if the stack is empty
    ///
    /// @return element on top of the stack
    E peak();
    /// Removes the element on top of the stack and returns it
    ///
    /// @throws IllegalStateException if there is nothing to remove
    ///
    /// @return element on top of the stack
    E pop();
    /// Checks if the stack is full. This is applicable if the stack has a fixed size
    ///
    /// @return whether the stack is full
    boolean isFull();
}
