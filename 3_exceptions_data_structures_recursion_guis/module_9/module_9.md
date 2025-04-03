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

`ArrayList` is a generic class so you can create one with or without constraints on the element type. With no constraints, the elements of the underlying Object array can refer to any Java object.

```java
ArrayList playlist = new ArrayList();
```

Length can be passed. If it is not, the initial length will be 10 though they will be null reference. So in this case length won't actually be 10 but it will have a capacity of 10.

```java
ArrayList playlist = new ArrayList(5);
```

It is best to ensure we have a type parameter that defines what kinds of objects its elements can reference.

Both of these are legal as of Java 7.

```java
ArrayList<elementType> aList = new ArrayList<elementType>();
```

```java
ArrayList<elementType> aList = new ArrayList<elementType>(initialCapacity);
```

Example from before:

```java
ArrayList<String> playlist = new ArrayList<>(5);
```

### ArrayList Methods Demo

https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

```java
import java.util.ArrayList;

public class StringArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> playlist = new ArrayList<>();
        playlist.add("Humpty Dumpty");
        playlist.add("Swing Low Sweet Chariot");
        playlist.add("Itsy Bitsy Spider");
        playlist.add("Twinkle, Twinkle Little Star");
        playlist.add("Wheels on the Bus");
        System.out.println(playlist.toString());

        playlist.remove("Humpty Dumpty");
        System.out.println(playlist.toString());
        System.out.println(playlist.contains("Humpty Dumpty"));
        System.out.println(playlist.contains("Itsy Bitsy Spider"));
        playlist.add("Humpty Dumpty");
        System.out.println(playlist.toString());
    }
}
```

### Autoboxing and For Each Loops Demo

Autoboxing refers to being able to add primitive types to the ArrayList even though the underlying type is Object. Java creates an object version of the primitive value. So an integer Object is added below.

```java
import java.util.ArrayList;

public class IntArrayListDemo {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(90);
        scores.add(95);
        scores.add(110);
        scores.add(99);
        scores.add(105);
        System.out.println(scores.toString());

        int sum = 0;
        for (Integer score : scores) {
            sum += score;
        }
        System.out.println("Total points: " + sum);
    }
    
}
```

### Motivating LinkedLists

While `ArrayLists` improve greatly on using just `Arrays` there are still severe limitations. For example, to grow it beyong it's capaity you would still need to create a larger list and copy the elements over. We need a dynamic list type that will not require us to provide an initial size. Another limitation is we cannot add elements to a location besides the end of a list without again creating a new `Array` and copying elements over.

`LinkedLists` help us with these concerns.

```java
GenericLinkedList favBabySongs = new GenericLinkedList<>();
```

### Basics of Writing Generic Classes

First let's review writing generic classes as this will be important for our `LinkedLists`.

Recall you can use generic types:

```java
public class Bin<T> {
    
    private T content;

    public Bin(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static void main(String[] args) {
        Bin<String> test = new Bin<>("I'm a basic String");
        System.out.println(test);
    }

}
```

You can even extend comparable to make the classes totally general.

```java
public class BinCompare<T extends Comparable<T>> {
    private T content1;
    private T content2;

    public BinCompare(T content1, T content2) {
        this.content1 = content1;
        this.content2 = content2;
    }

    public T greaterValue() {
        return (content1.compareTo(content2) >= 0 ? content1 : content2);
    }

    public static void main(String[] args) {
        BinCompare<String> test = new BinCompare<>("I'm a basic String", "short and stout");
        System.out.println(test.greaterValue());
    }
}
```

### What is a Linked List?

The basis of a linked list is an object called a `Node`. A `Node` is an element of a linked list as opposed to the data being stored directly like an `Array` or `ArrayList`. Nodes are not placed in contiguous memory locations they are essentially just sets of objects floating in the heap. We connect the `Nodes` to create a list. Each `Node` connects to the next hence the name Linked List.

`Node` class

```java
private class Node<E> {
    E data;
    Node<E> next;

    Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}
```

```java
new Node<String>(newData, null);
```

The core operations of a `LinkedList` class is how to get to the actual list. So it will need an instance variable that's a reference to at least 1 `Node`, we can call his `head`. `head` will be `null` if the list is empty.

### addToFront(), traversal logic

Assuming we have an empty list, we must first create a node.

```java
head = new Node<E>(data, head);
```

What if there is already 1 or more nodes?

We can add to the front. This entails using the `addToFront()` method. This method would assign the `next` reference of the new node to the value of the current head of the list. It also needs to ensure the head points to the new node.

```java
Node current = head;
while (current != null) {
    current = current.next;
}
```

```java
    public void addToFront(E newData) {
        head = new Node<E>(newData, head);
    }
```

### addToRear()

Adding to the rear is a bit more complicated and entails `addToRear()`.

First you create a node

```java
Node<E> node = new Node <E> (newData, null);
```

Notice the `next` reference of the new `Node` is initialized to `null`. That's because it is the end of the new list with nothing following it.

To add the node assign it to head:

```java
public void addToRear(E newData) {
    public void addToRear(E newData) {
        Node<E> node = new Node <E> (newData, null);
        Node<E> current = head;
        if (head == null) {
            head = node;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
    }
}
```

### GenericLinkedList and Private Inner Classes

See [GenericLinkedList.java](/3_exceptions_data_structures_recursion_guis/module_9/programs/GenericLinkedList.java) for full code of a generic LinkedList.

### GenericLinkedList and removing nodes

See [GenericLinkedListUpdated.java](/3_exceptions_data_structures_recursion_guis/module_9/programs/GenericLinkedListUpdate.java) for full code from prior with additional features removing from the front and back of the list.

When removing from the front you simply need to move the head back so `head = head.next;`

```java
E removeData = head.data;
head = head.next
return removedData;
```

On an empty list this would fail due to null pointer. Below is more robust:

```java
public E removeFromFront() {
    if (isEmpty()) {
        return null;
    }
    E removedData = head.data;
    head = head.next;
    return removedData;
}
```

What about removing from the end of the list?

We don't want to go to the end node since that is what is being removed. We want to move to the 2nd last node and set it's pointer to null to sever the last node.

```java
Node<E> current = head;
while (current.next.next != null) {
    current = current.next;
}
E removedData = current.next.data;
current.next = null;
return removedData;
```

We also ahve to realize the code will fail if there is only 1 node due to `current.next.next`.

```java
public E removeFromRear() {
    E removedData;
    is (isEmpty()) {
        removedData = null;
    }
    else if (head.next == null) {
        removedData = head.data;
        head = null;
    }
    else {
        Node<E> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        removedData = current.next.data;
        current.next = null;
    }
    return removedData;
}
```

### Linked Lists vs ArrayLists

`ArrayLists` do have their benefits. For example they have faster data reads to the ability to access the values directly without intermediate elements.

For example if you want to simply get an item at a specific index this will have constant time with `ArrayLists` but will have linear time in `LinkedLists` due to needing to traverse the list.

## Recursion

### Introduction to Recursion

### Tracing Recursion with the Call Stack

### Factorial Example

### The Call Stack and Methods with Return Values
