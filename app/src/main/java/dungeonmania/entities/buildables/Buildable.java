package dungeonmania.entities.buildables;

import dungeonmania.entities.collectables.Useable;
import dungeonmania.util.Position;

public abstract class Buildable extends Useable {
    public Buildable(Position position, int durability) {
        super(position, durability);
    }
}
