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



### Comparing Objects

### java.long.Comparable

### Arrays.sort()

### Writing a compareTo() method

### Generic Types and Comparable

### An Arrays.sort() Example

### A Deeper Look at Sorting Algorithms

### Selection Sort in a Nutshell

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

