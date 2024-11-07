package dungeonmania.entities.logicSwitches.logicalStrategies;

import java.util.List;

import dungeonmania.entities.logicSwitches.LogicalEntity;
import dungeonmania.entities.logicSwitches.conductors.Conductor;

public class CoAndStrategy implements LogicalStrategy {
    @Override
    public boolean isSatisfied(List<Conductor> conductors, LogicalEntity logicalEntity) {
        if (logicalEntity.isActivated()) {
            for (Conductor conductor : conductors) {
                if (!conductor.isActivated()) {
                    return false;
                }
            }

            return true;
        }

        if (conductors.size() < 2) {
            return false;
        }

        int validConductorsCount = 0;
        for (Conductor conductor : conductors) {
            if (!conductor.getJustActivated() || !conductor.isActivated()) {
                return false;
            }

            validConductorsCount++;
        }

        return validConductorsCount >= 2;
    }
}
