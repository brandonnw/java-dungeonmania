package dungeonmania.entities.logicSwitches.logicalStrategies;

import java.util.List;

import dungeonmania.entities.logicSwitches.conductors.Conductor;

public class AndStrategy implements LogicalStrategy {
    @Override
    public boolean isSatisfied(List<Conductor> conductors) {
        return false;
    }
}
