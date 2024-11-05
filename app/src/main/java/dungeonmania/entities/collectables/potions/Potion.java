package dungeonmania.entities.collectables.potions;

import dungeonmania.util.Position;
import dungeonmania.entities.collectables.Collectable;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Buffable;

public abstract class Potion extends Collectable implements Buffable {
    private int duration;

    public Potion(Position position, int duration) {
        super(position);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public abstract BattleStatistics applyBuff(BattleStatistics origin);
}
