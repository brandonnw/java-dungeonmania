package dungeonmania.entities.logicSwitches.logicalStrategies;

import java.util.List;

import dungeonmania.entities.logicSwitches.LogicalEntity;
import dungeonmania.entities.logicSwitches.conductors.Conductor;

public class OrStrategy implements LogicalStrategy {
    @Override
    public boolean isSatisfied(List<Conductor> conductors, LogicalEntity logicalEntity) {
        for (Conductor conductor : conductors) {
            if (conductor.isActivated()) {
                return true;
            }
        }

        return false;
    }
}
