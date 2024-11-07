package dungeonmania.entities.logicSwitches;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.collectables.Bomb;
import dungeonmania.entities.logicSwitches.conductors.Conductor;
import dungeonmania.entities.logicSwitches.conductors.Wire;
import dungeonmania.entities.logicSwitches.logicalStrategies.LogicalStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class BombSwitch extends Bomb implements LogicalEntity {
    private boolean activated = false;
    private List<Conductor> conductors = new ArrayList<>();
    private LogicalStrategy logicalStrategy;

    public BombSwitch(Position position, int radius, LogicalStrategy logicalStrategy) {
        super(position, radius);
        this.logicalStrategy = logicalStrategy;
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public void activate(GameMap map) {
        if (logicalStrategy.isSatisfied(conductors, this)
                && (getState() == State.PLACED || getState() == State.SPAWNED)) {
            activated = true;
            super.activate(map);
        }
    }

    @Override
    public void subscribeAdjacentConductor(Conductor conductor) {
        if (!conductors.contains(conductor) && conductor != null)
            conductors.add(conductor);
    }

    @Override
    public void onPutDown(GameMap map, Position p) {
        setPosition(p);
        map.addEntity(this);
        super.setState(State.PLACED);
        List<Position> adjPosList = getPosition().getCardinallyAdjacentPositions();
        adjPosList.stream().forEach(node -> {
            List<Entity> entities = map.getEntities(node).stream().filter(e -> (e instanceof Switch))
                    .collect(Collectors.toList());
            entities.stream().map(Switch.class::cast).forEach(s -> subscribeAdjacentConductor(s));
        });

        adjPosList.stream().forEach(node -> {
            List<Entity> entities = map.getEntities(node).stream().filter(e -> (e instanceof Wire))
                    .collect(Collectors.toList());
            entities.stream().map(Wire.class::cast).forEach(w -> subscribeAdjacentConductor(w));
        });
    }
}
