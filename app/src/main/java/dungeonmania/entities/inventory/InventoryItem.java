package dungeonmania.entities.inventory;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Overlappable;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

/**
 * An item in the inventory
 */
public abstract class InventoryItem extends Entity implements Overlappable {
    public InventoryItem(Position position) {
        super(position);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public abstract void onOverlap(GameMap map, Entity entity);

}
