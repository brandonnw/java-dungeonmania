package dungeonmania.entities.enemies.enemyMovement;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class AlliedMovement implements MovementStrategy {
    private boolean isAdjacentToPlayer = false;

    @Override
    public void move(Game game, Enemy enemy) {
        Position nextPos = null;
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        nextPos = isAdjacentToPlayer ? player.getPreviousDistinctPosition()
                : map.dijkstraPathFind(enemy.getPosition(), player.getPosition(), enemy);
        if (!isAdjacentToPlayer && Position.isAdjacent(player.getPosition(), nextPos))
            isAdjacentToPlayer = true;

        map.moveTo(enemy, nextPos);
    }

    public void setAdjacentToPlayer(boolean isAdjacentToPlayer) {
        this.isAdjacentToPlayer = isAdjacentToPlayer;
    }

}
