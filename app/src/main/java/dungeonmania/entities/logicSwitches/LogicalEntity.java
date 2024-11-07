package dungeonmania.entities.logicSwitches;

import dungeonmania.entities.logicSwitches.conductors.Conductor;

import dungeonmania.map.GameMap;

public interface LogicalEntity {
    public boolean isActivated();

    public void activate(GameMap map);

    public void addConductor(Conductor conductor);
}
