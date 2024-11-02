package dungeonmania.entities;

import dungeonmania.battles.BattleStatistics;

public interface Buffable {
    public BattleStatistics applyBuff(BattleStatistics origin);
}
