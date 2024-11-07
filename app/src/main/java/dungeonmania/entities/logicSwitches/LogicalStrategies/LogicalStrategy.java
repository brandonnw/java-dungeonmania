package dungeonmania.entities.logicSwitches.logicalStrategies;

import java.util.List;

import dungeonmania.entities.logicSwitches.conductors.Conductor;

public interface LogicalStrategy {
    public boolean isSatisfied(List<Conductor> conductors);
}
