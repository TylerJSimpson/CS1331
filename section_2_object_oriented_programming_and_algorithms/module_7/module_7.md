# Table of Contents
1. [More About Interfaces](#more-about-interfaces)
    1. [Default Methods](#default-methods)
    2. [Static Methods](#static-methods)
    3. [Constants in Interfaces](#constants-in-interfaces)
    4. [Contant Interfaces?](#constant-interfaces)
    5. [Interface Hierarchies with a Taste of More UML](#interface-hierarchies-with-a-taste-of-more-uml)
2. [Polymorphism]
    1. [Introduction to Polymorphism](#introduction-to-polymorphism)
    2. [A Simple Example](#a-simple-example)
    3. [Legal Assignments](#legal-assignments)
    4. [Method Calls (at Compile Time)](#method-calls-at-compile-time)
    5. [Casting (at Compile Time)](#casting-at-compile-time)
    6. [Dynamic Binding](#dynamic-binding)

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

We know java objects can be **polymorphic** as they exist in different forms. We have also seen that objects can be referred to by an interface-typed variable as long as the object's class or ancestor implements the interface.

### Introduction to Polymorphism

Until recently the objects we've created and the reference variables declared to interact with them hae been of the same types.

```java
Scanner input = new Scanner(System.in);
```

Here the declaration of the variable `input` and the instantiation of the object are both `Scanner`.

We have seen a variable with a type that is a superclass of the actual class and with interfaces we have seen that an object can be referred to by an interface-typed variable as long as the object's class or ancestor implements the interface.

### A Simple Example

```java
Canine pixy;
pixy = new Poodle(...);
pixy.bark();
```

**The object type is the actual class that follows the new operator when an object is instantiated.**

### Legal Assignments

*Can an object of some class be legally assigned to a reference variable fo some declared type?*

Java compiler sees `Poodle` on the right and `Canine` on the left and it asks is a `Poodle` a `Canine`? Which is true so it passes and compiles.

```java
Canine pixy;
Poodle richie = new Poodle(...)
pixy = richie;
pixy.bark()
```

The same thing happens as above and the code compiles.

You can replace `Canine` with `Dog` and the code would also compile.

```java
Dog pixy;
pixy = new Poodle(...);
pixy.bark();
```

This is because a `Poodle` is a `Dog`.

If you replace `Dog` and `Poodle`:

```java
Poodle pixy;
pixy = new Dog(...);
pixy.bark();
```

Then you will receve an error incompatible types, `Dog` cannot be converted to `Poodle`.

Say you have the following:

```java
Human owner = new Human();
Dog richie = new Dog(...)
Poodle pixy = new Poodle(...)
owner.playFetch(richie);
owner.playFetch(pixy);
```

As well as:

```java
public class Human {
    ...

    public void playFetch(Dog myPet) {
        ...
    }
}
```

This would compile because `Dog myPet = richie` and `Dog myPet = pixy` and a `Poodle` is a `Dog`.

---

The declared type can also be an interface.

```java
public interface Groombale {
    public void groom();
}
```

```java
public class Poodle extends Dog implements Groomable
```

```java
public class Car implements Groomable
```

```java
Groomable toGroom = new Car(...);
Groomable toGroom = new Poodle(...);
```

Both of these will compile because there are groomable types on both sides.

But, this will **not** compile:
```java
Car sparky = new Poodle(...)
```

### Method Calls (at Compile Time)

*Is every variable and method we try to access with a referece variable available in its declared type?*

The same rules apply to methods and variables.

Say we have `enterDogShow()` as a `Poodle` method. The declared type of Pixy is `Canine` which does not contain `enterDogShow()` so the compile will fail.

```java
Canine pixy;
Poodle richie = new Poodle(...)
pixy = richie;
pixy.bark();
pixy.enterDogShow()
```

This leads to casting.

### Casting (at Compile Time)

**Legally we can cast up and down an inheritance tree, as long as the target type has the variable or method being accessed.**

```java
Canine pixy;
pixy = new Poodle(...);
pixy.bark();
((Poodle)pixy).enterDogShow();
```

Note that pixy stays a `Canine` reference even after the cast operation. The cast just creates a temporary reference.

You can't just cast up and down without the method or variable being referenced being checked if it exists.

This would fail because Object does not have `enterDogShow()` method:

```java
((Object)pixy).enterDogShow()
```

### Dynamic Binding

Consider the below scenario where both `Canine` and `Poodle` independently define a bark method.

```java
Canine pixy;  
pixy = new Poodle(...); 
pixy.bark();
```

Canine.java:

```java
public void bark() {
    System.out.println("Woof Woof");
}
```

Poodle.java

```java
public void bark() {
    System.out.println("arf arf");
}
```

The `Poodle` version of bark would execute since the object class is `Poodle`. However, if `Poodle` did not define it's own bark method then the JVM would chekc up the inheritance tree until it finds a superclass with a bark definition. 

This process is done **at runtime**.