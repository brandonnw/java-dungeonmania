package dungeonmania.entities.buildables;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Buffable;

public class Bow extends Buildable implements Buffable {
    public Bow(int durability) {
        super(null, durability);
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 2, 1));
    }

}
