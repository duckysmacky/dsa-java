package io.github.duckysmacky.dsa.collections.queue;

import io.github.duckysmacky.dsa.collections.Collection;

/// A Deque (also known as DEQueue) stands for Double Ended Queue. As the name implies, it functions as a queue with
/// two ends, meaning that you can delete and insert from both sides. It combines functionality of both `Stack` and
/// `Queue`
///
/// @param <E> type of element in Deque
public interface Deque<E> extends Collection<E> {
    void addFirst(E element);
    void addLast(E element);
    boolean offerFirst(E element);
    boolean offerLast(E element);
    void setFirst(E element);
    void setLast(E element);
    E removeFirst();
    E removeLast();
    E pollFirst();
    E pollLast();
    E getFirst();
    E getLast();
}
