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
    1. [The while statement](#the-while-statement)
    2. [The do-while statement](#the-do-while-statement)
    3. [The for statement](#the-for-statement)
    4. [Nested loops](#nested-loops)
    5. [The break statement](#the-break-statement)
    6. [The continue statement](#the-continue-statement)

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

If the if statement is false the program continues to run just not the statement inside the brackets.

### Comparing Non-numeric Data

You can also compare `char` via the Unicode value. Values increase from 0 to 9 and through various symbols. Then continuing with 1st uppercase alphabet then lower case alphabet with 0 = 48 and z = 122.

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

In our previous example extremely hot temperatures will result in the program telling us to go to the park. So perhaps we also want an extra requirement.

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
1. PARENTHESIS: ()
2. NOT: !
3. RELATIONAL: >, >=, <=, <, !=, ==
4. AND: &&
5. OR: ||


### Other Operators

Boolean expressions can be extended to other operators as long as the final results are true or false values.

```java
if ((saturdayFahrenheit + sundayFahrenheit) / 2 >= MIN_TEMP) {
    System.out.println("Yay! Nice weekend average.");
}
```

Note that **mathetmatical operators** go before **relational operators** which go before **logical operators**.

In the above java code the order is:
1. (saturdayFahrenheit + sundayFahrenheit)
2. / 2
3. \>= MIN_TEMP

This is a more appropriate way of structuring the statement:
```java
if (weekendAverage >= MIN_TEMP) {
    System.out.println("Yay! Nice weekend average.");
}
```

### Short-circuit Evaluation

**Short-circuit evaluation** is when java checks the logical operators based on the sidedness rule it will automatically skip a portion of the computation.

For example:
`(fahrenheit >= MIN_PARK_TEMP) && (fahrenheit <= MAX_PARK_TEMP)`

In this case with the `&&` operator java knows if one side is False then the other side does not need to be calculated. It will check the left side first. If it determines that it is False then it will not calculate the right side of the equation.

**Short-circuit evaluation** also applies to the `||` operator.

`raining || !isValidTemp()`

In this case Java again goes left to right. In this case, if the first portion `raining` is true then it skips the right side calculations.

Because of the left to right processing and **short-circuit evaluation** a lot of computing can be cut out if development puts the less computationally intensive tasks on the left side of equations.

### Nesting

So far we have experienced some nesting, particularly the `System.out` method within if and if-else statements.

```java
if ((fahrenheit >= MIN_PARK_TEMP) && (fahrenheit <= MAX_PARK_TEMP)) {
    System.out.println("Yay! It's at least " + MIN_PARK_TEMP + "degrees but under " + MAX_PARK_TEMP + ".");
    if (raining) {
        System.out.println("Look up fun things to do in the rain.");
    }
    else {
        System.out.println("Time to go to the park.");
    }
}
```

### The Dangling Else Problem

```java
public class DanglingElse {
    public static void main(String[] args) {
        int num = 9;
        if (num > 0);
        if (num < 10);
        System.out.println("aaa");
        else
        System.out.println("bbb");
    }
}
```

Output: "aaa"

While the above is legal and will compile and run the code format makes it difficult to follow.

### The Ternary Conditional Operator

Java offers a one-line shortcut for if-else statements called the **ternary conditional operator**.

`condition ? expression1 : expression2`

Because the first operand is a condition and results in a boolean value - if the condition is true then the operator returns ther esult of evaluating the scond operand (expression 1). Otherwise, the operator returns the result of the third operand (expression2).

`raining = rainInput.startsWith("y") ? true : false;`

The above expression uses the **ternary conditional operator** to the same effect as the code below:

```java
if (rainInput.startsWith("y")) {
    raining = true;
}
else {
    raining = false;
}
```

Here is an example where the **ternary conditional operator** is used to choose between degree and degrees based on the fahrenheit int:

```java
System.out.println(fahrenheit + " " + (((fahrenheit == 1) || (fahrenheit == -1)
                                           ? "degree"
                                           : "degrees"));
```

### Multi-way Branching

We have seen splitting execution into two possible paths with the **if-else statement**, but what if we want multiple paths?

This can be achieved using a **multi-way if-else statement**.

```java
        if (condition1) {
            statement(s)
        }
        else if (condition2) {
            statement(s)
        }
        else {
            statement(s)
        }
```

In this case the chain continues until one of the **if statements** is found to be true. Then the code inside is executed and the execution jumps outside of the chain.

### The switch Statement

Another way to achieve multi-way branching is via the **switch statement**.

```java
        switch (expression) {
            case value1:
                statement(s)
                break;
            case value2:
                statement(s)
                break;
            default:
                statement(s)
        }
```

Inside the first parenthesis, there can be an expression or a variable or something more complex. What matters is that it evaluates to a value of one of these types:
* char
* byte
* short
* int
* String
* enum
* Character
* Byte
* Short
* Integer

Within the braces are a set of branches. They can either begtin with the keyword `case` or `default`. The `case` sort of acts as an if while the `default` sort of acts as the else.

Here is an example using a telephone menu:
```java
import java.util.Scanner;

public class SwitchTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your selection: ");
        int selection = input.nextInt();

        String menuOption;

        switch (selection) {
            case 0:
                menuOption = "Operator";
                break;
            case 1:
                menuOption = "Customer Service";
                break;
            default:
                menuOption = "Retry Selection";
                break;
        }
        System.out.println(menuOption);
    }
}
```

*Note that without a `break` in the branches, nothing will stop the default from executing.*

## Iteration

**Iteration** is the concept which involves looping over a block of code until some condition is false.

We will touch on:
* while statements
* do-while statements
* for statements

### The while statement

Like we have seen with other statements, if there is only 1 body braces are not required. Generally it is best practice to always use braces, though.

```
while (booleanExpression)
    bodyStatement;
```

```
while (booleanExpression) {
    bodyStatement1;
    bodyStatement2;
    ...
}
```

```java
public class HelloWorldLoop {
    public static void main(String[] args) {
        int counter = 0;
        while (counter < 10) {
            System.out.println("Hello, World!");
            counter++;
        }
    }
}
```

You don't always have to use a counter.

```java
import java.util.Scanner;

public class FahrenheitToCelsiusOperatorsWhile {
    public static void main(String[] args) {
        final int MAX_TEMP = 140;
        final int MIN_PARK_TEMP = 70;
        final int MAX_PARK_TEMP = 90;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Fahrenheit value: ");
        int fahrenheit = input.nextInt();

        while (fahrenheit > MAX_TEMP) {
            System.out.println("Error: The fahrenheit value must be lower than " + MAX_TEMP);
            System.out.print("Please enter another Fahrenheit value: ");
            fahrenheit = input.nextInt();
        }


        System.out.print("Enter a day of the week: ");
        String day = input.next();

        double celsius = (5.0/9) * (fahrenheit - 32);

        System.out.println(day + " Fahrenheit: " + fahrenheit);
        System.out.printf("%s %.1f \n", day + " Celsius", celsius);

        if ((fahrenheit >= MIN_PARK_TEMP) && (fahrenheit <= MAX_PARK_TEMP)) {
            System.out.println("Yay! It's at least " + MIN_PARK_TEMP + " degrees but under " + MAX_PARK_TEMP + ".");
            System.out.println("Time to go to the park.");
        }
        else {
            System.out.println("Stay home and learn 1331 online.");
        }
    }
}
```

### The do-while statement

The **do-while statement** starts with one or more statements in the body automatically being executed. Then the condition is assessed. This repeats until the condition is false.

```
do {
    bodyStatement1;
    bodyStatement2;
} while (booleanExpression);
```

```java
public class HelloWorldLoop {
    public static void main(String[] args) {
        int lineNum = 1;
        do {
            System.out.println("Hello, World!" + lineNum);
            lineNum++;
        } while (lineNum <= 10);
    }
}
```

### The for statement

The **for statement** is another option for writing repetition statements that are controlled by a counter variable.

```
for (initStatement; condition, updateStatement) {
    bodyStatement1;
    bodyStatement2;
    ...
}
```

```java
public class HelloWorldLoop {
    public static void main(String[] args) {
        for (int lineNum = 1; lineNum <= 10; lineNum++) {
            System.out.println("Hello, World!");
        }
    }
}
```

You can also declare the variable used in the intialization statement of the for loop outside the **for statement** and then initialize it inside.

```java
public class HelloWorldLoop {
    public static void main(String[] args) {
        int lineNum;
        for (lineNum = 1; lineNum <= 10; lineNum++;) {
            System.out.println("Hello, World!");
        }
    }
}
```

You could also use decrements for example starting `lineNum = 10` and updating with a decrement `lineNum--`.

You can also update with other mathematical statements such as `pow*=2`.

### Nested loops

```java
public class HelloWorldLoop {
    public static void main(String[] args) {
        int outerCounter = 1;
        while (outerCounter <= 5) {
            int innerCounter = 1;
            while (innerCounter <= 10) {
                System.out.println("Hello, World!");
                innerCounter++;
            }
            outerCounter++;
        }
    }
}
```

In this case of nested loops above "Hello, World!" will be printed 50 times. 10 times in the inner loop which is run 5 times, 1 for each outer loop.

### The break statement

We saw **break statements** used earlier. **Break statements** can also be used to exit a while, do-while, or for statement in a similar manner.

### The continue statement

Similar to the break statement, a **continue statement** can be used to jump out of a loop cycle or iteration. Instead of terminating the loop, it just jumps to the next cycle.

```java
import java.util.Scanner;

public class FahrenheitToCelsiusContinue {
    public static void main(String[] args) {

        final int MIN_TEMP = -200;
        final int MAX_TEMP = 200;

        Scanner input = new Scanner(System.in);

        double totalFahrenheit = 0;
        int validFahrenheits = 0;
        int fahrenheit;

        for (int i = 1; i <= 5; i++) {
            
            System.out.print("Enter a Fahrenheit value: ");
            fahrenheit = input.nextInt();
            if ((fahrenheit >= MIN_TEMP) && (fahrenheit <= MAX_TEMP)) {
                totalFahrenheit = totalFahrenheit + fahrenheit;
                validFahrenheits++;
            }
            else {
                System.out.println("Invalid value.");
                continue;
            }
        }
        if (validFahrenheits > 0) {
            System.out.println("Average Fahrenheit Temperature: "
                                + totalFahrenheit/validFahrenheits);
        }
    }
}
```

In this example, if you input an invalid int the continue statement will allow the loops to continue. As long as 1 of the inputs was valid it will still return an average.

***break** and **continue** can be used with the **switch method**, **while and do-while loops**, and **for loops**.*