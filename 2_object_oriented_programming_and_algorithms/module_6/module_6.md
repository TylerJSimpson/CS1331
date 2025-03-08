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

Where n is the size of the list, selection sort makes n-1 passes.

### Selection Sort Method

```java
public class TestAlgorithms {

    public static void selectionSort(int[] list) {
        int minIndex; // Keep track of minimum index
        int nextSmallest; // Temporary variable used to perform swaps

        for (int unSortedStart = 0; unSortedStart < list.length-1; unSortedStart++) {
            minIndex = unSortedStart;
            for (int currentIndex = unSortedStart+1; currentIndex < list.length; currentIndex++) { // Start counting after minIndex
                if (list[currentIndex] < list[minIndex]) {
                    minIndex = currentIndex;
                }
            }

            nextSmallest = list[minIndex]; // 
            list[minIndex] = list[unSortedStart]; // 
            list[unSortedStart] = nextSmallest; // 
        }

    }

    public static void main(String[] args) {
    }
}
```

### Adapting the Selection Sort Method for Other Types

Our `selectionSort()` was only able to accept int arrays. A few changes can be done to allow for the algorithm to accept doubles and objects for example.

```java
    public static void selectionSort(double[] list) {
        int minIndex; // Keep track of minimum index
        double nextSmallest; // Temporary variable used to perform swaps

        for (int unSortedStart = 0; unSortedStart < list.length-1; unSortedStart++) {
            minIndex = unSortedStart;
            for (int currentIndex = unSortedStart+1; currentIndex < list.length; currentIndex++) { // Start counting after minIndex
                if (list[currentIndex] < list[minIndex]) {
                    minIndex = currentIndex;
                }
            }

            nextSmallest = list[minIndex]; // 
            list[minIndex] = list[unSortedStart]; // 
            list[unSortedStart] = nextSmallest; // 
        }

    }
```

```java
    public static void selectionSort(Comparable[] list) {
        int minIndex; // Keep track of minimum index
        Comparable nextSmallest; // Temporary variable used to perform swaps

        for (int unSortedStart = 0; unSortedStart < list.length-1; unSortedStart++) {
            minIndex = unSortedStart;
            for (int currentIndex = unSortedStart+1; currentIndex < list.length; currentIndex++) { // Start counting after minIndex
                if (list[currentIndex].compareTo(list[minIndex]) < 0) {
                    minIndex = currentIndex;
                }
            }

            nextSmallest = list[minIndex]; // 
            list[minIndex] = list[unSortedStart]; // 
            list[unSortedStart] = nextSmallest; // 
        }

    }
```

Notice in the Object comparison we had to use the `compareTo()` method instead of the numerical operators.

### The Merge Sort Algorithm

* repetitively breaks list into halves
* repetitively merges halves into order

![m6_merge_layers](/images/m6_merge_layers.png)

![m6_merge_table](/images/m6_merge_table.png)

The 1st item of each sublist has the lowest number in that sublist since they are both sorted. The algorithm checks both and writes the lower to the new list. It then moves to the next number in the list that was written from. So since 2 < 3 it moves on to 6. Since 5 < 6 it then switches to the right list after writing 5.

### Merge Sort Implementation

Merge sort will be focused on more in future classes.

```java
public static void mergeSort(int[] list, int start, int end) {
    if (start == end) {
        return;
    } else if (start == end-1) {
        if (list[start] <= list[end]) {
            return;
        } else { // swap
            int temp = list[start];
            list[start] = list[end];
            list[end] = temp;
        }
    }
    
    int mid = (end-start)/2;
    mergeSort(list, start, start + mid);
    mergeSort(list, start + mid + 1, end);
    merge(list, start, start + mid, end);
}
```

```java
private static void merge(int[] list, int leftHalfStart, int rightHalfStart, int end) {
    int leftHalfSize = rightHalfStart - leftHalfStart + 1;
    int rightHalfSize = end - rightHalfStart;

    int[] leftHalf = new int[leftHalfSize];
    int[] rightHalf = new int[rightHalfSize];

    for (int i=0; i<leftHalfSize; ++i)
        leftHalf[i] = list[leftHalfStart + i];
    for (int j=0; j<rightHalfSize; ++j)
        rightHalf[j] = list[rightHalfStart + 1+ j];

    int i = 0;
    int j = 0;

    int k = leftHalfStart;
    while (i < leftHalfSize && j < rightHalfSize) {
        if (leftHalf[i] <= rightHalf[j]) {
            list[k] = leftHalf[i];
            i++;
        } else {
            list[k] = rightHalf[j];
            j++;
        }
        k++;
    }

    while (i < leftHalfSize) {
        list[k] = leftHalf[i];
        i++;
        k++;
    }

    while (j < rightHalfSize) {
        list[k] = rightHalf[j];
        j++;
        k++;
    }
}
```

