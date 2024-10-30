package dungeonmania.entities.enemies.enemyMovement;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;

public interface MovementStrategy {
    public void move(Game game, Enemy enemy);
}
