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
        1. [Variables](#variables)
        2. [Constructor](#constructor)
        3. [Methods](#methods)
        4. [Test Method (Main)](#test-method-main)
    10. [Craps.java](#crapsjava)
        1. [Variables](#variables)
        2. [Constructor](#constructor)
        3. [Methods](#methods)
        4. [Test Method (Main)](#test-method-main)
3. [Inheritance](#inheritance)
    1. [Terminology](#terminology)
    2. [Hierarchies](#hierarchies)
    3. [The Protected Modifier](#the-protected-modifier)
    4. [Declaring Subclasses and Instance Variables](#declaring-subclasses-and-instance-variables)
    5. [Subclass Constructors](#subclass-constructors)
    6. [Inheriting and Overriding Methods](#inheriting-and-overriding-methods)
    7. [Inheritance and The final Keyword](#inheritance-and-the-final-keyword)
    8. [The abstract Modifier](#the-abstract-modifier)
    9. [The Object Class and Overriding equals](#the-object-class-and-overriding-equals)

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

There are many exampels of constructor overloading in the Java docs. For example the Scanner class we have used can also take files and not just keyboard input.

Recall the constructor in our Insect class.

```java
    //constructor
    public Insect(double initWeight, int initX, int initY) {
        weight = initWeight;
        x = initX;
        y = initY;
        population++;
    }
```

Let's create a default variables for X and Y and create a constructor that passes those as default if they are not specific when the object is instantiated.

```java
    public static final int DEFAULT_X = 0;
    public static final int DEFAULT_Y = 0;
```

```java
    //constructors
    public Insect(double initWeight) {
        weight = initWeight;
        x = DEFAULT_X;
        y = DEFAULT_Y;
        population++;
    }

    public Insect(double initWeight, int initX, int initY) {
        weight = initWeight;
        x = initX;
        y = initY;
        population++;
    }
```

### Constructor Chaining and this()

You will notice above that there is some repeated code in our 2 constructors. We like to follow DRY (Don't repeat yourself). Thankfully Java allows us to do this using **constructor chaining**.

**In constructor chaining, a more specific constructor is always called by a less specific**.

We will use the **this()** keyword which delegates the initialization rather than instantiating a new object.

**Note when you use this() in a constructor it must be the first statement**.

```java
    //constructors
    public Insect(double initWeight) {
        this(initWeight, DEFAULT_X, DEFAULT_Y);
    }

    public Insect(double initWeight, int initX, int initY) {
        weight = initWeight;
        x = initX;
        y = initY;
        population++;
    }
```

### Using "this" as a reference

**The this keyword can also be used as a reference within a constructor or non-static method**.

Within a constructor it refers to the current object being initialized by that constructor. Within a method it refers to the object on which the method is being invoked.

Note that currently our parameters `initWeight`, `initX`, and `initY` do not have the same name as our instance variables `weight`, `x`, and `y`.

Using **this** we can simplify.

```java
    public Insect(double initWeight, int initX, int initY) {
        weight = initWeight;
        x = initX;
        y = initY;
        population++;
    }
```

```java
    public Insect(double weight, int x, int y) {
        this.weight = weight;
        this.x = x;
        this.y = y;
        population++;
    }
```

**this** can also be used on non-static methods.

```java
    public void setX(int newX) {
        if (isLegalX(newX)) {
            x = newX;
        }
    }
```

```java
    public void setX(int x) {
        if (isLegalX(x)) {
            this.x = x;
        }
    }
```

### The toString Method

**toString() returns a String representation of an object**

You may have noticed how verbose it is becoming to call our simple program with only 3 instance variables. Imagine if it was more complex.

```java
public class InsectClient {
    public static void main(String args[]) {
        System.out.println(Insect.produceRandomFact());
        Insect bug1 = new Insect(13, 31, 0);
        System.out.println("BUG1");
        System.out.println(bug1.getWeight());
        System.out.println(bug1.getX());
        System.out.println(bug1.getY());
        System.out.println(bug1.getPopulation());

        Insect bug2 = new Insect(13);
        System.out.println("BUG2");
        System.out.println(bug2.getWeight());
        System.out.println(bug2.getX());
        System.out.println(bug2.getY());
        System.out.println(bug2.getPopulation());

        Insect bug3 = new Insect();
        System.out.println("BUG3");
        System.out.println(bug3.getWeight());
        System.out.println(bug3.getX());
        System.out.println(bug3.getY());
        System.out.println(bug3.getPopulation());
    }
}
```

Thankfully **toString()** can help us do this. We can call it as-is but we are not yet ready to interpret that. Instead we will override it to make it more user-friendly for the time being.

First we must override it in our Insect class:
```java
    public String toString() {
        return "weight: " + weight + ", x: " + x + ", y: " + y;
    }
```

Now we can simplify our InsectClient class:
```java
public class InsectClient {
    public static void main(String args[]) {
        System.out.println(Insect.produceRandomFact());
        Insect bug1 = new Insect(13, 31, 0);
        System.out.println("BUG1");
        System.out.println(bug1.toString());
        System.out.println(Insect.getPopulation());

        Insect bug2 = new Insect(13);
        System.out.println("BUG2");
        System.out.println(bug2.toString());
        System.out.println(Insect.getPopulation());

        Insect bug3 = new Insect();
        System.out.println("BUG3");
        System.out.println(bug3);
        System.out.println(Insect.getPopulation());
    }
}
```

Notice that you actually don't even need to write `toString()` as shown in bug3. If a non-String object is passed as input to println (or print) Java will automatically call the object's `toString` method and use the returned String as the actual parameter for that println (or print) call.

### Putting Things Together (or A Simple OO Application)

Let's put together all thes skills and create an object-oriented application. We are going to create a simplified version of the game of Craps. The goal is to roll a pair of dice and get a certain set of numbers at certain stages.

Rules:
1. If you roll 7 or 11 you win
2. If you roll 2, 3, or 12 you lose
3. If you did not roll any of the above you roll a "point number"
4. Keep rolling until you repeat the point number and win or roll 7 and lose

When building an object-oriented program the nouns tend to reprsent classes and the verbs tend to represent methods.

Class: Die

Method: Roll

We will also make a main method for instantiating a game object and which will instantiate the dice objects.

### Die.java and the Random Class

We know roll behavior should be included and we will need to be able to see the face value of the dice.

#### Variables

First let's set our instance variables and static constants/variables.

```java
    //instance variables
    private Random rand;
    private int faceValue;

    //static constants/variables
    public static final int SIDES = 6;
```

We use `SIDES` as a constant to use as a boundary later. We do not break encapsulation by making it public since it is final.

`rand` and `faceValue` ase instance variables. In the case of `rand` it is also a reference variable. We use `rand` to generate random ints.

#### Constructor

Now let's look at the Constructor.

```java
    //constructors
    public Die() {
        faceValue = 1;
        rand = new Random();
    }
```

The constructors job is to initialize the object. `faceValue` of 1 is chosen arbitrarily. Recall it would default to 0 otherwise which we do not want since there is no 0 on a dice. `rand` object is also created.

#### Methods

Now let's look at the Methods.

```java
    //methods
    public int roll() {
        faceValue = rand.nextInt(SIDES) + 1;
        return faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }


```

`roll()` starts with `rand.nextInt()` which accepts a bound parameter and returns a value between 0 (inclusive) and the specified bound (exclusive).

This means the range is 0-5. We do not want 0 and we also want 6 so we can just add +1 to change the range from 1-6.

Next we have our getter method for `faceValue`. We do not have a setter method because it is not required. The only way to externally set the `faceValue` of a Die object is to run the `roll()` method.

Next we overwrite the `toString()` method to return the `faceValue`.

#### Test Method (Main)

Now let's look at the Main method which serves as a test.

```java
    //test method
    public static void main(String[] args) {
        Die die1 = new Die();

        System.out.println(die1);
        System.out.println(die1.roll());
        System.out.println(die1.roll());
    }
```

We test by creating a Die object, printing the variables and then running the `roll()` method a couple times.

### Craps.java

Now let's make a class for the game itself including the different stages of the game.

#### Variables

Because the game needs 2 die, we declare them here.

Instance variables:
```java
    //instance variables
    private Die die1, die2;
    private int point;
```

Note that there are no static constants/variables in this case.

#### Constructor

In the constructor we instantiate the `Die()` objects.

Constructor:
```java
    //constructors
    public Craps() {
        die1 = new Die();
        die2 = new Die();
    }
```

Point is not instantiated in the constructor so it's value defaults to 0.

#### Methods

```java
    //methods
    private int toss() {
        int total = die1.roll() + die2.roll();
        System.out.println("Die one: " + die1.getFaceValue() + ", Die two: " + die2.getFaceValue());
        return total;
    }

    public void go() {
        point = toss();
        System.out.println("Point: " + point);
        if ((point == 7) || (point == 11)) {
            System.out.println("Winner!");
        }
        else if ((point == 2) || (point == 3) || (point == 12)) {
            System.out.println("You lost!");
        }
        else {
            System.out.println("Entering Stage 2");
            stage2();
        }
    }

    private void stage2() {
        
        boolean endGame = false;

        while (!endGame) {
            int total = toss();

            System.out.println("Total: " + total);
            if (total == point) {
                System.out.println("Winner!");
                endGame = true;
            }
            else if (total == 7) {
                System.out.println("You lost!");
                endGame = true;
            }
        }
    }
```

`toss()` was named this way since we already say `roll()` for individual die. This way `toss()` represents the result of both rolls. This method adds up the values of the die and returns them.

`go()` begins the Craps game. It determins if you win, lose, or enter stage 2 based on the points.

`stage2()` starts with a loop variable that starts as false. So while the game isn't over, keep tossing. Total is printed and checked to see if it matches point then you win. Alternatively if you roll a 7 you lose. 

#### Test Method (Main)

This was used as a means to test in our Die object. Here it is used to start the game.

```java
    //main method
    public static void main (String[] args) {
        // Create a new instance of the game of craps
        Craps game = new Craps();

        // Start the game
        game.go();
    }

```

## Inheritance

### Terminology

### Hierarchies

### The Protected Modifier

### Declaring Subclasses and Instance Variables

### Subclass Constructors

### Inheriting and OVerriding Methods

### Inheritance and The final Keyword

### The abstract Modifier

### The Object Class and Overriding equals