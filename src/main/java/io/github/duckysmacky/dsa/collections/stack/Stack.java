package io.github.duckysmacky.dsa.collections.stack;

import io.github.duckysmacky.dsa.collections.Collection;

/// A `Stack` is a linear data structure that follows the **Last-In-First-Out (LIFO)** principle. The element that is
/// inserted last will come out first, and the element that was inserted first, will come out last. It is interacted
/// via adding or deleting items to/from the top of the `Stack`.
///
/// @param <E> type of elements in the stack
public interface Stack<E> extends Collection<E> {
    /// Add (push) an element to the top of the stack. Will throw an exception if the element cannot be added, like
    /// when the stack is full
    ///
    /// @throws IllegalStateException if the element cannot be added
    void push(E element);
    /// Will try to add (push) an element to the stack. If it is possible, will add the provided element to the top of
    /// the stack and return `true`, else won't do anything and will return `false`. This is useful and preferred to
    /// `push()` when working with stacks which have a **fixed** size to avoid throwing and catching exceptions
    ///
    /// @return whether was able to add an element to the stack
    boolean offer(E element);
    /// Gets the element on top of the stack and returns it, but doesn't remove it from the stack. Will return `null`
    /// if the stack is empty
    ///
    /// @return element on top of the stack or `null`
    E peek();
    /// Removes the element from the top of the stack. If the stack is empty, will throw an exception to indicate that
    /// there is not element to remove
    ///
    /// @throws java.util.NoSuchElementException if there is nothing to remove
    ///
    /// @return element on top of the stack
    E pop();
    /// Removes the element from the top of the stack. If the stack is empty, will return `null` to indicate that
    /// there is not element to remove. This is useful and preferred to `pop()` when working with stacks which have a
    /// **fixed** size to avoid throwing and catching exceptions
    ///
    /// @return element on top of the stack or `null`
    E poll();
    /// Checks if the stack is full. This is applicable if the stack has a fixed size
    ///
    /// @return whether the stack is full
    boolean isFull();
}
