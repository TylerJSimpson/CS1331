# Table of Contents
1. [Lists (and more Generics)](#lists-and-more-generics)
    - [List Interface](#list-interface)
    - [What is an ArrayList](#what-is-an-arraylist)
    - [Creating an ArrayList](#creating-an-arraylist)
    - [ArrayList Methods Demo](#arraylist-methods-demo)
    - [Autoboxing and For Each Loops Demo](#autoboxing-and-for-each-loops-demo)
    - [Motivating LinkedLists](#motivating-linkedlists)
    - [Basics of Writing Generic Classes](#basics-of-writing-generic-classes)
    - [What is a Linked List?](#what-is-a-linked-list)
    - [addToFront(), traversal logic](#addtofront-traversal-logic)
    - [addToRear()](#addtorear)
    - [GenericLinkedList and Private Inner Classes](#genericlinkedlist-and-private-inner-classes)       
    - [GenericLinkedList and removing nodes](#genericlinkedlist-and-removing-nodes)
    - [Linked Lists vs ArrayLists](#linked-lists-vs-arraylists)                                                  
2. [Recursion](#recursion)
    - [Introduction to Recursion](#introduction-to-recursion)
    - [Tracing Recursion with the Call Stack](#tracing-recursion-with-the-call-stack)
    - [Factorial Example](#factorial-example)
    - [The Call Stack and Methods with Return Values](#the-call-stack-and-methods-with-return-values)                   

## Lists (and more Generics)

### List Interface

```java
Interface List<E>
```

E - the type of elements in this list

Has methods such as `add()`, `clear()`, `contains()` for searching, `removing()`, `size()`, `sort()` and many others.

### What is an ArrayList

We have been using `Array` as a list but this has many limitations. For example if you have an `Array playlist` where each element is a string of a song i.e. "Wheels on the Bus", "Its Bitys Spider" what if you want to add a lot more songs to the playlist? You will need to create a new larger array and copy these elements back into the new one. Further, if the list is already sorted by priority what if you want to add a new high priority item to the top? These are all expensive to do.

Java has an `ArrayList` class that gives us a high level way of completing these operations. It is an implementation of the `List interface` with an array as the underlying data structure for storing items.

### Creating an ArrayList

### ArrayList Methods Demo

### Autoboxing and For Each Loops Demo

### Motivating LinkedLists

### Basics of Writing Generic Classes

### What is a Linked List?

### addToFront(), traversal logic

### addToRear()

### GenericLinkedList and Private Inner Classes

### GenericLinkedList and removing nodes

### Linked Lists vs ArrayLists

## Recursion

### Introduction to Recursion

### Tracing Recursion with the Call Stack

### Factorial Example

### The Call Stack and Methods with Return Values
