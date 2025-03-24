# Table of Contents
1. [Exceptions and File I/O](#exceptions-and-file-io)
    - [Introduction to Exceptions](#introduction-to-exceptions)
    - [What is a Thrown Exception](#what-is-a-thrown-exception)
    - [The call stack](#the-call-stack)
    - [The Throwable Hierarchy](#the-throwable-hierarchy)
    - [Handling Exceptions (by catching)](#handling-exceptions-by-catching)
    - [Multiple Catch Blocks](#multiple-catch-blocks)
    - [Exception Controlled Loops](#exception-controlled-loops)
    - [Why Exceptions?](#why-exceptions)
    - [Handling Multiple Exceptions](#handling-multiple-exceptions)
    - [Combined Catch Blocks](#combined-catch-blocks)
    - [The finally Block](#the-finally-block)
    - [File I/O (and the FileNotFoundException)](#file-io-and-the-filenotfoundexception)
    - [Declaring Exceptions to be Thrown (or Removing the FileNotFoundException catch)](#declaring-exceptions-to-be-thrown-or-removing-the-filenotfoundexception-catch)
    - [Checked vs Unchecked Exceptions](#checked-vs-unchecked-exceptions)
    - [Defining an Exception and using the throw operator](#defining-an-exception-and-using-the-throw-operator)
    - [More File I/O: Delimited Files](#more-file-io-delimited-files)


## Exceptions and File I/O

### Introduction to Exceptions

Exceptions are objects. They represent errors that occur at runtime an "exceptional event". 

Let's recall an old program:

```java
public class FahrenheitToCelsiusPrintf {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a fahrenheit value: ");
        int fahrenheit = input.nextInt();
        System.out.print("Enter the day of the week: ");
        String day = input.next();
        System.out.print("Enter your preferred Celsius label (Celsius, Centigrade, or C): ");
        String cText = input.next();
        double celsius = (5.0/9) * (fahrenheit - 32);
        System.out.printf("%s Fahrenheit: %d\n", day, fahrenheit);
        System.out.printf("%s %-10s: %,.1f\n", day, cText, celsius);
    }
```

You can see an `InputMismatchException` if you enter something unexpected:

```
Enter a fahrenheit value: thirty
Exception in thread "main" java.util.InputMismatchException
        at java.base/java.util.Scanner.throwFor(Scanner.java:939)
        at java.base/java.util.Scanner.next(Scanner.java:1594)
        at java.base/java.util.Scanner.nextInt(Scanner.java:2258)
        at java.base/java.util.Scanner.nextInt(Scanner.java:2212)
        at FahrenheitToCelsiusPrintf.main(FahrenheitToCelsiusPrintf.java:7)
```

### What is a Thrown Exception

Throwing an exception involves creation of an exception object and the hand off of that object to the JVM to try and find a method that can handle the exception.

At the bottom of the Exception message above you can see the error occurred deeper than where the "thirty" was written. There are layers of method calls between nextInt() and when "thirty" is actually processed. The lines above represent the trace of those calls.

```
        at FahrenheitToCelsiusPrintf.main(FahrenheitToCelsiusPrintf.java:7)
```

Execution started at main, then called Scanner's nextInt method, that call invoked an overloaded version of nextInt in Scanner. Then the next method is called which is where "thirty" is read and identified as an unexpected type and thus the next method call was throwFor. 

When throwFor is called an instance of an InputMismatchException object was created and handed off to the JVM. 

### The call stack

Our bottom up message we looked at above is called a call stack track or just stack trace for short.

```
Exception in thread "main" java.util.InputMismatchException
        at java.base/java.util.Scanner.throwFor(Scanner.java:939)
        at java.base/java.util.Scanner.next(Scanner.java:1594)
        at java.base/java.util.Scanner.nextInt(Scanner.java:2258)
        at java.base/java.util.Scanner.nextInt(Scanner.java:2212)
        at FahrenheitToCelsiusPrintf.main(FahrenheitToCelsiusPrintf.java:7)
```

Java has a process of completing a method and then switching back to its caller to continue all the way down to main. The state of the methods are saved in a build-up and wind-down approach using a stack-based data structure. State information is pushed onto the stack as one method calls another. After a method completes, its state is simply popped off the stack, revealing the state information that's needed to continue its caller. 

I.e.

method 5

method 4

method 3

method 2

method 1 (main)

As the methods complete they are popped off the stack and when main is popped this signals the end of a program.

### The Throwable Hierarchy

There is a hierarchy of exception types.

![](/images/m8_throwable_hierarchy.png)

Note there are many classes not shown this is just a summary.

It may seem odd that `Error` and `Exception` are separate but this is the current state of Java. `Error` tends to represent an error that cannot be recovered from which differentiates it from `Exception`.

### Handling Exceptions (by catching)

### Multiple Catch Blocks

### Exception Controlled Loops

### Why Exceptions?

### Handling Multiple Exceptions

### Combined Catch Blocks

### The finally Block

### File I/O (and the FileNotFoundException)

### Declaring Exceptions to be Thrown (or Removing the FileNotFoundException catch)

### Checked vs Unchecked Exceptions

### Defining an Exception and using the throw operator

### More File I/O: Delimited Files

