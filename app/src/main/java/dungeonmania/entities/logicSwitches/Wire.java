package dungeonmania.entities.logicSwitches;

import dungeonmania.entities.Entity;
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
    public boolean isActivated() {
        return this.activated;
    }

    public void activate(Switch s) {
        if (!activated) {
            justActivated = true;
        }
        subscribeSwitch(s);
        activated = true;

        activateAdjacentSubscribers(s);
    }

    public void subscribeSwitch(Switch s) {
        switchSubscribers.add(s);
    }

    @Override
    public void activateAdjacentSubscribers(Switch s) {
        for (Wire wire : wireSubscribers) {
            wire.activate(s);
        }
    }

    @Override
    public void deactivate(Switch s) {
        unsubscribeSwitch(s);
        verifyKeepActivated();
        deactivateAdjacentSubscribers(s);
    }

    public void unsubscribeSwitch(Switch s) {
        switchSubscribers.remove(s);
    }

    public void verifyKeepActivated() {
        if (switchSubscribers.size() == 0) {
            activated = false;
        }
    }

    @Override
    public void deactivateAdjacentSubscribers(Switch s) {
        for (Wire wire : wireSubscribers) {
            wire.deactivate(s);
        }
    }

    public boolean justActivated() {
        return this.justActivated;
    }

    public void resetJustActivated() {
        if (justActivated) {
            justActivated = false;
        }
    }

    public void subscribeAdjacentWire(Wire w) {
        wireSubscribers.add(w);
    }
}
