# DungeonMania

A Java-based dungeon crawler game engine developed for UNSW COMP2511 OOP course. The project focuses on backend system design, object-oriented programming principles, and extensive automated testing of complex game mechanics.

The game simulates a player navigating a dungeon, interacting with entities, completing goals, and engaging in combat and environmental interactions.

Created in collaboration with another student.

---

## ✨ Features

### 🎮 Core Game Engine
- Central `DungeonManiaController` managing all game state and logic
- Tick-based game loop driving all actions and updates
- Player movement system with directional input handling
- Dynamic entity updates per game tick

---

### 🧩 Entity System
Supports a wide range of interactive entities including:
- Player
- Enemies (Spiders, Zombies, Mercenaries)
- Items (Treasure, Weapons, Potions, Bombs)
- Environmental objects (Walls, Switches, Boulders, Exits)

Entities interact through a shared position-based world system, enabling collision, activation, and state changes.

---

### 🎯 Goal System
- Modular goal architecture supporting multiple win conditions
- Goals include:
  - Exit goal completion
  - Treasure collection
  - Boulder + switch activation
- Composite goal logic (AND/OR combinations)
- Real-time evaluation after each game tick

---

### 💥 Combat System
- Turn-based battle system triggered on entity collision
- Supports multiple enemy types with configurable stats
- Equipment modifiers:
  - Sword (attack boost with durability)
  - Shield (defence reduction of enemy damage)
  - Bow (ranged/double-attack mechanics with durability)
- Status effects:
  - Invincibility potion (one-hit kill mechanic)
  - Invisibility potion (stealth mechanics)
- Ally system providing combat bonuses when mercenaries are recruited

---

### 💣 Bomb Mechanics
- Bomb pickup and inventory management
- Manual bomb placement using item interaction system
- Explosion triggers based on switch activation rules:
  - Cardinal adjacency (not diagonal)
  - Active vs inactive switch states
- Configurable explosion radius
- Chain reaction behaviour for environmental destruction

---

## 🧪 Testing

This project includes a comprehensive suite of **JUnit 5 integration and system tests**.

### ✔️ Testing Coverage

The test suite validates full gameplay behaviour, including:

#### 🎯 Goals Testing
- Exit, treasure, and boulder/switch goal completion
- Composite goal evaluation over multiple game ticks

#### ⚔️ Combat System Testing
- Player vs enemy battle outcomes (win/loss conditions)
- Round-by-round damage calculations
- Weapon effects (sword, bow, shield)
- Potion-based combat modifiers (invincibility, invisibility)
- Ally-assisted combat scenarios

#### 💣 Bomb System Testing
- Bomb pickup and inventory behaviour
- Bomb placement mechanics
- Explosion triggering conditions (switch activation logic)
- Explosion radius variations (radius 2, radius 10)
- Environmental destruction rules

#### 🧠 System-Level Testing Approach
- Tests use full `DungeonManiaController` execution
- Game state is validated through simulated player actions (`tick`, `interact`, `build`)
- End-to-end scenarios verify complete game mechanics rather than isolated methods

Example test pattern:
```java
dmc.newGame(...)
dmc.tick(Direction.RIGHT)
dmc.tick(itemId)
