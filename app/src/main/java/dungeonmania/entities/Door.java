package dungeonmania.entities;

import dungeonmania.map.GameMap;

import dungeonmania.util.Position;

public abstract class Door extends Entity implements Overlappable {
    private boolean open = false;

    public Door(Position position) {
        super(position.asLayer(Entity.DOOR_LAYER));
    }

    @Override
    public abstract boolean canMoveOnto(GameMap map, Entity entity);

    @Override
    public abstract void onOverlap(GameMap map, Entity entity);

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }

}
