package dungeonmania.entities.enemies.enemyMovement;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;

public class CirclingMovement implements MovementStrategy {
    @Override
    public void move(Game game, Enemy enemy) {
        System.out.println("Spider goes in circles");
        return;
    }
}
