package dungeonmania.entities.logicSwitches.conductors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.logicSwitches.Switch;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

import java.util.ArrayList;
import java.util.List;

public class Wire extends Entity implements Conductor {
    private boolean activated = false;
    private boolean justActivated = false;
    private List<Wire> wireSubscribers = new ArrayList<>();
    private List<Switch> switchSubscribers = new ArrayList<>();

    public Wire(Position position) {
        super(position);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public boolean isActivated() {
        return this.activated;
    }

    public void activate(Switch s) {
        switchSubscribers.add(s);
        if (!activated) {
            justActivated = true;
        }
        activated = true;
        activateAdjacentSubscribers(s);
    }

    @Override
    public void activateAdjacentSubscribers(Switch s) {
        for (Wire wire : wireSubscribers) {
            if (!wire.isActivated()) {
                wire.activate(s);
            }
        }
    }

    @Override
    public void deactivate(Switch s) {
        if (switchSubscribers.contains(s)) {
            switchSubscribers.remove(s);
            verifyKeepActivated();
            deactivateAdjacentSubscribers(s);
        }
    }

    public void verifyKeepActivated() {
        if (switchSubscribers.size() == 0) {
            activated = false;
        }
    }

    @Override
    public void deactivateAdjacentSubscribers(Switch s) {
        for (Wire wire : wireSubscribers) {
            if (wire.isActivated()) {
                wire.deactivate(s);
            }
        }
    }

    public boolean getJustActivated() {
        return this.justActivated;
    }

    public void resetJustActivated() {
        if (justActivated) {
            justActivated = false;
        }
    }

    public void subscribeAdjacentWire(Wire wire) {
        if (!wireSubscribers.contains(wire)) {
            wireSubscribers.add(wire);
        }
    }

    public void subscribeAdjacentSwitch(Switch s) {
        if (!switchSubscribers.contains(s)) {
            switchSubscribers.add(s);
        }
    }
}
