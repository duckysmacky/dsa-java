package io.github.duckysmacky.dsa.collections.queue;

import io.github.duckysmacky.dsa.collections.Collection;

/// A `Queue` is a linear data structure that follows the **First-In-First-Out (FIFO)** principle. The element that is
/// inserted first will come out first, and the element that was inserted last, will come out last. It is used by
/// adding (enqueueing) elements to the end of the queue and removing (dequeueing) elements from the start of the
/// queue
///
/// @param <E> type of elements in the stack
public interface Queue<E> extends Collection<E> {
    /// Add (enqueue) an element to the end of the queue. Will throw an exception if the element cannot be added, like
    /// when the queue is full
    ///
    /// @throws IllegalStateException if the element cannot be added
    void add(E element);
    /// Will try to add (enqueue) an element to the queue. If it is possible, will add the provided element to the end of
    /// the queue and return `true`, else won't do anything and will return `false`. This is useful and preferred to
    /// `add()` when working with queues which have a **fixed** size to avoid throwing and catching exceptions
    ///
    /// @return whether was able to add an element to the queue
    boolean offer(E element);
    /// Gets the element on at the start of the queue and returns it, but doesn't remove it from the queue. Will return
    /// `null` if the queue is empty
    ///
    /// @return first element in the queue or `null`
    E peek();
    /// Removes (dequeues) the element from the beginning of the queue. Will throw an exception if the element cannot
    /// be removed, like when the queue is empty
    ///
    /// @throws java.util.NoSuchElementException if there is nothing to remove
    ///
    /// @return first element in the queue
    E remove();
    /// Removes the element from the start of the queue. If the queue is empty, will return `null` to indicate that
    /// there is not element to remove. This is useful and preferred to `remove()` when working with queues which have a
    /// **fixed** size to avoid throwing and catching exceptions
    ///
    /// @return first element in the queue or `null`
    E poll();
    /// Checks if the queue is full. This is applicable if the queue has a fixed size
    ///
    /// @return whether the queue is full
    boolean isFull();
}
