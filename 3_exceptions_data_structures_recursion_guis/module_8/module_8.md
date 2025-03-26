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

```java
try {
 statement(s);
} catch (ExceptionType identifier) {  
 statement(s);
}
```

When an exception occurs in the try block, a reference to its object is passed into a catch block, only if the declared type matches the object's type.


### Multiple Catch Blocks

```java
try {
 statement(s);
} catch (ExceptionType1 identifier) {  
 statement(s);
} catch (ExceptionType2 identifier) {  
 statement(s);
} catch (ExceptionType3 identifier) {  
 statement(s);
}
```

It is important to include all of the dependent code in the try block.

Bad example that won't compile:

```java
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a fahrenheit value: ");
        try {
            int fahrenheit = input.nextInt();            
        }
        catch(InputMismatchException e) {
            System.out.println("Sorry, that wasn't an int.");
            System.out.println("Please re-run the program again");
        }
        double celsius = (5.0/9) * (fahrenheit - 32);
        System.out.printf("%s Fahrenheit: %d\n", fahrenheit);
        System.out.printf("%s %-10s: %,.1f\n", celsius);
    }
```

Good example:

```java
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a fahrenheit value: ");
        try {
            int fahrenheit = input.nextInt();
            double celsius = (5.0/9) * (fahrenheit - 32);
            System.out.printf("%s Fahrenheit: %d\n", fahrenheit);
            System.out.printf("%s %-10s: %,.1f\n", celsius);
        }
        catch(InputMismatchException e) {
            System.out.println("Sorry, that wasn't an int.");
            System.out.println("Please re-run the program again");
        }
    }
```

### Exception Controlled Loops

```java
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean success = false;
        int fahrenheit = 0;

        while (!success) {
            try {
                System.out.print("Enter a fahrenheit value: ");
                fahrenheit = input.nextInt();
                success = true;

            }
            catch(InputMismatchException e) {
                input.nextLine();
                System.out.println("Sorry, that wasn't an int.");
                System.out.println("Please try again");
            }
        }
        
        double celsius = (5.0/9) * (fahrenheit - 32);
        System.out.printf("Fahrenheit: %d\n", fahrenheit);
        System.out.printf("Celsius: %,.1f\n", celsius);

    }
```

Here we have the dependent code outside of the block but this is okay due to the success variable.

### Why Exceptions?

There are cases such as `nextInt()` where you may think that the "exception" is being handled for us. This is true but the code is much easier to read and write when you have a separation in the error handling code and the core logic.

### Handling Multiple Exceptions

You ahve to be careful when catching multiple exceptions.

For example in the case below Exception e already captures **ALL** exceptions so the InputMismatchException and ArithmeticException are never touched. **Execution never returns to a particular try block after it throws an exception.**

```java
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a fahrenheit value: ");
        try {
            int fahrenheit = input.nextInt();
            double celsius = (5.0/9) * (fahrenheit - 32);
            System.out.printf("%s Fahrenheit: %d\n", fahrenheit);
            System.out.printf("%s %-10s: %,.1f\n", celsius);
            double x = 1331/fahrenheit;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        catch(InputMismatchException ime) {
            System.out.println("Sorry, that wasn't an int.");
            System.out.println("Please re-run the program again");
        }
        catch(ArithmeticException ae) {
            System.out.println("You entered an invalid number:");
            System.out.println("ae.getMessage()");
        }
    }
```

Here is the correct ordering:

```java
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a fahrenheit value: ");
        try {
            int fahrenheit = input.nextInt();
            double celsius = (5.0/9) * (fahrenheit - 32);
            System.out.printf("Fahrenheit: %d\n", fahrenheit);
            System.out.printf("Celsius: %,.1f\n", celsius);
            double x = 1331/fahrenheit;
        }
        catch(InputMismatchException ime) {
            System.out.println("Sorry, that wasn't an int.");
            System.out.println("Please re-run the program again");
        }
        catch(ArithmeticException ae) {
            System.out.println("You entered an invalid number:");
            System.out.println("ae.getMessage()");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
```

You can `getMessage()` and `printStackTrace()` and others.

### Combined Catch Blocks

In version 7 of Java and beyond you can combine multiple catch blocks into 1.

