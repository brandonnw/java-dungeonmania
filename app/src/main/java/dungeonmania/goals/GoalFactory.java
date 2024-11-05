package dungeonmania.goals;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.goals.compositeGoals.AndGoal;
import dungeonmania.goals.compositeGoals.OrGoal;
import dungeonmania.goals.leafGoals.BouldersGoal;
import dungeonmania.goals.leafGoals.EnemyGoal;
import dungeonmania.goals.leafGoals.ExitGoal;
import dungeonmania.goals.leafGoals.TreasureGoal;

public class GoalFactory {
    public static Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        JSONArray subgoals;
        switch (jsonGoal.getString("goal")) {
        case "AND":
            subgoals = jsonGoal.getJSONArray("subgoals");
            return new AndGoal(createGoal(subgoals.getJSONObject(0), config),
                    createGoal(subgoals.getJSONObject(1), config));
        case "OR":
            subgoals = jsonGoal.getJSONArray("subgoals");
            return new OrGoal(createGoal(subgoals.getJSONObject(0), config),
                    createGoal(subgoals.getJSONObject(1), config));
        case "boulders":
            return new BouldersGoal();
        case "treasure":
            int treasureGoal = config.optInt("treasure_goal", 1);
            return new TreasureGoal(treasureGoal);
        case "exit":
            return new ExitGoal();
        case "enemies":
            int enemyGoal = config.optInt("enemy_goal", 1);
            return new EnemyGoal(enemyGoal);
        default:
            return null;
        }
    }
}
