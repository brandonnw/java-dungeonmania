package dungeonmania.entities.logicSwitches.LogicalStrategies;

import java.util.List;
import dungeonmania.entities.logicSwitches.Conductor;

public interface LogicalStrategy {
    public boolean isActivated(List<Conductor> conductors);
}
