
# OOP Assignment 1

In this project we add the following new features to the UndoableStringBuilder we implemented in Assignment 0:
- The ability to organize members and get live changes of the UndoableStringBuilder using the Observer design pattern.
- Objects size tracking in the Heap.




## Setup Instructions

in IntelliJ click File -> Open then open the pom.xml file in the directory and click Open as Project.
## Package Classes
- __[UndoableStringBuilder]__

  The allows you to build a string, while also providing the ability to "undo" the most recent operation.

  The basic idea behind an UndoableStringBuilder is that it maintains a stack of operations. When you perform anoperation. When you perform an undo operation, the UndoableStringBuilder pops the most recent operation off the stack and reverses its effects, effectively "undoing" the change.

  To use an UndoableStringBuilder, you can call the relevant StringBuilder method (for example append) to add characters or substrings to the string, and the undo method to revert the most recent append operation. You can also use the toString method to get the current state of the string as a regular Java String object.

  Here is a simple example of how you might use an UndoableStringBuilder:

    ```java
    UndoableStringBuilder usb = new UndoableStringBuilder();

    usb.append("Hello");
    usb.append(" ");
    usb.append("world!");
    // The current string is "Hello world!"

    usb.undo();
    // The current string is "Hello "

    usb.append("there");
    // The current string is "Hello there"
    ```

- __[GroupAdmin]__

  This class implements the Sender Interface, which is an Observable.
  Here we register and unregister new members. each register member gets notified when an action has been made to an UndoableStringBuilder object.

  Usage Example
    ```java
    GroupAdmin ga = new GroupAdmin();
    ConcreteMember member1 = new ConcreteMember();
    ConcreteMember member2 = new ConcreteMember();

    ga.append("Hello");
    ga.append(" ");
    ga.append("world!");
    // all members get notified about the new StringBuilder "Hello world!"

    ga.undo();
    // all members perform the undo action.
    ```

- __[ConcreteMember]__

  This class implements the Member Interface, which is an Observer.
  Here we update the state of the UndoableStringBuilder object when notified from the GroupAdmin.

  [UndoableStringBuilder]: <https://github.com/mukatrinm/OOP_Ex1/blob/main/src/main/java/observer/UndoableStringBuilder.java>
  [GroupAdmin]: <https://github.com/mukatrinm/OOP_Ex1/blob/main/src/main/java/observer/GroupAdmin.java>
  [ConcreteMember]: <https://github.com/mukatrinm/OOP_Ex1/blob/main/src/main/java/observer/ConcreteMember.java>
## UML Diagram

![App Screenshot](https://imgur.com/C7eyrbW.png)