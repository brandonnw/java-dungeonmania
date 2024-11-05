package dungeonmania.entities.collectables.potions;

import dungeonmania.util.Position;
import dungeonmania.entities.collectables.Collectable;

public abstract class Potion extends Collectable {
    private int duration;

    public Potion(Position position, int duration) {
        super(position);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

}
