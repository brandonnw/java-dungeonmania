# Assignment II Pair Blog Template

## Task 1) Code Analysis and Refactoring ⛏️

### a) From DRY to Design Patterns

[Links to your merge requests](/put/links/here)

> i. Look inside src/main/java/dungeonmania/entities/enemies. Where can you notice an instance of repeated code? Note down the particular offending lines/methods/fields.

The repeated code occurs in the Mercenary.java and ZombieToast.java files. Within these files, both enemy entities have the type same movement when the player has an invincibility buff. Line 111-136 of Mercenary.java and line 41-67 of ZombieToast.java. Furthermore, random movement is also shared between these two entities. Line 99-108 of Mercenary.java and line 32-38, though there are slight differences in the way they are implemented. An example would be in ZombieToast.java, where moving is done outside the switch case, while in Mercenary.java it is done inside and out.

> ii. What Design Pattern could be used to improve the quality of the code and avoid repetition? Justify your choice by relating the scenario to the key characteristics of your chosen Design Pattern.

A Strategy design pattern can be used to improve the quality of code to avoid repetition because each movement type is a "Strategy" option that an entity can choose. Therefore, classes can be made for each strategy and entities can choose which strategy they implement depending on their guidelines. This also allows for entities to share the same type of movement if this is possible.

Applying this to the scenario, both the mercenary and zombietoast entities have the ability to move randomly or away from the player. Thus, the repeated code should be abstracted into a specific class which handles the logic of this specified movement strategy.

> iii. Using your chosen Design Pattern, refactor the code to remove the repetition.
PLAN:
Strategy Pattern -
Identify different enemy movement strategies:
    1. Mercenary
        a. allied (allied movement)
        b. invisible (random movement)
        c. invincible (run away movement)
        d. hostile (hostile movement)
import dungeonmania.entities.Player;
    2. ZombieToast
        a. random (random movement)
        b. runAway (run away movement)
    3. Spider
        a. default (circling movement)

What fields/methods you will need to add/change in a class:
    Fields -
        Remove
            movementType (mercenary and zombietoast)
        Add:
            private MovementStrategy movementType
    Methods -
        Move:
            Mercenary
            Spider
            ZombieToast


What new classes/packages you will need to create
    1. MovementStrategy Interface
        a. Strategy Classes
            i.  RandomMovement
            ii. RunAwayMovement
            iii. AlliedMovement
            iv. CirclingMovement
            v. HostileMovement
    2. enemyMovement Package
        This will have all the classes and interfaces for the Strategy Pattern

Brief overview:
Since the repeated code between Mercenary and ZombieToast were that of movement, we decided that there had to be a way to abstract the code so that both classes could share the same logic. Analysing all the other enemy entities made us realise that we could use a Strategy Pattern where each type of movement was a strategy.

1. Implement the MovementStrategy Interface
    In this interface we would have the move method that all other strategies had to implement.
2. Movement Strategies
    Then for every single movement strategy, we had to refactor the code into a new classes that handled each movement logic.
3. Refactoring enemy classes
    After the movement strategies were implemented, we had to refactor the enemy classes so that they used the strategy classes we created. Each enemy class will have a MovementStrategy field which will determine how that enemy moves.


### b) Pattern Analysis

[Links to your merge requests](/put/links/here)

> i. Identify one place where the State Pattern is present in the codebase. Do you think this is an appropriate use of the State Pattern?

[Answer]

> ii. (Option 1) If you answered that it was an appropriate use of the State Pattern, justify how the implementation relates to the key characteristics of the State Pattern.

> (Option 2) If you answered that it was not an appropriate use of the State Pattern, refactor the code to improve the implementation. You may choose to improve the usage of the pattern, switch to a different design pattern, or remove the pattern entirely.

[Answer or brief explanation of your code]

### c) Inheritance Design

[Links to your merge requests](/put/links/here)

> i. List one design principle that is violated by collectable objects based on the description above. Briefly justify your answer.

[Answer]

> ii. Refactor the inheritance structure of the code, and in the process remove the design principle violation you identified.

[Briefly explain what you did]

### d) More Code Smells

[Links to your merge requests](/put/links/here)

> i. What code smell is present in the above snippet?

[Answer]

> ii. Refactor the code to resolve the smell and underlying problem causing it.

[Briefly explain what you did]

### e) Open-Closed Goals

[Links to your merge requests](/put/links/here)

> i. Do you think the design is of good quality here? Do you think it complies with the open-closed principle? Do you think the design should be changed?

[Answer]

> ii. If you think the design is sufficient as it is, justify your decision. If you think the answer is no, pick a suitable Design Pattern that would improve the quality of the code and refactor the code accordingly.

[Briefly explain what you did]

### f) Open Refactoring

[Merge Request 1](/put/links/here)

[Briefly explain what you did]

[Merge Request 2](/put/links/here)

[Briefly explain what you did]

Add all other changes you made in the same format here:

## Task 2) Evolution of Requirements 👽

### a) Microevolution - Enemy Goal

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

### Choice 1 (Insert choice)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

### Choice 2 (Insert choice)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

### Choice 3 (Insert choice) (If you have a 3rd member)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

## Task 3) Investigation Task ⁉️

[Merge Request 1](/put/links/here)

[Briefly explain what you did]

[Merge Request 2](/put/links/here)

[Briefly explain what you did]

Add all other changes you made in the same format here:
