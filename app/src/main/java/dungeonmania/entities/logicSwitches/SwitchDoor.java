package dungeonmania.entities.logicSwitches;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.enemies.Spider;
import dungeonmania.entities.Door;
import dungeonmania.entities.logicSwitches.conductors.Conductor;
import dungeonmania.entities.logicSwitches.logicalStrategies.LogicalStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class SwitchDoor extends Door implements LogicalEntity {
    private boolean activated = false;
    private List<Conductor> conductors = new ArrayList<>();
    private LogicalStrategy logicalStrategy;

    public SwitchDoor(Position position, LogicalStrategy logicStrategy) {
        super(position);
        this.logicalStrategy = logicStrategy;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        if (isOpen() || entity instanceof Spider) {
            return true;
        }

        return (entity instanceof Player && isOpen());
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (activated) {
            open();
        }
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public void activate(GameMap map) {
        if (logicalStrategy.isSatisfied(conductors, this)) {
            activated = true;
            open();
        } else {
            activated = false;
            close();
        }
    }

    @Override
    public void subscribeAdjacentConductor(Conductor conductor) {
        if (!conductors.contains(conductor) && conductor != null)
            conductors.add(conductor);
    }
}
