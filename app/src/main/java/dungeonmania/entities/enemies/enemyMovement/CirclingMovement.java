package dungeonmania.entities.enemies.enemyMovement;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.entities.Boulder;
import dungeonmania.entities.Entity;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.entities.enemies.Spider;
import dungeonmania.util.Position;

public class CirclingMovement implements MovementStrategy {
    @Override
    public void move(Game game, Enemy enemy) {
        Spider spider = (Spider) enemy;
        Position nextPos = spider.getNextPos();
        List<Entity> entities = game.getMap().getEntities(nextPos);
        if (entities != null && entities.size() > 0 && entities.stream().anyMatch(e -> e instanceof Boulder)) {
            spider.setForward(!(spider.getForward()));
            updateNextPosition(spider);
            updateNextPosition(spider);
        }
        nextPos = spider.getNextPos();
        entities = game.getMap().getEntities(nextPos);
        if (entities == null || entities.size() == 0
                || entities.stream().allMatch(e -> e.canMoveOnto(game.getMap(), spider))) {
            game.getMap().moveTo(spider, nextPos);
            updateNextPosition(spider);
        }
        return;
    }

    private void updateNextPosition(Spider spider) {
        if (spider.getForward()) {
            spider.setNextPositionElement(spider.getNextPositionElement() + 1);
            if (spider.getNextPositionElement() == 8) {
                spider.setNextPositionElement(0);
            }
        } else {
            spider.setNextPositionElement(spider.getNextPositionElement() - 1);
            if (spider.getNextPositionElement() == -1) {
                spider.setNextPositionElement(7);
            }
        }
    }
}
