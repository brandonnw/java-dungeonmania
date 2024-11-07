package dungeonmania.entities.logicSwitches.logicalStrategies;

import java.util.List;

import dungeonmania.entities.logicSwitches.conductors.Conductor;

public class AndStrategy implements LogicalStrategy {
    @Override
    public boolean isSatisfied(List<Conductor> conductors) {
        if (conductors.size() < 2) {
            return false;
        }

        for (Conductor conductor : conductors) {
            if (!conductor.isActivated()) {
                return false;
            }
        }

        return true;
    }
}
