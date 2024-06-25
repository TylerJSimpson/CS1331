# Table of Contents
1. [Arrays](#arrys)
    1. [Declaring and Creating Arrays](#declaring-and-creating-arrays)
    2. [Default Values](#default-values)
    3. [Specifying Values](#specifying-values)
    4. [Using Arrays](#using-arrays)
    5. [The for-each Statement](#the-for-each-statement)
    6. [Searching Arrays](#searching-arrays)
    7. [Sparse Arrays and Null Checking](#sparse-arrays-and-null-checking)
    8. [Command-line Arguments and Wrapper Classes](#command-line-arguments-and-wrapper-classes)
    9. [2-D Arrays](#2-d-arrays)
    10. [Assigning Elements](#assigning-elements)
    11. [2-D Array Processing](#2-d-array-processing)
    12. [Ragged Arrays](#ragged-arrays)
    13. [More Dimensions](#more-dimensions)
2. [Methods](#methods)

## Arrays

Arrays allow us to store collections of values together under a single name, rather than having possibly very many individual variables to keep track of.

**An array is an ordered sequence of values, in which each element is of the same type.**


### Declaring and Creating Arrays

Template for declaring an array:

`elementType[] identifier;`

Example:

`double[] weekHighs;`

Template for creating an array:

`new elementType[length]`

Example:

`double[] weekHighs = new double[7];`

**The type of the created array must match the type used in the declaration.**

**Array lengths are fixed**

So above notice double is specified during the declaration and creation. Further, we will not be able to shrink or increase the size from 7 elements.

In an object array, each element acts as a reference variable.

### Default Values

When you create an array, Java initializes each location with a default value based on the type.
* int - 0
* boolean - false
* object - null

**null is a Java keyword and value that is assigned to a reference variable in order to specify that the variable has no address.**

### Specifying Values

You can also specify your own initial values like below:
`double[] weekHighs = {80, 70, 75};`

Note that if you use this approach you cannot separate the declaration and initialization.

You can also specify the values one at a time using the `arrayName[index]`.

```java
double[] weekHighs = new double[7];
weekHighs[0] = 80;
weekHighs[1] = 70;
```

### Using Arrays

```java
public class Averager {
    public static void main(String[] args) {
        double[] weekHighs = {80, 70, 75, 69, 72, 74, 90};
        double highSum = 0;

        for (int index = 0; index < weekHighs.length; index++) {
            highSum = highSum + weekHighs[index];
        }
        double averageHighs = highsSum / weekHighs.length;
        System.out.println("Average is: " + averageHighs);

    }
}
```

*Note that you can compile while looping out of bounds of an array but you will receive a runtime error*.

### The for-each Statement

Java provides a statement called a **for-each** statement that helps us avoid indices completely when traversing an array.

```
for (arrayType element : array) {
    bodyStatement1;
    bodyStatement2;
    ...
}
```

```java
public class Averager2 {
    public static void main(String[] args) {
        double[] weekHighs = {80, 70, 75, 69, 72, 74, 90}; //initialization
        double highsSum = 0;
        for (double dayHigh : weekHighs) {
            highsSum = highsSum + dayHigh;
        }
        double averageHighs = highsSum / weekHighs.length;
        System.out.println(averageHighs);
    }
}
```

### Searching Arrays

```java
public class ArraySearch {
    public static void main(String[] args) {
        String[] concepts = {"abstraction","polymorphism","inheritance","encapsulation"};
        String result = "not found";

        for (String concept : concepts) {
            if (concept.equals("polymorphism")) {
                result = "Found.";
                break;
            }
        }

        System.out.println(result);
    }
}
```

### Sparse Arrays and Null Checking

Our previous example traverses through the String array with the assumption that it is non-sparse; i.e., none of the elements are assigned a value of null. But remember that null is the default value of each element in an object array.

```java
public class SparseArraySearch {
    public static void main(String args[]) {
        String[] concepts = new String[5];
        concepts[0] = "abstraction";
        concepts[2] = "polymorphism";
        concepts[3] = "inheritance";
        concepts[4] = "encapsulation";
        String result = "not found";
        for (String concept : concepts ) {
            if (concept.equals("polymorphism") ) {
                result = "found";
                break;
            }
        }
        System.out.println(result);
    }
}
```

While this program will compile you will receive a runtime error.

Here you can check if there is a null to avoid these errors:
```java
public class SparseArraySearch {
    public static void main(String args[]) {
        String[] concepts = new String[5];
        concepts[0] = "abstraction";
        concepts[2] = "polymorphism";
        concepts[3] = "inheritance";
        concepts[4] = "encapsulation";
        String result = "not found";
        for (String concept : concepts ) {
            if ((concept != null) && (concept.equals("polymorphism"))) {
                result = "found";
                break;
            }
        }
        System.out.println(result);
    }
}
```

### Command-line Arguments and Wrapper Classes

Recall how the main method is defined in java:
```java
public static void main(String[] args) {

}
```
Notice that `String[] args` s referencing a string object array.

This array of string objects works to grab any arguments passed to the program from the command line at runtime.

Here is a simple program that will illustrate this concept.

```java
public class Averager3 {
    public static void main(String[] args) {
        double sum = 0;
        for (String num : args) {
            sum += Double.parseDouble(num); //Convert str to dbl
        }
        double average = (args.length > 0) ? (sum/args.length) : 0;
        System.out.println("Average is: " + average);
    }
}
```

Running:
```bash
javac Averager3.java
java Averager3
```

Results in `Average is: 0.0` because we did not pass any `args`.

What about if we ran this:
```bash
java Averager3 2 3 4 5 6 7 182 91
```

Result: `Average is: 37.5`.

Note that we had to convert to double since the arguments were passed as Strings.

In this case we used the class `Double`. Think of it as a class wrapped around the primitive double type. This is known as a **wrapper class** and there is one for each primitive type.

**Wrapper classes do not need imported since they are part of the java lang package.**

Also notice that we did not get a divide-by-zero error. Note that we used a conditional to avoid this issue.

### 2-D Arrays

### Assigning Elements

### 2-D Array Processing

### Ragged Arrays

### More Dimensions

## Methods

