package dungeonmania.entities.enemies.enemyMovement;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.entities.enemies.Spider;

public class CirclingMovement implements MovementStrategy {
    @Override
    public void move(Game game, Enemy enemy) {
        if (!(enemy instanceof Spider)) {
            return;
        }

        enemy = (Spider) enemy;
        Position nextPos = movementTrajectory.get(nextPositionElement);
        List<Entity> entities = game.getMap().getEntities(nextPos);
        if (entities != null && entities.size() > 0 && entities.stream().anyMatch(e -> e instanceof Boulder)) {
            forward = !forward;
            updateNextPosition();
            updateNextPosition();
        }
        nextPos = movementTrajectory.get(nextPositionElement);
        entities = game.getMap().getEntities(nextPos);
        if (entities == null || entities.size() == 0
                || entities.stream().allMatch(e -> e.canMoveOnto(game.getMap(), this))) {
            game.getMap().moveTo(this, nextPos);
            updateNextPosition();
        }
    }
}
