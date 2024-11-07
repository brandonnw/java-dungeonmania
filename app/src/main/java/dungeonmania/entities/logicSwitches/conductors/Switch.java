package dungeonmania.entities.logicSwitches.conductors;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Boulder;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Overlappable;
import dungeonmania.entities.collectables.Bomb;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Switch extends Entity implements Overlappable, Conductor {
    private boolean activated;
    private List<Bomb> bombs = new ArrayList<>();

    // New fields for logic switch
    private boolean justActivated = false;
    private List<Wire> wireSubscribers = new ArrayList<>();

    public Switch(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
    }

    public void subscribe(Bomb b) {
        bombs.add(b);
    }

    public void subscribe(Bomb bomb, GameMap map) {
        bombs.add(bomb);
        if (activated) {
            activateBombs(map);
        }
    }

    public void unsubscribe(Bomb b) {
        bombs.remove(b);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activate();
            activateBombs(map);
        }
    }

    public void activateBombs(GameMap map) {
        for (Bomb b : bombs) {
            b.activate(map);
        }
    }

    public void onMovedAway(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = false;
        }
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    // New switch logic for logic switches 

    public void activate() {
        if (!activated) {
            justActivated = true;
        }
        activated = true;
        activateAdjacentSubscribers(this);
    }

    @Override
    public void activateAdjacentSubscribers(Switch s) {
        for (Wire wire : wireSubscribers) {
            wire.activate(s);
        }
    }

    @Override
    public void deactivate(Switch s) {
        activated = false;
        deactivateAdjacentSubscribers(s);
    }

    @Override
    public void deactivateAdjacentSubscribers(Switch s) {
        for (Wire wire : wireSubscribers) {
            wire.deactivate(s);
        }
    }

    @Override
    public boolean justActivated() {
        return this.justActivated;
    }

    @Override
    public void resetJustActivated() {
        if (justActivated) {
            justActivated = false;
        }
    }

    public void subscribeAdjacentWire(Wire wire) {
        wireSubscribers.add(wire);
    }
}
