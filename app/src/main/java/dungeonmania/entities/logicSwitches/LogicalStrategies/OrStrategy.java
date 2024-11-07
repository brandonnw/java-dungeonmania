package dungeonmania.entities.logicSwitches.LogicalStrategies;

import java.util.List;

import dungeonmania.entities.logicSwitches.Conductor;

public class OrStrategy implements LogicalStrategy {
    @Override
    public boolean isActivated(List<Conductor> conductors) {
        return false;
    }
}
