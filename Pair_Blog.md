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
        Remove:
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

The design principle that is being violated by the collectable objects based ont the description above is the Liskov Substitution Principle. This is because some items like Wood and Treasure have methods applyBuff and durability, even though they do not require them which leads to inconsistent behaviour. This means that according to the definition of InventoryItem, Wood and Treasure do not fit as they don't have a durability and they don't apply a buff and therefore should not have an inheritence relationship with the current definition of InventoryItem. Therefore, the code must be refactored in a way such that any inheritence relationship can be classified as an "Is a" reltionship where a class can be considered as its superclass.

> ii. Refactor the inheritance structure of the code, and in the process remove the design principle violation you identified.
Plan:
1. Remove applyBuff and getDurability from InventoryItem.
2. Change interface useable to abstract class that extends inventoryItem with methods use and getDurability
3. Make sword, bow and shield extend useable
4. Make interface for applyBuff
5. Make Sword, Bow, Shield implement applyBuff

What fields/methods you will need to add/change in a class
    Fields -
        Remove:
            InventoryItem
                a. applyBuff
                b. getDurability
            Potion
                a. applyBuff
                b. getDurability
            InvincibilityPotion
                a. applyBuff
                b. getDurability
            InvisibilityPotion
                a. applyBuff
                b. getDurability
            Sword
                a. getDurability 
            Bow
                a. getDurability
            Shield
                a. getDurability
            Arrow
                a. applyBuff
                b. getDurability
            Bomb
                a. applyBuff
                b. getDurability
            Key
                a. applyBuff
                b. getDurability
            Treasure
                a. applyBuff
                b. getDurability
            Wood
                a. applyBuff
                b. getDurability

What new classes/packages you will need to create
    Abstract Class Useable extends InventoryItem
        a. public Useable(Position position, int durability)
        b. public void use(Game game)
        c. getDurability()
    Abstract Class Buildable extends Useable
        a. public Buildable(Position position, int durability)
    Interface Buffable
        a. public BattleStatistics applyBuff(BattleStatistics origin)

Brief overview:
Since there was a clear violation of the Liskov Substitution Principle (LSP) for a number of classes, we needed to change the relationships between classes so that the principle wouldn't be violated. We decided on two majoring refactoring changes. The first one was to remove applyBuff from InventoryItem since there are few items which can apply a buff, and the second was to remove getDurability as only a few items need a durability.

1. Refactor Useable to an Abstract Class and refactor Builable to extend Useable
Since the use method for Swords, Bows and Shields, along with getDurability was the same, the logic was centralised in the Useable abstract class to clean up code.

2. Remove getDurability from classes which don't need the method
To ensure that LSP was adhered to, getDurability was removed from the InventoryItem class, along with other classes including, all potions, Arrow, Bomb, Key, Treasure, Wood.

3. Create interface for applyBuff
an interface Buffable was created so that Sword, Bow and Shield as they are the only classes which can applyBuff.

4. Remove applyBuff from classes which don't need the method
The method applyBuff was removed from all other classes. Notably it was removed from potions since Player.java has an implementation for applyBuff.

5. Modify battle method in BattleFacade
In the battle method of BattleFacade, with the refactoring choices we did, there was an error as the object item (InventoryItem) no longer had the applyBuff method. Since the if statement checks for if the item is a Sword, Bow or Shield, we decided to cast item as a Buffable so that we could use the applyBuff method.

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

The current Goal class does not comply with the open-closed principle (OCP), as it is still open for modification rather than extension. For instance, if a new goal type was to be added, this would mean that its main methods such as "achieved" would require modification by adding more switch case statements. Rather, the class should implement a design pattern which can prevent modification of existing structure, but allow for additional classes to be made in the case of requiring extension. Therefore, the design should be changed.

> ii. If you think the design is sufficient as it is, justify your decision. If you think the answer is no, pick a suitable Design Pattern that would improve the quality of the code and refactor the code accordingly.

PLAN:

The most suitable design pattern to allow the goal class to be open for extension but closed for modification would be a composite pattern. This new design will make goals with multiple sub-goals (AND goal, OR goal) composite components, and basic goals such as "treasure" and "boulders" leaf nodes. Overarchingly, there will be a component interface which allows for the composite goals and leaf goals to be treated similarly. BY utilising this method, a component interface can be made, and different types of goals can be distinguished by having their own unique classes. This will ensure that the main goal class does not have to be modified when a new type of goal is added, and rather only a new class will have to be made. 

What fields/methods/classes you will need to add/change in a class:
        New Classes:
            Goal.java (Component interface for composite pattern)
            AndGoal.java (Composite class for goals with multiple sub-goals)
            OrGoal.java (Composite class for goals with multiple sub-goals)
            ExitGoal.java (Leaf class for the exit goal)
            BouldersGoal.java (Leaf class for the boulders goal)
            TreasureGoal.java (Leaf class for the treasure goal)

        Modified class:
            GoalFactory.java (Tweeked to adapt to new composite pattern)

Conclusion:

Overall the refactored code for the goal logic presents a program which abides moreso with the open-closed principle by being open for extension and closed for modification. This is because, by creatign a component interface (Goal.java interface), and having a structure where different types of goals are repsented by their own concrete class, when a new type of goal is added, the only major change would be an extension by creating an additional concrete class. 


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
