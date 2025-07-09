# Data Structures and Algorithms

This project consists of different classical data structure and algorithm implementations written in Java

## About

This project is mostly just a sandbox environment to practice my skills and knowledge of different DSA topics. Java was 
selected for purely personal experience and overall development comfort reasons, so most of the presented concrete data 
structures are implemented mostly **language-independent**. Meaning, this is almost exactly how they would be 
implemented in any other language.

Still, I included some Java-specific features while implementing most of the data structures. This includes custom
interfaces to allow for generic implementation of a given data structure. E.g., a generic `Stack` interface can be 
implemented in two distinct ways: `ArrayStack` and `LinkedStack` (read on their differences from the Javadocs). 

This is how most of the data structures are implemented in this project, allowing me to provide a hierarchical interface 
inheritance and ability to choose a concrete implementation for a given generic data structure. This is also similar to
know Java's own Collections API works.

## Data Structure Hierarchy

Here is a quick example of how a part of the `collections` hierarchy works:

```
Collection (I)
|   LinearCollection (I)
|   |   Array (C)
|   |   List (I)
|   |   |   ArrayList (C)
|   |   |   LinkedList (C)
|   |   |   DoublyLinkedList (C)
etc.
```
- 
- `(I)` - interface
- `(C)` - class
