package dungeonmania.entities.buildables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectables.Useable;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class Buildable extends Useable {
    public Buildable(Position position, int durability) {
        super(position, durability);
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        return;
    }

}