### Comparing Algorithms using Complexity Analysis

Algorithms are typically represented by how fast they are and how much space they use. We will mostly focus on the speed portion. 

To evaluate the speed you can use a stopwatch-based evaluation:
```java
long start = System.nanoTime();
selectionSort(input); //you can change this to any code block to collect its runtime
long end = System.nanoTime();
System.out.println("Elapsed time in ns:" + (end - start));
```

Using this stopwatch-based evaluation you can fill out a table to compare two algorithms.

|Input Size|Selection Sort (seconds)|Merge Sort (seconds)|
|----------|------------------------|--------------------|
|10|?|?|
|100|?|?|
|1,000|?|?|
|10,000|?|?|
|100,000|?|?|
|1,000,000|?|?|

While the table is important, more important are the extrapolations we can draw from it.

For example, does the amount of time that an algorithm takes stay constant regardless of how big the input is? If the amount of time grows as input size increases, then is the growth directly proportional?

These sort of questions are **complexity analysis**.

There are many other questions that may come up though. For example what computer was used, what operating system, what java version, background processes, etc.

To avoid device-specific factors, you can represent an algorithm's runtime using the number of operations it takes to complete a task as opposed to elapsed time.

### Constant Time

There is no constant-time sorting algorithm. An example of one would be an algorithm that checks the 1st index of an array and returns null if it is 0 so no matter the size of the array if the 1st index is 0 then it returns null.

### Linear Time and The Linear Search Algorithm

With linear time, doubling the input size doubles the runtime i.e. direct proportionality where if input size increases 100x then run time increases 100x. When X and Y are proportional this creates a straight line on the run time vs input time graph.

An example of this is a worst case `Sequential Search`.


I.e. searching for 72 in this array {12,531,53,3412,72}.

In this case n = 5. If you double the length of the array but still have 72 at the end and search for it then 2 * n = 2 * 5. 

Note we are looking at the worst case. This is actually common with how algorithms are analyzed. 

Example `Sequential (linear) Search` implementation:

```java
public static int linearSearch(Comparable target, Comparable[] list) {
    int index = 0;
    while (index < list.length) {
        if (list[index].compareTo(target) == 0)
            return index;
        else
            index++;
    }
    return -1;
}
```

### Some Empirical Results (Merge Sort vs. Selection Sort)

When comparing `selection sort` vs `merge sort` at an input size of 10,000 `selection sort` may take about 0.0075 seconds while `merge sort` may take about 0.00095 seconds, only 1 order of magnitude greater. But if we scale this up to an input of 1,000,000 it may take hours for `selection sort` while taking only 1 second for `merge sort`. 

This is due to the quadratic nature of the algorithms runtime growth rate. Let's look at the most basic quadratic function:

f(n) = n^2

If we set n = 2, then f(2) = 4. If you increase by a magnitude of 10x then f(2*10) = 400. 

![linear vs quadratic](/images/m6_linear_vs_quadratic.png)

Quadratic growth is fine for small input sizes but it quickly gets worse.

### Worst Case Hypothesis for Selection Sort

For `linear sort` the worst case was having the target at the end of the array. But what about for `selection sort`?

### Quadratic Time (or Why is Selection Sort Slow?)

Regardless of the order being reversed, presorted, or anything else, `selection sort` will actually always exhibit quadratic growth.

![selection sort reverse order input](/images/m6_selection_sort_reverse_order_input.png)

![selection sort presorted input](/images/m6_selection_sort_presorted_input.png)

In this example whether pre-sorted or reverse ordered the total number of comparisons are the same at 28.

C(n) = 1/2 (n^2 - n)

### Deducing Growth Rates From Code

```java
public static int linearSearch(Comparable target, Comparable[] list) {
    int index = 0;
    while (index < list.length) {
        if (list[index].compareTo(target) == 0)
            return index;
        else
            index++;
    }
    return -1;
}
```

Note: `list.length`

If you see a single loop whose limit depends on the size of the input we can expect linear runtime growth.

