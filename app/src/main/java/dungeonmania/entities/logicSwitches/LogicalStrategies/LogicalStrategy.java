package dungeonmania.entities.logicSwitches.logicalStrategies;

import java.util.List;

import dungeonmania.entities.logicSwitches.conductors.Conductor;
import dungeonmania.entities.logicSwitches.LogicalEntity;

public interface LogicalStrategy {
    public boolean isSatisfied(List<Conductor> conductors, LogicalEntity logicalEntity);
}
