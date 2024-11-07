package dungeonmania.entities.logicSwitches;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.Entity;
import dungeonmania.entities.logicSwitches.conductors.Conductor;
import dungeonmania.map.GameMap;
import dungeonmania.entities.logicSwitches.logicalStrategies.LogicalStrategy;
import dungeonmania.util.Position;

public class LightBulb extends Entity implements LogicalEntity {
    private boolean activated = false;
    private List<Conductor> conductors = new ArrayList<>();
    private LogicalStrategy logicalStrategy;

    public LightBulb(Position position, LogicalStrategy logicStrategy) {
        super(position);
        this.logicalStrategy = logicStrategy;
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public void activate(GameMap map) {
        if (logicalStrategy.isSatisfied(conductors)) {
            activated = true;
        }
    }

    @Override
    public void addConductor(Conductor conductor) {

    }

    @Override
    public void removeConductor(Conductor conductor) {

    }
}
