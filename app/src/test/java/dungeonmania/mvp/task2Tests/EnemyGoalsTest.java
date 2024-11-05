package dungeonmania.mvp.task2Tests;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.mvp.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class EnemyGoalsTest {
    @Test
    @Tag("11-10")
    @DisplayName("Test goal is achieved with minimum 3 enemies and 2 spawners")
    public void enemyGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/d_enemyGoalsTest_basic",
                "task2Configs/c_enemyGoalsTest_basic");

        // Confirms there is an enemy_goal
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // Confirms the number of enemies assuming that the enemy_goal is 3 enemies
        List<EntityResponse> entities = res.getEntities();
        int spiderCount = TestUtils.countEntityOfType(entities, "spider");
        int zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
        int mercCount = TestUtils.countEntityOfType(entities, "mercenary");
        assertEquals(1, spiderCount);
        assertEquals(1, zombieCount);
        assertEquals(1, mercCount);
        assertEquals(2, TestUtils.getEntities(res, "zombie_toast_spawner").size());

        // Defeat all 3 enemies
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);

        // Check that all 3 enemies no longer exist
        entities = res.getEntities();
        assertEquals(0, TestUtils.countEntityOfType(entities, "spider"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "zombie_toast"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "mercenary"));

        // Verifies that only killing the minimum number of enemies does not achieve the goal
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // Picks up a sword to destroy zombie spawners
        assertEquals(0, TestUtils.getInventory(res, "sword").size());
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // Move to first spawner and check that player is now cardinally adjacent to it
        res = dmc.tick(Direction.RIGHT);
        // System.out.println(TestUtils.getEntities(res, "player").get(0).getPosition());
        // System.out.println(TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getPosition());

        // Interact with the first spawner
        String spawnerIdOne = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();
        res = assertDoesNotThrow(() -> dmc.interact(spawnerIdOne));
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());

        // Enemy goal should still be active as there is still one remaining spawner
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // Move down to get cardinally adjacent to second spawner 
        res = dmc.tick(Direction.DOWN);

        // Destroy the second spawner
        String spawnerIdTwo = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();
        res = assertDoesNotThrow(() -> dmc.interact(spawnerIdTwo));

        // Verify the enemy goal is now achieved
        assertFalse(TestUtils.getGoals(res).contains(":enemies"));
        assertEquals("", TestUtils.getGoals(res));
    }

    // @Test
    // @DisplayName("Testing that the exit goal must be achieved last in EXIT and ENEMIES")
    // public void orAll() {
    //     DungeonManiaController dmc;
    //     dmc = new DungeonManiaController();
    //     DungeonResponse res = dmc.newGame("d_enemyGoalsTest_exitAndEnemiesOrder",
    //             "c_enemyGoalsTest_exitAndEnemiesOrder");

    //     // Verify that both goals are initially required
    //     assertTrue(TestUtils.getGoals(res).contains(":enemies"));
    //     assertTrue(TestUtils.getGoals(res).contains(":exit"));

    //     // move player onto exit
    //     res = dmc.tick(Direction.DOWN);

    //     // don't check state of exit goal in string
    //     assertTrue(TestUtils.getGoals(res).contains(":enemies"));

    //     // Defeat the required number of enemies (assume target number is two)
    //     res = dmc.tick(Direction.DOWN);
    //     res = dmc.tick(Direction.RIGHT);
    //     assertTrue(TestUtils.getGoals(res).contains(":enemies"));

    //     // Move to spawner
    //     res = dmc.tick(Direction.UP);

    //     // Destroy all spawners
    //     try {
    //         res = dmc.interact("spawner");
    //     } catch (IllegalArgumentException | InvalidActionException e) {
    //         fail("Interaction with spawner should not throw an exception.");
    //     }

    //     // assert treasure goal met, but goal string is not empty
    //     assertFalse(TestUtils.getGoals(res).contains(":enemies"));
    //     assertNotEquals("", TestUtils.getGoals(res));

    //     // move player back onto exit
    //     res = dmc.tick(Direction.LEFT);

    //     // assert goal met
    //     assertEquals("", TestUtils.getGoals(res));
    // }
}