```java
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a fahrenheit value: ");
        try {
            int fahrenheit = input.nextInt();
            double celsius = (5.0/9) * (fahrenheit - 32);
            System.out.printf("Fahrenheit: %d\n", fahrenheit);
            System.out.printf("Celsius: %,.1f\n", celsius);
            double x = 1331/fahrenheit;
        }
        catch(InputMismatchException | ArithmeticException e) {
            System.out.println("Sorry, that wasn't an valid value.");
            System.out.println("Please re-run the program again");
        }
    }
```

### The finally Block

The finally block contains statements that must execute regardless if the try exectutes or not. Commonly this is used for cleanup code.

Below the nextLine() would fit this use case.

```java
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean success = false;
        int fahrenheit = 0;

        while (!success) {
            try {
                System.out.print("Enter a fahrenheit value: ");
                fahrenheit = input.nextInt();
                success = true;

            }
            catch(InputMismatchException e) {
                input.nextLine();
                System.out.println("Sorry, that wasn't an int.");
                System.out.println("Please try again");
            }
        }
        
        double celsius = (5.0/9) * (fahrenheit - 32);
        System.out.printf("Fahrenheit: %d\n", fahrenheit);
        System.out.printf("Celsius: %,.1f\n", celsius);

    }
```

For example:

```java
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean success = false;
        int fahrenheit = 0;

        while (!success) {
            try {
                System.out.print("Enter a fahrenheit value: ");
                fahrenheit = input.nextInt();
                success = true;
            }
            catch(InputMismatchException e) {
                System.out.println("Sorry, that wasn't an int.");
                System.out.println("Please try again");
            }
            finally {
                input.nextLine();
            }
        }
        System.out.print("Enter a day of the week: ");
        String day = input.nextLine();
        double celsius = (5.0/9) * (fahrenheit - 32);
        System.out.printf("%s Fahrenheit: %d\n", day, fahrenheit);
        System.out.printf("%s Celsius: %,.1f\n", day, celsius);
    }
```

### File I/O (and the FileNotFoundException)

Below you see a program that takes in file name and word to search for i.e. `java FileTest Test.txt We` would run the program with the Test.txt file as the input file and searches for the word "We". Whether or not the word exists it outputs a file with a name including the lines with the word we are searching for. 

```java
    public static void main(String[] args) {
        String inputFileName = args[0];
        String word = args[1];

        File fileIn = new File(inputFileName);
        File fileOut = new File(word+"In"+inputFileName);

        Scanner fileScan = null;
        PrintWriter filePrint = null;

        try {
            fileScan = new Scanner(fileIn);
            filePrint = new PrintWriter(fileOut);

            int LineCount = 0;
            filePrint.printf("Lines in %s containing %s: \n", args[0], args[1]);

            while (fileScan.hasNextLine()) {
                String Line = fileScan.nextLine();
                if (Line.contains(word)) {
                    filePrint.println(LineCount + ": " + Line);
                }
                LineCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (fileScan != null) {
                fileScan.close();
            }
            if (filePrint != null) {
                filePrint.close();
            }
        }

    }
```

### Declaring Exceptions to be Thrown (or Removing the FileNotFoundException catch)

What happens if we remove the try catch and try to compile with the intent of only sending valid files?

```java
public static void main(String[] args) {
    String fileName = args[0];
    String word = args[1];

    File file = new File(args[0]);
    Scanner scan = null;

    scan = new Scanner(File);
    int lineCount = 0;
    System.out.printf("Lines in %s containing %s: \n", args[0], args[1]);

    while (scan.hasNextLine()) {
        String line = scan.nextLine();
        if (line.contains(word)) {
            System.out.println(lineCount + ": " + line);
        }
        lineCount++;
    }
    scan.close();
}
```

Because the code can throw an exception we can't compile it without some code to handle it. This can be done using `throws FileNotFoundException to the method header.

```java
public static void main(String[] args) throws FileNotFoundException{
```

### Checked vs Unchecked Exceptions

![](/images/m8_throwable_hierarchy.png)

Recall `Exception` has a subclass `RuntimeException`. Exceptions under `RuntimeException` are called **unchecked exceptions** because checking for them would be cumbersome. I.e. checking for null pointer exceptions every time we use a reference variable. Everything outside `RuntimeException` hierarchyt are the **checked exceptions** that we must either catch or specify.

### Defining an Exception and using the throw operator

We can write our own exceptions by extending the `Exception` class or any of it's descendants.

Rememver `ArithmeticException` is a descendant of `RuntimeException` so generally it is unchecked so we can specify to check it. Further, this allows us to custome the message when we use `getMessage`.

