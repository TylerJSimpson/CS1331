# Table of Contents
1. [Interfaces and Algorithms](#interfaces-and-algorithms)
    1. [Introduction to Interfaces](#introduction-to-interfaces)
    2. [Interface Basics](#interface-basics)
    3. [Writing a Sorting Method](#writing-a-sorting-method)
    4. [Comparing Objects](#comparing-objects)
    5. [java.lang.Comparable](#javalangcomparable)
    6. [Arrays.sort()](#arrayssort)
    7. [Writing a compareTo() method](#writing-a-compareto-method)
    8. [Generic Types and Comparable](#generic-types-and-comparable)
    9. [An Arrays.sort() Example](#an-arrayssort-example)
    10. [A Deeper Look at Sorting Algorithms](#a-deeper-look-at-sorting-algorithms)
    11. [Selection Sort in a Nutshell](#selection-sort-in-a-nutshell)
    12. [Selection Sort Walkthrough](#selection-sort-walkthrough)
    13. [Selection Sort Method](#selection-sort-method)
    14. [Adapting the Selection Sort Method for Other Types](#adapting-the-selection-sort-method-for-other-types)
    15. [The Merge Sort Algorithm](#the-merge-sort-algorithm)
    16. [Merge Sort Implementation](#merge-sort-implementation)
    17. [Comparing Algorithms using Complexity Analysis](#comparing-algorithms-using-complexity-analysis)
    18. [Constant Time](#constant-time)
    19. [Linear Time and The Linear Search Algorithm](#linear-time-and-the-linear-search-algorithm)
    20. [Some Empirical Results (Merge Sort vs. Selection Sort)](#some-empirical-results-merge-sort-vs-selection-sort)
    21. [Worst Case Hypothesis for Selection Sort](#worst-case-hypothesis-for-selection-sort)
    22. [Quadratic Time (or Why is Selection Sort Slow?)](#quadratic-time-or-why-is-selection-sort-slow)
    23. [Deducing Growth Rates From Code](#deducing-growth-rates-from-code)
    24. [Revisiting the Worst Case Hypothesis for Selection Sort](#revisiting-the-worst-case-hypothesis-for-selection-sort)
    25. [The Insertion Sort Algorithm](#the-insertion-sort-algorithm)
    26. [Insertion Sort Implementation](#insertion-sort-implementation)
    27. [Linearithmic Time](#linearithmic-time)
    28. [Growth Rates and Big-O Notation](#growth-rates-and-big-o-notation)
    29. [The Binary Search Algorithm](#the-binary-search-algorithm)
    30. [Binary Search Implementation](#binary-search-implementation)
    31. [Logarithmic Complexity](#logarithmic-complexity)


## Interfaces and Algorithms

The interface construct offers us a way of creating and using classes that share similar kinds of behaviors but do not necessarily have meaningful inheritance relationships.

### Introduction to Interfaces

To review the interfaces topic We created GroomEverything and Dog classes as well as updated the Wolf class. 

GroomEverything:
```java
public class GroomEverything {
    public static void main(String[] args) {
        Canine[] groomer = {
            new Wolf(17.01, 3),
            new Poodle("richie", 9, "Lux Brand", "Rich Brand"),
            new Wolf(16, 5),
            new Poodle("pixy", 4, "Top Shelf", "Only the Best"),
        };

        for (Canine c : groomer) { //groom everything
            c.groom();
        }
    }
}
```

We have declared a `Canine` array which means that any object that are canines can be inserted. So any subclasses can be inserted.

Poodle: 
```java
public class Poodle extends Dog {
    private String favoriteShampoo;
    private String favoriteConditioner;

    public Poodle(String name, double size, String favoriteShampoo, String favoriteConditioner) {
        super(name, size);
        this.favoriteShampoo = favoriteShampoo;
        this.favoriteConditioner = favoriteConditioner;
    }

    public void groom() {
        System.out.println(favoriteShampoo + " rinse " + favoriteConditioner + " wait 10 mins " + "dry and massage");
    }
}
```

We also added `Poodle` class which introduced a favorite shampoo and conditioner.

Wolf:
```java
    public void groom() {
        System.out.println("lick");
    }
```

To illustrate we added `groom()` to the `Wolf` class as well.

The loop in `GroomEverything` iterates through each `Canine` and applies their `groom()` method. 

Check out [Car.java](/2_object_oriented_programming_and_algorithms/module_6/programs/Car.java). Notice we added a `groom()` method.

What if we tried to change our `GroomEverything()` class to iterate over a general object array instead of a `Canine` array to allow for "grooming" of the `Car` class as well?

```java
        for (Object c : groomer) { //groom everything
            c.groom();
        }
```

This will fail at compilation. 

So how do we write generic code to interact with classes that have no super/sub-class relationship? This is where **interfaces** come in.

### Interface Basics

Our goal is to create a single array that could hold all kinds of groomable objects from `Canines` to `Cars`. 

The solution is to create an **interface**. In it we will declare a set of abstract methods that we expect classes to define in their own way. Once the **interface** is compiled any concrete or abstract class can then declare itself as an implementor of the interface.

For concrete classes the class must define all of the interface's methods or it will not compile. For abstract classes it does not require this and can delegate the task to a concrete subclass.

An object can be an instance of an infinite amount of types.

```java
public interface Groomable {
    public void groom();
}
```

Notice the word `interface` as opposed to `class`.

Then you will see the list of abstract methods. Note that abstract methods in an interface do not need the abstract modifier like they do in classes since it is implied. Likewise, the methods are all public but it is still good practice to write public as a reminder.

Now we need to bind the interface to the classes. This involves 2 steps.

1st update the class header:
`public class Car` to `public class Car implements Groomable`

```java
public class Car implements Groomable
```

```java
public class Wolf extends Canine implements Groomable
```

```java
public class Poodle extends Dog implements Groomable
```

2nd make sure there is a `groom()` method in each class. We already did this previously.

We will also change the `GroomEverything` object since that is what calls the others.

```java
public class GroomEverything {
    public static void main(String[] args) {
        Groomable[] groomer = {
            new Wolf(17.01, 3),
            new Poodle("richie", 9, "Lux Brand", "Rich Brand"),
            new Wolf(16, 5),
            new Poodle("pixy", 4, "Top Shelf", "Only the Best"),
            new Car("Yuhina", "Spark", 2037)
        };

        for (Groomable g : groomer) { //groom everything
            g.groom();
        }
    }
}
```


Response:
```
lick
Lux Brand rinse Rich Brand wait 10 mins dry and massage
lick
Top Shelf rinse Only the Best wait 10 mins dry and massage
soap, rinse, wax, and a little tree air freshener
```

Remember that `Poodle` is a subclass of `Dog` which is a subclass of `Canine`. So if we added a groomable clause to the `Canine` class all the subclasses would automatically be groomable types via inheritance.

```java
public abstract class Canine implements Groomable 
```

Now we can remove it from the header of the `Canine` subclasses. Also, since `Canine` is abstract the `groom()` method does not need to be present since it is defined by the concrete subclasses.

### Writing a Sorting Method

Suppose we need to sort a group of objects like an array of `wolves` in ascending order. We already have `rank` defined so we can use that. The lower the `rank` the higher the order so it should be `rank` 1, 2, 3, 4.

There are many established sorting methods:
* Merge Sort
* Insertion Sort
* Bubble Sort
* Quicksort
* Selection Sort

The sort method would accept a reference to a `Wolf` array and stored as a formal parameter. For example:
```java
public void quickSortWolfArray(wolf[] pack) {

}
```

Inside you will see something like:

`pack[someIndex].getRank() > pack[anotherIndex].getRank()`

What if we want to sort an array of objects of some other type and types that dont exist yet.

These objects likely won't have a rank so we need to generalize the algorithm.

For the header:
```java
public void quickSort(Object[] arr) {

}
```

Inside we will use `compareTo()` which delegates to the objects own `compareTo()` method.

`((SomeType)arr[someIndex]).compareTo(arr[anotherIndex])`

### Comparing Objects

`compareTo() rules`:

|Comparison Result|ReturnValue|
|-----------------|-----------|
|Calling object is less|negative int|
|Calling object is greater|positive int|
|both are equal|0|

For example rank2.compareTo(rank1) = positive int because calling object is larger

The problem with using object as the array type is that the class does not have a `compareTo()` method built in. While we can pass any object array in, nothing specifies it must have a `compareTo()` method, and if it doesn't, it will generate an error.

Recall that **an interface provides a way of enforcing that a calss declares and/or defines one or more methods** and if not the class will not compile.

The interface is like a contract. If a class implements an interface it's bound to a contract represented by a set of abstract methods.

### java.long.Comparable

Java has a built in interface with a `compareTo`() method caled `Comparable` in the java.lang package.

`java.lang.Comparable`

### Arrays.sort()

Java also has it's own static sorting methods for arrays of objects.

`java.util.Arrays`

This method uses Timsort as opposed to Quicksort like we looked at before.

The method in the array class is called `sort()`. It performs a `comparable` cast to the reference calling `compareTo()` to temporarily treat the reference as a comparable object in order to invoke compareTo.

### Writing a compareTo() method

Let's take a look at writing a `compareTo()` method using our `Wolf` class.

1st we must update the header:
```java
public class Wolf extends Canine implements Groomable, Comparable {
```

Note `extends` must always come before `implements`. To `implement` multiple interfaces just separate them with commas.

2nd we must create a `compareTo()` method:
```java
public int compareTo(Object anotherWolf) {
    return -(rank - ((Wolf)anotherWolf).rank);
}
```

In this example if the rank of calling `Wolf` is 1 and the rank of the reference `Wolf` is 4 then 1 - 4 = -3. But if you recall we want a return value of a positive int if the calling object is greater so we add a negative sign. Now a 3 is returned.

Notice the `(Wolf)` cast is performed on the formal parameter variable. This is because `anotherWolf` is an object class reference and `rank` is a `Wolf` property. Without the cast we will get a compiler error.

We can test this by updating the main method:
```java
    public static void main(String[] args) {
        Wolf alpha = new Wolf(17.1, 1);
        Wolf puppy = new Wolf(3, 10);
        System.out.println(alpha.compareTo(puppy));
    }
```

Output:
```
9
```

### Generic Types and Comparable

Let us improve the `Wolf` `compareTo()` method we created above.

In the main method we could write the below and the program would compile:
```java
System.out.println(alpha.compareTo("Hello, World!"));
```

Of course there would be an error at runtime trying to cast a `String` to a `Wolf`.

Waiting until runtime to find errors is problematic, especially in large programs. We need a solution that can check the type compatability of the actual parameters that `compareTo()` accepts and the objects to which that input is being compared.

**generic type** was introduced by Java to solve this problem. It is an interface or class that is capable of accepting input that identifies the kidns of data it can work with.

In the Java docs you will see `Interface Comparable<T>` where the `T` represents a type as a form of parameter that allows us to give the interface information about how it will be used. We can pass in a type to `Comparable` which will then limit the kinds of objects used in `compareTo()`. 

`int compareTo(T o)`

To do this let's update the `Wolf` class header to include the parameterized version of `Comparable`:
```java
public class Wolf extends Canine implements Groomable, Comparable<Wolf> {
```

Now we can update the `compareTo()` method to accept `Wolf` as opposed to generic object:
```java
    public int compareTo(Wolf anotherWolf) {
        return -(rank - ((Wolf)anotherWolf).rank);
    }
```

### An Arrays.sort() Example

Let's use our `Wolf` class to implement `Arrays.sort()`.


```java
import java.util.Arrays;
public class Wolf extends Canine implements Groomable, Comparable<Wolf> {
    
    protected int rank;

    public Wolf(double size, int rank) {
        super(size);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void bark() { //3 times the default canine bark
        for (int i = 1; i <= 3; i++)
            super.bark();
    }

    public void bark(int barkMultiple) {
        for (int i = 1; i <= barkMultiple; i++) {
            super.bark();
        }
    }

    public void groom() {
        System.out.println("lick");
    }

    public int compareTo(Wolf anotherWolf) {
        return -(rank - ((Wolf)anotherWolf).rank);
    }

    public String toString() {
        return ("Rank " + rank + ", Size "+ size);
    }

    public static void main(String[] args) {
        Wolf[] pack = {
            new Wolf(17.1, 2),
            new Wolf(3, 10),
            new Wolf(9.2, 7),
            new Wolf(9.1, 8),
            new Wolf(5, 9),
            new Wolf(10, 6),
            new Wolf(16, 5)
        };

        System.out.println("Unsorted Pack: " + Arrays.toString(pack));
        Arrays.sort(pack);
        System.out.println("===============================");
        System.out.println("Sorted Pack: " + Arrays.toString(pack));
    }

}
```

1. We added `import java.util.Arrays`
2. We created a `toString()` method overwrite
3. We updated the main method to illustrate the sorting

### A Deeper Look at Sorting Algorithms

Next we are going to take a look at 3 separate sorting algorithms:
* Selection Sort
* Merge Sort
* Insertion Sort

We mentioned **Timsort** earlier. This is a hybrid of **insertion** and **merge** sort.


### Selection Sort in a Nutshell

* Scans a list for smallest item
* Places item in order
* Scans remaining (unsorted) items for next smallest
* Places that item in order

### Selection Sort Walkthrough

### Selection Sort Method

### Adapting the Selection Sort Method for Other Types

### The Merge Sort Algorithm

### Merge Sort Implementation

### Comparing Algorithms using Complexity Analysis

### Constant Time

### Linear Time and The Linear Search Algorithm

### Some Empirical Results (Merge Sort vs. Selection Sort)

### Worst Case Hypothesis for Selection Sort

### Quadratic Time (or Why is Selection Sort Slow?)

### Deducing Growth Rates From Code

### Revisiting the Worst Case Hypothesis for Selection Sort

### The Insertion Sort Algorithm

### Insertion Sort Implementation

### Linearithmic Time

### Growth Rates and Big-O Notation

### The Binary Search Algorithm

### Binary Search Implementation

### Logarithmic Complexity

