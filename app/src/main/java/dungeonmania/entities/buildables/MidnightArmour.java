package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Buffable;

public class MidnightArmour extends Buildable implements Buffable {
    private static final int INFINITE_DURABILITY = Integer.MAX_VALUE;
    private double attack;
    private double defence;

    public MidnightArmour(int attack, int defence) {
        super(null, INFINITE_DURABILITY);
        this.attack = attack;
        this.defence = defence;
    }

    @Override
    public void use(Game game) {
        // Lasts forever. Does not need to reduce durability.
        return;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, attack, defence, 1, 1));
    }
}