```java
public class DivideByZeroException extends ArithmeticException {
    public DivideByZeroException() {
        super("Divide by zero.");
    }
}
```

An example throwing and catching the error:

```java
import java.util.Scanner;
import java.util. InputMismatchException;

public class FahrenheitToCelsiusExceptions {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Fahrenheit value: ");
        try {
            int fahrenheit = input.nextInt();
            double celsius = (5.0/9) * (fahrenheit - 32);
            System.out.printf("Fahrenheit: %d\n", fahrenheit);
            System.out.printf("Celsius:    %.1f\n", celsius);
            if (fahrenheit == 0) {
                throw new DivideByZeroException();
            }
            double x = 1331/fahrenheit;
        }
        catch(InputMismatchException ime) {
            System.out.println("Sorry, that wasn't an int.");
            System.out.println("Please re-run the program again");
        }
        catch(DivideByZeroException dze) {
            System.out.println("Oops. You entered an invalid number:");
            System.out.println(dze.getMessage());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### More File I/O: Delimited Files

Recall previously when we did a terminal output for creating a `Wolf` pack.

```java
public static void main(String[] args) {
    Wolf[] pack = {
        new Wolf(17.1, 2),
        new Wolf(3, 10),
        new Wolf(9.2, 7),
        new Wolf(9.1, 8),
        new Wolf(17.01, 3),
        new Wolf(16.2, 1),
        new Wolf(16, 4),
        new Wolf(16, 5),
        new Wolf(10, 6),
        new Wolf(5, 9)
    };

    Arrays.sort(pack);

    File fileOut = new File("SortedWolves.csv");
    PrintWriter filePrint = null;

    try {
        filePrint = new PrintWriter(fileOut);
        for (Wolf wolf : pack) {
            filePrint.println(wolf.getRank() + "," + wolf.getSize());
        }
    } 

    catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    }


    finally {
        if (filePrint != null) {
            filePrint.close();
        }
    }
}
```

This improvement in the code now would write each wolf to a line in a CSV.

```
10,3.0
9,5.0
8,9.1
7,9.2
6,10.0
5,16.0
4,16.0
3,17.01
2,17.1
1,16.2
```

What if we wanted to find the average size of Wolves in the pack using this csv file?

We previously saw how to read af ile line by line but what if we want to use a delimiter?

```java
public static void main(String []args) {
    String chant = "Java Is The Best!";
    String[] tokens = chant.split(" ");
    for (String x : tokens) {
        System.out.println(x);
    }
}
```

```
Java
Is
The
Best!
```

Using similar method we split using a `,` instead this time.

```java
public static void main(String[] args) {
    File fileIn = new File("SortedWolves.csv");
    Scanner fileScan = null;
    String[] tokens = null;
    double[] allWeights = new double[10];
    int index = 0;
    try {
        fileScan = new Scanner(fileIn);
        String line = null;
        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            tokens = line.split(",");
            allWeights[index] = Double.parseDouble(tokens[1]);
            index++;
        }
    } 
    catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    }

    finally {
        if (fileScan != null) {
            fileScan.close();
        }
    }
}
```

What if we wanted to split a String using uppercase letters as a delimiter that is 26 actual delimiters (A,B,C...Z) and we don't want to call `split()` that many times. Instead you can use RegEx `[A-Z]`.

```java
public static void main(String []args) {
    String chant = "Java Is The Best!";
    String[] tokens = chant.split("[A-Z]+");
    for (String x : tokens){
        System.out.println(x);
    }
}
```

```
ava 
s 
he 
est!
```

You can also parse delimited strings using Scanner.

```java
Scanner (String source)
```

```java
public static void main(String[] args) {
    File fileIn = new File("SortedWolves.csv");
    Scanner fileScan = null;
    Scanner wolfScan = null;
    double[] allWeights = new double[10];
    int index = 0;
    try {
        fileScan = new Scanner(fileIn);
        String line = null;
        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            wolfScan = new Scanner(line);
            wolfScan.useDelimiter(",");
            wolfScan.nextInt(); //consume unused rank token
            allWeights[index] = wolfScan.nextDouble();
            index++;
        }
    } 
    catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    }
    finally {
        if (fileScan != null) {
            fileScan.close();
        }
    }
}
```

We had to specify the delimiter here because otherwise the default is whitespace.