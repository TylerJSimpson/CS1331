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
    1. [Defining and Calling Methods](#defining-and-calling-methods)
    2. [External Method Calls](#external-method-calls)
    3. [Method Overloading](#method-overloading)

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

We can also create an array of an array or a **2-D Array**.

`elementType [][] identifier;`

`double[][] array 2d;`

Where `[row][column]`

`double[][] array2d = {{80,70,75},{69,72,74}};`

You can take advantage of whitespace to make it cleaner:
```
double[][] array2d = {{80,70,75},
                      {69,72,74}};
```

Like other arrays you can also use the new operator:
`double[][] array2d = new double[2][3]`

### Assigning Elements

```
array2d[0][0] = 80;
array2d[0][1] = 70;
array2d[0][2] = 75;
array2d[1][0] = 69;
array2d[1][1] = 72;
array2d[1][2] = 74;
```

Note that the individual array elements can be assigned in any arbitrary order.

### 2-D Array Processing

Processing a 2-D array can follow two approaches. **row-major** traverses the array row by row and **column-major** traverses the array by column.

You will need a loop for each dimension of data.

row-major:
```java
public class Park2Drowm {
    public static void main(String[] args) {
        double[][] array2d = {{80, 70, 75},
                              {69, 72, 74}};
        final double MIN_TEMP = 75;
        final double MAX_TEMP = 90;

        for (int row = 0; row < array2d.length; row++) {
            for (int column = 0; column < array2d[row].length; col++) {
                if ((array2d[row][col] >= MIN_TEMP) && 
                    (array2d[row][col] <= MAX_TEMP)) {
                        System.out.println("Go to the park.");
                    }
            }
        }

    }
}

```

col-major:
```java
public class Park2Dcolm {
    public static void main(String[] args) {
        double[][] array2d = {{80, 70, 75},
                              {69, 72, 74}};
        final double MIN_TEMP = 75;
        final double MAX_TEMP = 90;

        for (int col = 0; col < array2d[0].length; col++) {
            for (int row = 0; row < array2d.length; row++) {
                if ((array2d[row][col] >= MIN_TEMP) && (array2d[row][col] <= MAX_TEMP)) {
                    System.out.println("Go to the park.");
                }
            }
        }
    }
}
```

### Ragged Arrays

Previously our 2d arrays had rows of equal length. Java also allows **ragged arrays** which have varying row lengths.

`double[][] array2d = {{1,2,3},{11,12,13}};`

```
double[][] array2d = new double[2][];
// Fill first row with new 3-element array.
array2d[0] = new double[3];
array2d[0][0] = 80;
array2d[0][1] = 70;
array2d[0][2] = 75;
// Fill second row with a new 4-element array.
array2d[1] = new double[4];
array2d[1][0] = 69;
array2d[1][1] = 72;
array2d[1][2] = 74;
array2d[1][3] = 90;
```

### More Dimensions

While it is possible to create an array of array of arrays forming a 3D array we will focus on 2D arrays in this course.

## Methods

Let us explore modularity and reusability by defining our own non-main method.

Methods allow a programmer to group related statements together and give them a name. One of the main reasons to do that is to modularize programs and break them into small and manageable pieces that represent a specific and meaningful task.

### Defining and Calling Methods

To create a method you need 3 things in order:

return type, method name, formal parameters

`String searchStringArray(String target, String[] array)`

There does not have to be a return type or parameters, in that case:

`void searchStringArray()`

We are still missing 2 other things:
* public - visibility modifier - allows method to be called in class outside this one
* static - complex, we will revisit later

`public static String searchStringArray(String target, String[] array)`

```java
public class ArraySearchMethod {
    public static void main(String[] args) {
        
        String[] concepts = {"abstraction", "polymorphism", "inheritance", "enapsulation"};
        
        String result = searchStringArray("polymorphism", concepts);
        System.out.println(result);

        result = searchStringArray("inheritance", concepts);
        System.out.println(result);
        


    }

    public static String searchStringArray(String target, String[] array) {
        String result = "not found";
        for (String element : array) {
            if (element.equals(target)) {
                result = "found";
                break;
            }
        }
    return result;
    }
}
```

*Once Java reaches a **return** statement during run-time the statement marks the ne od fthe containing method's execution*

### External Method Calls

Note that internally we called the methods like this:

`searchStringArray("polymorphism", concepts)`

Well since we made our method public we can call it externally. To do so simply proceed the method name with the class name and a period.

`ArraySearchMethod.searchStringArray("polymorphism", concepts)`

### Method Overloading

While we created a great reusable method for parsing String arrays for a target String what if we wanted to parse arrays of other types? We could create a method for each type but that would be cumbersome and sort of defeat the purpose.

This is where **method overloading** comes into play.

```java
public class ArraySearch2 {
    public static boolean searchArray(String target,
                                  String[] array) {
        boolean result = false;
        for (String element : array) {
            if ((element != null) && (element.equals(target))) {
                result = true;
                break;
            }
        }
        return result;
    }
        
    public static boolean searchArray(int target,
                                      int[] array) {
        boolean result = false;
        for (int element : array) {
            if (element == target) {
                result = true;
                break;
            }
        }
        return result;
    }
}
```

Now this searchArray is overloaded with two versions. The first version accepts a String and String array whiel the second accepts an int and int array. 

To create an overloaded method in Java you simply need two or more versions that differ in **types, order, and/or number of formal parameters**. The name will be the same, but the signature will be different.

In summary: method names MUST be the same and the parameters MUST be different.

Here is calling the overloaded method:
```java
public static boolean searchArray(String target, String[] array)
public static boolean searchArray(int target, int[] array)
```

Java understands what to call when compiling a call to an overloaded method. It exames the parameter list and searches for a method with a matching signature. This means at runtime Java already knows what it is going to do in this case.

What if we call an overloaded method with a signature with a type that is not supported?

`ArraySearch2.searchArray(90.0, weekHighs)`

This would cause an error since there is no searchArray with this signature:

`searchArray(double,int[])`

This concept is best understood by looking at `System.out.println` which is the most frequently used overloaded method thus far.