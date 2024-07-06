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
        1. [Math.random()](#mathrandom)
2. [Writing Classes 2](#writing-classes-2)
    1. [Encapsulation Revisited](#encapsulation-revisited)
    2. [Accessor (or Getter) Methods](#accessor-or-getter-methods)
    3. [Mutator (or Setter) Methods](#mutator-or-setter-methods)
    4. [Overloading Constructors](#overloading-constructors)
    5. [Constructor Chaining and this()](#constructor-chaining-and-this)
    6. [Using "this" as a reference](#using-this-as-a-reference)
    7. [The toString Method](#the-tostring-method)
    8. [Putting Things Together (or A Simple OO Application)](#putting-things-together-or-a-simple-oo-application)
    9. [Die.java and the Random Class](#diejava-and-the-random-class)
    10. [Craps.java](#crapsjava)
    11. [Finding the Errors](#finding-the-errors)

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

There are also methods called **instance methods**. They do not have `static` modifier in their headers and represent behaviors or actions that we can invoke on a specific object.

For example below see `eat` and `move`.

```java
    //methods
    public void eat(double amount) {
        System.out.println("Nibble Nibble");
        weight = weight + amount;
    }

    public void move(int newX, int newY) {
        double distance = calculateDistance(x, y, newX, newY);
        if (distance > 0) {
            x = newX;
            y = newY;
            weight = weight * DIST_WEIGHT_LOSS_FACTOR * distance;
            System.out.printf("Moved %.2f units\n", distance);
        }
        else {
            System.out.println("Staying put");
        }
    }
```

These public methods of a class are called it's **interface**.

We also created a private method for calculations that was called in the `move` method. This is a **helper method**, a method used internally by other methods.

```java
    private static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2-y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
```

**static methods** belong to a class and are not something you invoke on a specific instance of that class. Generally, helper methods are typically static.

### Static Variables and Constants

Notice in our Insect.java code we have introduced static constant variables.

```java
    //static constants/variables
    public static final double DIST_WEIGHT_LOSS_FACTOR = 0.001;
```

* final - this means it's a constant
* public - shouldn't instance data be private?
* static - because it belongs to the class saving memory (shared across all objects of a class)

We do not violate incapsulation by having a public variable in this case because it is a constant (final modifier) so it cannot be changed outside it's class. It is a matter of preference in this case to expose this constant or not.

Because `DIST_WEIGHT_LOSS_FACTOR` is public we can access it from outside our program using `Insect.DIST_WEIGHT_LOSS_FACTOR`.

```java
    //static constants/variables
    private static int population = 0;

    //constructor
    public Insect(double initWeight, int initX, int initY) {
        weight = initWeight;
        x = initX;
        y = initY;
        population++;
    }
```

Now let us look at another example. `Population` keeps track of how many insects are created throughout the programs life.

* static - Because this variable is information about the class and not a specific instance
* private - This is not a constant like above so it needs to be private to enforce encapsulation.

### Static Methods

Let's take a look at another static method we are adding to Insect.java that covers some more cases.

First we will add a private static string array:
```java
    private static final String[] FACTS = {
        "The two main groups of insects are winged and wingless",
        "There are more than 1 million insect species",
        "Insects are cold-blooded",
        "Spiders are not considered insects"
    };
```

This array must be static because the method below we write to call it is also static.

**A static method cannot dierctly access a non-static member**.

Then we add the actual public static method:
```java
    public static String produceRandomFact() {
        int index = (int)(Math.random() * ((FACTS.length - 1) + 1));
        return FACTS[index];
    }
```

We add to our main method an output of the string produced by `produceRandomFact`:
```java
        System.out.println(produceRandomFact());
```

1. Notice that we return the value instead of print it to the terminal. This is generally better practice since we do not want to assume how the end user will want to use the value.

2. This method is static because it represents facts about insects in general not the specific instances.

3. This method is public so clients of the class to call it.

#### Math.random()

Since we used `Math.random()` above we should go over it.

It returns a random double value that is greater than or equal to 0 and less than 1. Range: [0,1).

To generate random integers some tinkering needs to happen:

`(int)(Math.random() * ((max - min) + 1)) + min`

Notice how what we wrote follows this:
```java
int index = (int)(Math.random() * ((FACTS.length - 1) + 1));
```

## Writing Classes 2

Remember previously in our Insect.java program:
```java
    //instance variables
    private double weight;
```

We declared `weight` as private. Well what if an external class like `InsectClient` instantiates some insects and invokes their eat and move methods and then tries to determine the weight? Because it's private this is a limitation but it must be private for encapsulation.

We will look at accessors and mutators which allow us to overcome this issue.

We will also look at method overloading which allows multiple versions of a method within the same class definition.

### Encapsulation Revisited

We will be using our `InsectCleint` class for testing.

```java
public class InsectClient {
    public static void main(String args[]) {
        System.out.println(Insect.produceRandomFact());
    }
}
```

What if we try to change variables that are private such as weight and location?

```java
public class InsectClient {
    public static void main(String args[]) {
        System.out.println(Insect.produceRandomFact());
        Insect bug1 = new Insect(13, 31, 0);
        bug1.weight = -1;
    }
}
```

This would generate an error because we are trying to access a private variable.

Note that public methods do have access to the private variables so using those as windows to changing those variables is the approach used for this encapsulation problem.

### Accessor (or Getter) Methods

```java
    //methods

    public double getWeight() {
        return weight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
```

We have added to Insect.java **accessor (or getter) methods**. These allow a client to access a variables value and stay in line with encapsulation.

Getter header template:

`public varType getVarName()`

Note that **getter methods** do not have any parameters, since they are not manipulating anything, and their return type is always the same of the variable they are associated with.

**Not every instance variable needs a getter method**.

Now we can return the variables.

```java
public class InsectClient {
    public static void main(String args[]) {
        System.out.println(Insect.produceRandomFact());
        Insect bug1 = new Insect(13, 31, 0);
        System.out.println(bug1.getWeight());
        System.out.println(bug1.getX());
        System.out.println(bug1.getY());
    }
}
```

Note that you can also have getter methods for static variables. In this case it also must be static. For example:

```java
public static int getPopulation() {
    return population;
}
```

### Mutator (or Setter) Methods

**By convention setter methods have a return type of void**.

Setter header template:

`public void setVarName(varType varName)`

**A former parameter's type must be the same type as the variable being changed.**

```java
    public void setX(int newX) {
        if (isLegalX(newX)) {
            x = newX;
        }
    }
```

```java
    public void setY(int newY) {
        if (isLegalY(newY)) {
            y = newY;
        }
    }
```

### Overloading Constructors

### Constructor Chaining and this()

### Using "this" as a reference

### The toString Method

### Putting Things Together (or A Simple OO Application)

### Die.java and the Random Class

### Craps.java

### Finding the Errors

## Inheritance