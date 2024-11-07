package dungeonmania.entities.logicSwitches.logicalStrategies;

import java.util.List;

import dungeonmania.entities.logicSwitches.LogicalEntity;
import dungeonmania.entities.logicSwitches.conductors.Conductor;

public class XorStrategy implements LogicalStrategy {
    @Override
    public boolean isSatisfied(List<Conductor> conductors, LogicalEntity logicalEntity) {
        int activatedCount = 0;

        for (Conductor conductor : conductors) {
            if (conductor.isActivated()) {
                activatedCount++;
            }
        }

        return activatedCount == 1;
    }
}
