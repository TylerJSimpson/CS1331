# Table of Contents
1. [More About Interfaces](#more-about-interfaces)
    1. [Default Methods](#default-methods)
    2. [Static Methods](#static-methods)
    3. [Constants in Interfaces](#constants-in-interfaces)
    4. [Contant Interfaces?](#constant-interfaces)
    5. [Interface Hierarchies with a Taste of More UML](#interface-hierarchies-with-a-taste-of-more-uml)
2. [Polymorphism]
    1. [Introduction to Polymorphism](#introduction-to-polymorphism)
    2. [Revisiting "Motivating Interfaces"](#revisiting-motivating-interfaces)
    3. [Revisting "Interface Basics"](#revisting-interface-basics)
    4. [A Simple Example](#a-simple-example)
    5. [Legal Assignments](#legal-assignments)
    6. [Method Calls (at Compile Time)](#method-calls-at-compile-time)
    7. [Casting (at Compile Time)](#casting-at-compile-time)
    8. [Casting (at Runtime)](#casting-at-runtime)
    9. [Dynamic Binding](#dynamic-binding)

## More About Interfaces

### Default Methods

 After Java 8 you could add concrete default and static methods in an interface. 

 recall Groomable:

 ```java
public interface Groomable {
    public void groom();
}
```

Recall the loop we created to run an array of Groomable objects:

```java
public class GroomEverything {
    public static void main(String[] args) {
        Groomable[] groomer = {
                new Wolf(17.01, 3),
                new Poodle("richie", 9, "Lux Brand", "Rich Brand"),
                new Wolf(16, 5),
                new Poodle("pixy", 4, "Top Shelf", "Only the Best"),
                new Car("Yuhina", "Spark",2037)
        };

        for (Groomable g : groomer) {  //groom everything
            g.groom();
        }
    }
}
```

What if we wanted to add functionality, perhaps they need to pay after grooming?

```java
        for (Groomable g : groomer) {  //groom everything
            g.groom();
            g.pay();
        }
```

If we simply add it to the interface now every Groomable class must define a pay method.

```java
public interface Groomable {
    public void groom();
    public void pay();
}
```

We can add a **default implementation** of a method to an interface and all classes that implement that interface automatically gain that definition without having to recompile them. Even if the class was compiled with an old interface version!

```java
public interface Groomable {
    public void groom();

    default void pay() {
        System.out.println("Cha-Ching!");
    }
}
```

You can override these default methods:

```java
    public void pay() {
        System.out.println("Grrrr! Warning, don’t try and chase me for payment. I'm a wolf. Run Run Run!");
    }
```

### Static Methods

Default methods are treated much like instance methods defined in an actual class. We can also define static methods in an interface that can represent class-centric behavior.

Maybe we want to add a tip calculator?

```java
public interface Groomable {

    public void groom();
    default void pay() {
        System.out.println("Cha-Ching!");
    }

    static String calculateTip(double price, double percentage) {
        double rawTip = price * (percentage / 100);
        return String.format("$%,.2f", rawTip);
    }
}
```
**Because all methods in an interface are public we are not required to put it in the header.**

To run the method:

`Groomable.calculateTip(39.99, 20)`

Unlike default methods, **static interface methods canot be overridden in classes.**


### Constants in Interfaces

Interfaces can also contain public static constants.

```java
public interface Mascot {
    public static final double MAX_CELEBRATION_SEC = 30;
    public void celebrate();
    public void whine();
    public void chant();
    public void run();
}
```

You don't even need to specify `public`, `static`, and `final` since they are already implied.

```java
double MAX_CELEBRATION_SEC = 30;
```

Say you have 2 different mascots i.e.:

```java
public class YellowJacket implements Mascot
```

```java
public class Bulldog implements Mascot
```

Inheritance doesn't offer an option for sharing this constant since the two classes are only related by being subclasses of the Object class. 

Realistically we should probably create an abstract method in this case. 

```java
public abstract class Mascot {

    public static double MAX_CELEBRATION_SEC = 30;
    public static int MAX_VOLUME = 100;
    public static int MAX_SPEED = 10;

    private int speed;
    private int volume;
    private String name;

    public abstract void celebrate();
    public abstract void whine();
    public abstract void chant();
    public abstract void run();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setVolume(int volume) {
        if (volume <= MAX_VOLUME) {
            this.volume = volume;
        }
    }

    public int getVolume() {
        return volume;
    }

    public void setSpeed(int speed) {
        if (speed <= MAX_SPEED) {
            this.speed = speed;
        }
    }

    public int getSpeed() {
        return speed;
    }
}
```

### Constant Interfaces?

Sometimes you can come across programs or packages with interfaces with the sole purpose of containing constant definitions i.e. not having any declared or defined methods. These are called **constant interfaces**.

Example:

```java
public interface ChemistryConstants {
    public static final double AVOGADROS_NUMBER = 6.02214199e23;
    public static final double FARADAY_CONSTANT = 96485.33;
    public static final double COULOMB_CONSTANT = 8.987551e9;
    public static final double PLANCK_CONSTANT = 6.62607004e34;
}
```

Interfaces were intended to serve as constructs for declaring behaviors so this can be considered an anti-pattern. It solves the problem but it can be discouraged.

### Interface Hierarchies with a Taste of More UML

With interfaces methods can come from multiple parents.

```java
public interface Mascot {
    public void celebrate();
    public void whine();
    public void chant();
    public void run();
}
```

```java
public interface CollegeMascot extends Mascot, Student {
    public void setCollege(String college);
}
```

A college mascot can be a mascot and a student resulting in multiple identifiers in the `extend` clause.

If we were to create a class implementing `CollegeMascot` then it would need to define all the methods declared and inherited by `CollegeMascot` to compile.

![](/images/m7_interface_hierarchies.jpg)

The dashed lines represent dependencies. For example remember our craps game we created where it relied on die? This would be the same if say we implemented as MascotDuel class that created an instace of 2 different mascots and had them duel. This would result in a dashed line.

## Polymorphism

### Introduction to Polymorphism

### Revisiting "Motivating Interfaces"

### Revisting "Interface Basics"

### A Simple Example

### Legal Assignments

### Method Calls (at Compile Time)

### Casting (at Compile Time)

### Casting (at Runtime)

### Dynamic Binding