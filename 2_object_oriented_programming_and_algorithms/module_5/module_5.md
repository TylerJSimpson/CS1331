# Table of Contents
1. [Writing Classes 1](#writing-classes-1)
    1. [Identifying Data and Behavior](#identifying-data-and-behavior)
    2. [Instance Variables](#instance-variables)
    3. [Visibility Modifiers (public and private)](#visibility-modifiers-public-and-private)
    4. [Testing Objects](#testing-objects)
    5. [The Default Constructor](#the-default-constructor)
    6. [Writing Constructors](#writing-constructors)
    7. [Calling Constructors](#calling-constructors)
    8. [Methods](#methods)
    9. [Static Variables and Constants](#static-variables-and-constants)
    10. [Static Methods](#static-methods)
2. [Writing Classes 2](#writing-classes-2)

3. [Inheritance](#inheritance)


## Writing Classes 1

In the 1st section (modules 1-4) we focused on use-cases involving classes that we did not write such as Scanner and String. Now we will focus on representing custom concepts by writing instantiable classes.

### Identifying Data and Behavior

Let us work on a "blueprint class" by using the example of insects.

What do insects do?

Behaviors:
* move
* eat

We know that as an insect eats it's weight increases and as it moves it's weight decreases. This relationship can help us determine the needed data. In this case we can use weight and location (x,y).

|behaviors|data/state|
|---------|----------|
|eat|weight|
|move|x|
||y|

```java
public class Insect {

}
```

### Instance Variables

Using the weight as an example. This variable can maybe start the same, say each insect starts with weight of 10. But if 1 of the insects weight increases we don't want it to increase for all other insects. This is instance specific data which means it's value is unique to each instance or object of a class.

Remember before we spoke of heaps. Imagine we created 4 insect instances. They will each have their own space within the insect heap. That is, each variable may have the same name but it has it's own separate space in memory.

|insect1|insect2|insect3|insect4|
|-------|-------|-------|-------|
|weight=14.0|weight=10.0|weight=10.0|weight=10.0|
|x=3|x=13|x=46|x=30|
|y=19|y=12|y=2|y=20|

Declaring an instance variable actually occurs at the class level and is not specific to a method or block statement. 

```java
public class Insect {
    private double weight;
    private int x;
    private int y;
}
```

**In Java any variable or method that is declared in this scope is called a class member. So an instance variable is a class member and NOT a local variable.**

The convention is to always declare instance variables directly below the class header.

### Visibility Modifiers (public and private)

The reason we use the **private** key word in declaring the instance variables is because of the concept of **encapsulation**.

**Encapsulation** states that a class itself should govern how the instance variables of its objects are manipulated so that the objects operate as they are intended.

The **private** keyword simply enforces **encapsulation** by giving access to an instance variable just to the methods wtihin its class.

### Testing Objects

Regular and incremental testing will help stop errors from building up.

```java
public class Insect {
    private double weight;
    private int x;
    private int y;
}
```

Just compiling is not enough, though. We need to test runtime too. So we need to test the instantiation of insect objects. While this can be done by writing test classes that will become cumbersome as the code increases in complexity. Instead a main method can be added for testing.

```java
public class Insect {

    //instance variables
    private double weight;
    private int x;
    private int y;

    //test method
    public static void main(String[] args) {
        Insect buzz1 = new Insect();
        Insect buzz2 = new Insect();
    }
}
```

Now we can compile and run the code to search for errors.

```bash
javac Insect.java
java Insect
```

Now we can continue developing this test method as we evolve the class.

### The Default Constructor

You may have wondered how the runtime succeeded when we only initialized the instance variables. It turns out that java has a **default constructor** that assigns default values.

The default value initialization is the same as what we saw with arrays.

numeric primitive types default to 0

booleans default to false

object types default to null

In our case the initial weights of `buzz1` and `buzz2`are 0.0 and x and y are both 0. But having a default weight of 0.0 doesn't make sense so we need to overwrite the default constructor.

### Writing Constructors

Let us write our own constructor method.

Notice this is public since we want other classes to have access. And notice there is no return type. 

**Constructors are the only kinds of methods that can be declared without a return type.**

You will actually get a compiler error if you try to specify a return type. Even **void** will cause an error.

**The name of the constructor also must be the same as the name of the class.**

```java
    //constructor
    public Insect(double initWeight, int initX, int initY) {
        weight = initWeight;
        x = initX;
        y = initY;
    }
```

### Calling Constructors

```java
    //test method
    public static void main(String[] args) {
        //Insect buzz1 = new Insect();
        //Insect buzz2 = new Insect();
        Insect bug1 = new Insect(10, 100, 90);
        Insect bug2 = new Insect(4, -300, 400);
    }
```

Now we can instantiate new insects with values other than those of the default constructor. The reason the initial 2 initializations are commented out is because the code would fail at compilation if not. This is because once we overwrite the constructor the original one can no longer be used. 

### Methods

### Static Variables and Constants

### Static Methods

## Writing Classes 2

## Inheritance