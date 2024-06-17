# Table of Contents
1. [Back to Basics](#back-to-basics)
    1. [Whitespaces](#whitespaces)
    2. [Errors](#errors)
        1. [Compiler Errors](#compiler-errors)
        2. [Runtime Errors](#runtime-errors)
        3. [Logical Errors](#logical-errors)
    3. [Comments](#comments)
    4. [Variables and Constants](#variables-and-constants)
    5. [Primitive Types](#primitive-types)
    6. [Arithmetic Expressions](#arithmetic-expressions)
2. [Using Predefined Classes](#using-predefined-classes)
3. [Input and Output](#input-and-output)


## Back to Basics

### Whitespaces

Blanks, tabs, and newline characters that help provide separation.

Take a look at some previous code we wrote:

```java
// A simple program that prints text on the terminal
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Our first Java program!");
    }
}
```

The whitespace between separating words is all actually "extra". The code will still compile. But, the extra whitespacing helps improve readability.

```java
// A simple program that prints text on the terminal
public class HelloWorld {
public static void main(String[] args) {
System.out.println("Our first Java program!");}}
```

### Errors

3 types of errors:
* compiler
* runtime
* logical

**Note semantics represents the meaning of some code.**

Just because code is syntactically correct, it is not semantically correct. That means just because it compiles does not necessarily mean it works or does what you expect it to. 

Errors are essentially getting either syntax or semantics wrong (or both).

#### Compiler Errors

**Compilers check syntax not semantics.**

For example removing a space between words when assigning a variable.

The error will have the name of the Java file and the line number with that error.

Then there will be an error description such as `connt find symbol`.

#### Runtime Errors

Even if you got the syntax right there could still be an error as runtime due to semantics.

For example `int x = 5/0;`.

The error message will contain a list of the statements and methods that were active when the error occurred. This is referred to as a **stack trace**.

#### Logical Errors

Caused when there are mistakes in the program's semantics.

For example, entering the wrong mathematical operator when writing a formula in the code.

In this case there is not necessarily a useful error produced. Because of this you should incrementally test programs as you build them and not wait until you have written large complex code.

**Compiling and testing after each class and method is good practice.**

### Comments

**All comments are ignored by the compiler.**

Java has 3 types of comments:
* Line comments
* Block comments (multi-line)
* Javadoc comments

**Line comments** are dictated by `//` for example:
```java
//line comment
```

**Block comments** are dictated by `/* and */` for example:
```java
/*
block
comment
*/
```

**Javadoc comments** are automatically created by Java scanning source code for comments and creating a nicely formatted HTML file describing your code. They are dictated by `/** and */`.

### Variables and Constants

One method cannot see the variables declared inside another. You will receive a **cannot find symbol error**. A variables scope is within the closest set of braces `{}`.

We looked at assigning variables earlier such as `double PI = 3.1415926`.

But what if we don't want the variable to change later?

`final double PI = 3.1415926` will prevent any changes later. In this case instead of calling it a variable we call it a **constant**.

Trying to re-assign the constant will result in a compilation error, for example:

```java
public class ConstantTest {
    public static void main(String[] args) {
        final double PI = 3.14159265359;
        PI = 3.14;
    }
}
```

### Primitive Types

The most basic types of data that are built into the Java language. Each have their own reserved word. There are 8 primitive types.

* byte - integer based
* short - integer based
* int - integer based
* long - integer based
* float - real number values
* double - real number values
* char - individual characters
* boolean - logic based values

| TYPE   | SIZE      | RANGE                                                        |
|--------|-----------|--------------------------------------------------------------|
| byte   | 8 bits    | -128 to +127                                                 |
| short  | 16 bits   | -32,768 to +32,767                                           |
| int    | 32 bits   | -2,147,483,648 to +2,147,483,647                             |
| long   | 64 bits   | -9E18 to +9E18 (approximately)                               |
| float  | 32 bits   | approximately -3.40282347E+38 to +3.40282347E+38 (6-7 digits of precision) |
| double | 64 bits   | approximately -1.79769313486231570E+308 to +1.79769313486231570E+308 (15-16 digits of precision) |

For integers the default type is **int** and for floating point numbers the default type is **double**.

Let's look at an example of assigning a large integer `99,999,999,999` which is larger than the default int allows.

`long reallyBigNumber = 99999999999;` would fail at compilation.

`long reallyBigNumber = 99999999999L;`is appropriate and defaults the default type.

Similarly with floats:

`float fraction = 0.1331;` is illegal because remember floating point defaults to **double** and not **float**.

`float fraction1 = 0.1331F;` is legal.

**You can also use lowercase to override i.e. `f` but it is not recommended**

You can also use `D` numeric literal. Remember before this code:

`double saturdayCelsius = (5.0/9) * ...`

We used `5.0` instead of `5` to avoid error since this variable is **double**. We could have also done the following:

`double saturdayCelsius = (5D/9) * ...`

The **char type** represents individual characters. 

Because characters do not necessarily follow a semantically logical order we refer to a collection of characters as a **set** and not a **range**. 

There are various **character sets** such as ASCII. Java uses a superset of ASCII called **Unicode**.

Assigning **char**:

`char space = ' ';`

`char yes = 'Y';`

**Double quotes are used for strings only, chars must be wrapped in single quotes.** But what if there is a single quote inside a string? In this case you need to use an **escape sequence** followed by a **descriptor** for example `'\''`.

Escape sequences:
* `\t` - tab
* `\n` - new line
* `\r` - carriage return
* `\\` - backslash
* `\"` - double quote
* `\'` - single quote

`"For \"Quotes inside quotes\", make sure to use the quote escape sequence"`

**Boolean** can only have 2 possible values, `true` or `false`.

### Arithmetic Expressions

An **expression** is a combination of operators, operands, or method calls in a single Java statement. The expression must evaluate to a single value.

|operator|symbol|
|--------|------|
|addition|+|
|subtraction|-|
|multiplication|*|
|division|/|
|remainder (modulus)|%|



## Using Predefined Classes
## Input and Output
