package dungeonmania.goals.leafGoals;

import dungeonmania.Game;
import dungeonmania.goals.Goal;

public class TreasureGoal implements Goal {
    private int target;

    public TreasureGoal(int target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        return game.getCollectedTreasureCount() >= target;
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":treasure";
    }
}
