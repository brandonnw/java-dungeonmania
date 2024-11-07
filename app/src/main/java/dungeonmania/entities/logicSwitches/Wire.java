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
        switchSubscribers.add(s);
        activated = true;

        activateAdjacentSubscribers(s);
    }

    @Override
    public void activateAdjacentSubscribers(Switch s) {
        switchSubscribers.add(s);
        for (Wire wire : wireSubscribers) {
            wire.activate(s);
        }
    }

    @Override
    public void deactivate(Switch s) {
        switchSubscribers.remove(s);
        deactivateAdjacentSubscribers(s);
    }

    @Override
    public void deactivateAdjacentSubscribers(Switch s) {
        switchSubscribers.add(s);
        for (Wire wire : wireSubscribers) {
            wire.activate(s);
        }
    }

    public void subscribe(Wire wire) {

    }

    public boolean justActivated() {
        return false;
    }

    public void resetJustActivated() {

    }

    public void deactivateAdjacentSubscriber() {

    }
}
