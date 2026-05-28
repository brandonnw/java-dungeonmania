# DungeonMania (COMP2511)

A Java-based dungeon crawler game engine developed for UNSW COMP2511. The project implements a fully functional backend game system where players navigate dungeons, interact with entities, and complete dynamic goals.

The focus of this project is on object-oriented design, game logic implementation, and automated testing of complex system behaviour.

---

## ✨ Features

### Core Gameplay Engine
- Fully functional dungeon game loop controlled via a central `DungeonManiaController`
- Player movement in four directions with real-time state updates
- Tick-based game progression system

### Game Entities & Interactions
- Interactive entities including:
  - Boulders
  - Floor switches
  - Treasure
  - Exits
- Entity interaction system supporting collision and state changes
- Dynamic world updates based on player actions

### Goal System
- Modular goal architecture supporting multiple win conditions:
  - Exit goals
  - Treasure collection goals
  - Boulder/switch activation goals
- Composite goal logic (AND/OR conditions)
- Real-time goal evaluation after each game tick

### Backend Design
- Strong object-oriented architecture
- Separation of concerns between controller, entities, and game logic
- Extensible system designed for additional entities and mechanics

---

## Testing

This project includes a comprehensive suite of JUnit 5 automated tests validating full gameplay behaviour.

### What is tested:
- Game state transitions after each tick
- Player movement and collision handling
- Entity interactions (e.g. pushing boulders, activating switches)
- Goal completion logic and composite conditions
- End-to-end gameplay scenarios using controller-level simulation

### Testing Approach
- Built using JUnit 5 (Jupiter)
- Tests are primarily integration/system tests rather than isolated unit tests
- Entire game engine is exercised through `DungeonManiaController`
- Example pattern:
  ```java
  dmc.newGame(...)
  dmc.tick(Direction.RIGHT)
