# Table of Contents
1. [Decision-Making Statements](#decision-making-statements)
    1. [The if statement](#the-if-statement)
    2. [Comparing Non-numeric Data](#comparing-non-numeric-data)
    3. [The equals Method](#the-equals-method)
    4. [The compareTo Method](#the-compareto-method)
    5. [The if-else Statement](#the-if-else-statement)
    6. [Logical Operators](#logical-operators)
    7. [Other Operators](#other-operators)
    8. [Short-circuit Evaluation](#short-circuit-evaluation)
    9. [Nesting](#nesting)
    10. [The Dangling Else Problem](#the-dangling-else-problem)
    11. [The Ternary Conditional Operator](#the-ternary-conditional-operator)
    12. [Multi-way Branching](#multi-way-branching)
    13. [The switch Statement](#the-switch-statement)
2. [Iteration](#iteration)

## Decision-Making Statements

While the static programs we have written in previous modules can complete some basic tasks, introducing decision-making statements/conditional statements will help us achieve dynamic behavior.

### The if statement

```java
if (booleanExpression) {
    statement1;
    statement2;
    statement3;
}
```

```java
if (fahrenheit > 70)
    System.out.println("Go outisde!");
```

Relational Operators:
* `<`
* `>` 
* `<=`
* `>=`

Equality Operators:
* `==`
* `!=`

If the if statement is false the program continues to run just not the 

### Comparing Non-numeric Data

You can also compare `char` viat he Unicode value. Values increase from 0 to 9 and through various symbols. Then continuing with 1st uppercase alphabet then lower case alphabet with 0 = 48 and z = 122.

### The equals Method

This is completely legal in Java:

`String x = "park";`

`String y = "home";`

`(x == y)`

`(x != y)`

In this case Java checks if the variables are aliases. Because they are not it would return `False` then `True`.

But what about checking the actual content of the strings?

We must call the String class `equals` method.

`(x.equals(y))` is false

`(!x.equals(y))` is true

There is a caveate that we must consider.

```java
//in this case String x and String y are considered aliases
String x = "park";
String y = "park";
```

x == y would evaluate as True.

It considers them aliases because of an optimization process where it recycles the string in the pool via inturning.

This can be important in conserving memory but it must be understood to avoid issues with aliasing.

```java
//in this case String x and String y are not aliases
String x = new String("park");
String y = "park";
```

x == y would evaluate as false.

### The compareTo Method

`int compareTo(String anotherString)`

`int result = x.compareTo(y);`

Returns:
* 0 - x and y are equal
* negative int - x is less than y
* positive int - x is greater than y

This is essentially how we get around an illegal String comparison like (x>y).

### The if-else Statement

Remember our program from earlier where we introduced the **if** statement.

```java
import java.util.Scanner;

public class FahrenheitToCelsius {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Fahrenheit value: ");
        int fahrenheit = input.nextInt();
        System.out.print("Enter a day of the week: ");
        String day = input.next();
        double celsius = (5.0/9) * (fahrenheit - 32);
        System.out.println(day + " Fahrenheit: " + fahrenheit);
        System.out.printf("%s %.1f \n", day + " Celsius:", celsius);

        if (fahrenheit >= 70) {
            System.out.println("Yay! Go to park.");
        }
    }
}
```

This would have greatly benefited from an **if-else** statement.

```
if (booleanExpression)
    statement1;
else
    statement2;
```

```
if (booleanExpression) {
    statement;
    statement;
    statement;
}
else {
    statement;
    statement;
    statement;
}
```

```java
        if (fahrenheit >= 70) {
            System.out.println("Yay! Go to park.");
        }
        else {
            System.out.println("Stay home where there’s wi-fi and learn 1331 online");
        }
```

### Logical Operators

In our previous example extremely hot temperatures would will result in the program telling us to go to the park. So perhaps we also want an extra requirement.

Logical Operators:
|Name|Symbol|
|----|------|
|AND|&&|
|OR|\|\||
|NOT|!|

Truth tables:

NOT Operator (!):

|a|!a|
|-|-|
|true|false|
|false|true|

AND (&&) and OR (||) Operators:
|a|b|a&&b|a\|\|b|
|-|-|----|------|
|false|false|false|false|
|false|true|false|true|
|true|false|false|true|
|true|true|true|true|

Here is an example:

`(fahrenheit >= 70) && (fahrenheit <= 90)`

Note that the relational operators have higher precedence than logical operators so the parenthesis are not necessary in this case. It does help with readability, though.

Putting it all together:
```java
import java.util.Scanner;

public class FahrenheitToCelsiusOperatorsTest {
    public static void main(String[] args) {
        final int MIN_PARK_TEMP = 70;
        final int MAX_PARK_TEMP = 90;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Fahrenheit value: ");
        int fahrenheit = input.nextInt();
        System.out.print("Enter a day of the week: ");
        String day = input.next();
        double celsius = (5.0/9) * (fahrenheit - 32);

        //initialize raining as false only change to true based on input
        boolean raining = false;
        System.out.print("Is it raining (y/n)? ");
        String rainInput = input.next().toLowerCase();
        if (rainInput.startsWith("y")) {
            raining = true;
        }

        System.out.println(day + " Fahrenheit: " + fahrenheit);
        System.out.printf("%s %.1f \n", day + " Celsius", celsius);
        if ((fahrenheit >= MIN_PARK_TEMP) && (fahrenheit <= MAX_PARK_TEMP) && !raining) {
            System.out.println("Yay! It's at least " + MIN_PARK_TEMP + " degrees but under " + MAX_PARK_TEMP + ".");
            System.out.println("Time to go to the park.");
        }
        else {
            System.out.println("Stay home and learn 1331 online.");
        }
    }
}
```

Precedence:
* parenthesis takes precedence over logical operators
* Relational operators > logical operators
* ! takes precedence over AND and OR
* AND takes precedence over OR


### Other Operators

### Short-circuit Evaluation

### Nesting

### The Dangling Else Problem

### The Ternary Conditional Operator

### Multi-way Branching

### The switch Statement

## Iteration