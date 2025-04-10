# Table of Contents
1. [Introduction to JavaFX](#introduction-to-javafx)
    - [HelloWorld in JavaFX](#helloworld-in-javafx)
    - [Events, Scene Graphs, Nodes, Controls, and Panes](#events-scene-graphs-nodes-controls-and-panes)
    - [Anonymous Inner Classes](#anonymous-inner-classes)
    - [Lambda Expressions](#lambda-expressions)
    - [A More Advanced GUI Pt. 1](#a-more-advanced-gui-pt-1)
    - [A More Advanced GUI Pt. 2](#a-more-advanced-gui-pt-2)

## Introduction to JavaFX

Here we will learn the basics of JavaFX one of the premiere packages in Java for creating GUIs.

### HelloWorld in JavaFX

In a GUI application there is the main `stage` and then `scenes` within the stage. I.e. a window and it's contents.

You won't see a `launch()` method in the code but this is used by JavaFX to start the application.

You will see `StackPane` which is a grouping of elements so each element is added on top of the previous.


### Events, Scene Graphs, Nodes, Controls, and Panes

Much like we couldn't omit `main` in terminal based programs we cannot omit `start` in JavaFX. Also, you do not need to write `main` methods. `start` gets a reference to a stage object.

```java
    public void start(Stage mainStage) {
        mainStage.setTitle("Hello World Program");
    }
```

Let's look at buttons and the actions they contains:

```java
    ..
    btn.setOnAction(new CustomEventHandler());
    ..

    private class CustomEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            System.out.println("Hello World!");
        }
    }

```

Behind the scenes when you click a button it creates an object of type `ActionEvent` which is a child of the class `Event` that

![](/images/m10_gui_event_handling.png)

Instead of saying an `ActionEvent` object was **created** we say it was **fired**.

Any `EventHandler` class must implement `handle` interface which has a type parameter which must be a subclass of `event`.

We use `ActionEvent` since that is the `Event` child that handles button clicks.

```
Interface EventHandler<T extends Event>
```

**A functional interface is an interface that has only one abstract method**

This is important when there are multiple buttons in order to Java to know which functions to map to which buttons.

Moving on to the `StackPane` 

```java
    StackPane root = new StackPane();
    root.getChildren().add(btn); 
```

Notice **root** this is because there is a hierarchy of scenes.

This hierarchy is called a `Scene Graph`.

![](/images/m10_gui_scene_graphs.png)

There are many different node types, for example:

* **Layouts:** FlowPane, GridPane, BorderPane, HBox, StackPane, VBox, etc
* **Shapes:** Circle, Rectangle, etc
* **Images:** ImageView
* **Controls:** Button, CheckBox, TextField, Label

You will see `Pane` as a suffix this is because they are all subclasses of a class `Pane`. If any pre-existing layouts arrange components in a way we don't like we would then use a `Pane` class to specify our own arrangement.

![](/images/m10_gui_scene_graphs_extended.png)

![](/images/m10_gui_scene_graphs_extended_further.png)

### Anonymous Inner Classes

Notice `CustomEventHandler` is only mentioned once outside of where it is instantiated. 
Single use classes are called **anonymous inner classes**.

```java
EventHandler<ActionEvent> handler = new EventHandler<>() {
    public void handle(ActionEvent event) {
        System.out.println("Hello World!");
    }
};
btn.setOnAction(handler);
```

You can even make it more concise by removing the **handler** variables:

```java
btn.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent event) {
        System.out.println("Hello World!");
    }
});
```

Despite being an interface you can instantiate, because we are providing the definition of the class that implements the interface right here it is legal.

### Lambda Expressions

Now we have implemented a class without giving it a name. But for simple classes such as those based on interfaces with a single method, we can use **lambda** expressions to slim the code down even further.

```java
btn.setOnAction((ActionEvent event) -> {
    System.out.println("Hello World!");
    }
);
```

You can even condense this to a single line:
```java
btn.setOnAction(event -> System.out.println("Hello World!"));
```

### A More Advanced GUI Pt. 1

Oracle resource on built in Layout Panes:

https://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm

A layout refers to a `Layout Container Class`. You can see BorderPane shows Top, Left, Center, Right, Bottom. `HBox` and `VBox` line up rows in single columns and rows. There is `GridPane` and many other layouts.

There are also many UI controls such as `Text Field` and `Choice Box`.

### A More Advanced GUI Pt. 2

See [TemperatureConverterGUI.java](/3_exceptions_data_structures_recursion_guis//module_10/programs/TemperatureConverterGUI.java)