```java
public static void selectionSort (Comparable[] list) {
    int minIndex;
    Comparable nextSmallest;

    for (int unSortedStart = 0; unSortedStart < list.length-1; unSortedStart++) {
        minIndex = unSortedStart;
        for (int currentIndex = unSortedStart+1; currentIndex < list.length; currentIndex++) {
            if (list[currentIndex].compareTo(list[minIndex]) < 0) {
                minIndex = currentIndex;
            }
        }

        nextSmallest = list[minIndex];
        list[minIndex] = list[unSortedStart];
        list[unSortedStart] = nextSmallest;
    }
}
```

Note:

`list.length-1`

`list.length`

If you see code nested within two for loops and both have limits based on n we can expect worst case quadratic growth.

### Revisiting the Worst Case Hypothesis for Selection Sort

```java
    public static void selectionSort (Comparable[] list) {
        int minIndex;
        Comparable nextSmallest;

        for (int unSortedStart = 0; unSortedStart < list.length-1; unSortedStart++) {
            minIndex = unSortedStart;
            for (int currentIndex = unSortedStart+1; currentIndex < list.length; currentIndex++) {
                if (list[currentIndex].compareTo(list[minIndex]) < 0) {
                    minIndex = currentIndex;
                }
            }
            nextSmallest = list[minIndex];
            list[minIndex] = list[unSortedStart];
            list[unSortedStart] = nextSmallest;
        }
    }
```

The last 3 lines of code are executing swaps on every iteration of the loop which needs to be factored into the time complexity. But, if the list is pre-sorted then this won't be necessary.

```java
    public static void selectionSort (Comparable[] list) {
        int minIndex;
        Comparable nextSmallest;

        for (int unSortedStart = 0; unSortedStart < list.length-1; unSortedStart++) {
            minIndex = unSortedStart;
            for (int currentIndex = unSortedStart+1; currentIndex < list.length; currentIndex++) {
                if (list[currentIndex].compareTo(list[minIndex]) < 0) {
                    minIndex = currentIndex;
                }
            }

            if (minIndex != unSortedStart) {
                nextSmallest = list[minIndex];
                list[minIndex] = list[unSortedStart];
                list[unSortedStart] = nextSmallest;
            }
        }
    }
```

### The Insertion Sort Algorithm

Insertion sort starts by splitting the array into 2 lists. The left list is sorted and the right is unsorted. It picks just the 1st element to start the sorted list.

{9,6,3,2,1}

sorted  
{9} -> {6,9}

unsorted  
{6,3,2,1} -> {3,2,1}

The comparison happens for the last object in the array so next 3 would be compared with 9 then 6.

![](/images/m6_insertion_sort_comparisons.PNG)

Notice the triangle pattern in the Comparisons column. 

C(n) = 1/2(n^2 -n)

Insertion sort's worst case scenario is a reverse ordered list while it's best case scenario is a pre-sorted list.

There is no shifting and only 1 comparison per iteration in a best case scenario.

C(n) = n-1


### Insertion Sort Implementation

```java
public static void insertionSort(int[] list) {
    for (int unsortedStart = 1; unsortedStart < list.length; unsortedStart++) {
        int nextInsert = list[unsortedStart];
        int currentIndex = unsortedStart -1;

        while (currentIndex >= 0 && list[currentIndex] > nextInsert) {
            list[currentIndex+1] = list[currentIndex];
            currentIndex--;
        }

        list[currentIndex+1] = nextInsert;
    }
}
```

### Linearithmic Time

Let's review `linearithmic` time and why `merge sort` is so fast. 

linearithmic function:  
n*log(n)

![](/images/m6_linearithmic_vs_quadratic_time.png)

Revising the merge layers you can see 3 comparisons or log2(8) = 3.

![](/images/m6_merge_layers.png)

### Growth Rates and Big-O Notation

We have reviewed 4 types of runtime growth rates:

- **constant** - with getFront()
- **linear** - with linear search (worst case) and insertion sort (best case)
- **linearithmic** - with merge sort
- **quadratic** - with selection sort and insertion sort (worst case)

These are the 7 common growth rates:

|Growth Rate|Big-O Notation|
|-----------|--------------|
|constant|O(1)|
|logarithmic|O(log(n))|
|linear|O(n)|
|linearithmic|O(nlog(n))|
|quadratic|O(n^2)|
|cubic|O(n^3)|
|exponential|O(a^n)|



### The Binary Search Algorithm

### Binary Search Implementation

### Logarithmic Complexity

