# Assignment II Pair Blog Template

## Task 1) Code Analysis and Refactoring ⛏️

### a) From DRY to Design Patterns

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T3/teams/W15C_KINGFISHER/assignment-ii/-/merge_requests/2)

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

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T3/teams/W15C_KINGFISHER/assignment-ii/-/merge_requests/3)

> i. Identify one place where the State Pattern is present in the codebase. Do you think this is an appropriate use of the State Pattern?

A notable state pattern in the codebase occurs in the logic for player states. Particularly, the folder named "playerState" encapsulates the abstract state class, and the different states which a player can be in depending on whether a potion is active or not. Additionally, the Player.java function implements the logic for transitioning this state pattern mainly in the triggerNext method. However, we believe that this State Pattern has not been appropriately used. This is mainly because there are no major alterations in behaviour when the internal state for player changes. For instance, when the player transitions from base state to invincible, the player's behaviour doesn't change, but rather the behaviour of the potionListeners changes. 

Hence, there is an observer pattern implemented for the potionListeners, notifying them and updating their behaviour when the internal state of player changes. As a result, this State Pattern is mostly redundant, and the codebase can operate more efficiently and just as effectively without it.


> ii. (Option 1) If you answered that it was an appropriate use of the State Pattern, justify how the implementation relates to the key characteristics of the State Pattern.

> (Option 2) If you answered that it was not an appropriate use of the State Pattern, refactor the code to improve the implementation. You may choose to improve the usage of the pattern, switch to a different design pattern, or remove the pattern entirely.

PLAN:

Classes to be removed:
    PlayerState
    InvisibleState
    BaseState
    InvincibleState

What fields/methods you will need to add/change in a class:
    Player.java
        Fields -
            Change:
                PlayerState (Type: PlayerState)
            Add:
                private enum PlayerState
                PlayerState (Type: enum)
    
        Methods -
            Change:
                public void triggerNext(int currentTick)
                public void changeState(Potion inEffective)
                public BattleStatistics applyBuff(BattleStatistics origin)


When it came to refactoring the code, we decided to completely omit the State Pattern and opt for a simpler approach to managing player states. 

Firstly, we centralizes the state management logic to within player by declaring an enum field which tracks the player's current states (BASE, INVINCIBLE, INVISIBLE). This enum removed the need for state class instantiation whilst also providing an easy and yet effective way to manage states.

Additionally, state transition logic is also handled inside the Player class. This is done mainly through a modified "changeState()" method which handles the conditions for switching to specific player states.

The final change which are refactoring involved was modifiyng the applybuff method. The if statements for this method now refer to the ENUM values of player states to decide on battle statistics.

Overall, our refactored code mainly focused on removing the need for implementing a complex State Pattern which would bring very little beneift to our code and was rather unecesssary. Our refactored code now moreso focuses on the idea that the already implemented observer pattern is all that is necessary when it comes to managing behavioural changes from changes in player state. 


### c) Inheritance Design

[Links to your merge requests](/put/links/here)

> i. List one design principle that is violated by collectable objects based on the description above. Briefly justify your answer.

[Answer]

> ii. Refactor the inheritance structure of the code, and in the process remove the design principle violation you identified.

[Briefly explain what you did]

### d) More Code Smells

[Links to your merge requests](/put/links/here)

> i. What code smell is present in the above snippet?

The code smell present in the above snippet involves violating the Single Responsibility Principle (SRP). This is because, the switch class should mainly only be responsible for handling behaviours which concern itself. For instance, it currently handles functionalities such as activation and deactivation when a boulder moves on top of it and managing bomb subscriptions. However, the switch class should not be responsible for calculating the radius of a bomb's detonation and subsequently destroying the entities within that radius. That behaviour should naturally be handled by the bomb class, as it represents the core functionality of a bomb’s explosion mechanics, including calculating its blast radius and determining which entities are affected within that area.

> ii. Refactor the code to resolve the smell and underlying problem causing it.

What fields/methods you will need to add/change in a class:
    Switch.java
        Methods -
            Change:
                public void activateBombs(GameMap map)
    
    Bomb.java
        Methods -
            Add:
                public void activate(GameMap map)


To resolve this code smell the first step was to figure out a way to move the logic for bomb activation into the bomb class itself, as this is a principal behaviour of a bomb and hence is not relevant to the switch class. Therefore, first a method in bomb named "activate" was created, and it accepted the map argument. Essentially, this method is the same as the original "activateBomb" method in Switch.java, however it removed the logic for iterating through the bombs subscribed to the switch's list. After creating a method in the bomb class to store the detonation and destroying logic, the switch method was modified by having the sole responsibility of looping through the array of subscribed bombs, and calling the newly-made "activate" method in the bomb class. This would ensure that the functionality of the original "activateBomb" method could be achieved whilst also ensuring SRP through abstracting the specific logic for bomb activation into the more suitable bomb class.


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
