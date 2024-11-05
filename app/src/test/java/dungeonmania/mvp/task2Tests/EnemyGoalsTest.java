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
    public void testFullEnemyGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/d_enemyGoalsTest_zombiesAndSpawners",
                "task2Configs/c_enemyGoalsTest");

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

        // Move to first spawner
        res = dmc.tick(Direction.RIGHT);

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

    @Test
    @Tag("12-10")
    @DisplayName("Test goal is achieved with minimum 3 enemies and 0 spawners")
    public void testEnemiesWithoutSpawners() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/d_enemyGoalsTest_noSpawners", "task2Configs/c_enemyGoalsTest");

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

        // Verifies that only killing the minimum number of enemies achieves the goals
        assertFalse(TestUtils.getGoals(res).contains(":enemies"));
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @Tag("13-10")
    @DisplayName("Test goal is achieved with enemy_goal = 0 and 2 spawners")
    public void testNoEnemiesWithSpawners() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/d_enemyGoalsTest_noEnemies",
                "task2Configs/c_enemyGoalsTest_enemyGoalZero");

        // Confirms there is an enemy_goal
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // Pickup sword
        assertEquals(0, TestUtils.getInventory(res, "sword").size());
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // Move to first spawner
        res = dmc.tick(Direction.RIGHT);

        // Interact with the first spawner
        String spawnerIdOne = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();
        res = assertDoesNotThrow(() -> dmc.interact(spawnerIdOne));
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());

        // Confirm goal is still incomplete because there is one more spawner
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // Move down to get cardinally adjacent to second spawner
        res = dmc.tick(Direction.DOWN);

        // Destroy the second spawner
        String spawnerIdTwo = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();
        res = assertDoesNotThrow(() -> dmc.interact(spawnerIdTwo));

        // Verify the enemy goal is now achieved
        assertFalse(TestUtils.getGoals(res).contains(":enemies"));
        assertEquals("", TestUtils.getGoals(res));

        // Since enemy_goal = 0, and all spawners are destroyed, goal should be satisfied
        assertFalse(TestUtils.getGoals(res).contains(":enemies"));
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @Tag("14-10")
    @DisplayName("Test that enemies goal can be in disjunction with other goals")
    public void testOrGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/d_enemyGoalsTest_orGoal", "task2Configs/c_enemyGoalsTest");

        // Confirms there is an enemy_goal
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        assertTrue(TestUtils.getGoals(res).contains(":boulders"));

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

        // Move to first spawner
        res = dmc.tick(Direction.RIGHT);

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

        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @Tag("14-10")
    @DisplayName("Test that enemies goal when in conjunction with Exit must be done first")
    public void testAndGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/d_enemyGoalsTest_andGoal",
                "task2Configs/c_enemyGoalsTest_enemyGoalZero");

        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        assertTrue(TestUtils.getGoals(res).contains(":exit"));

        // move player onto exit
        res = dmc.tick(Direction.DOWN);

        // don't check state of exit goal in string
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // move player to destroy spawners and collec sword to destroy spawners
        res = dmc.tick(Direction.UP);
        // Picks up a sword to destroy zombie spawners
        assertEquals(0, TestUtils.getInventory(res, "sword").size());
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // Move adjacent to spawner to destroy
        res = dmc.tick(Direction.RIGHT);

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

        // assert enemies goal met, but goal string is not empty
        assertFalse(TestUtils.getGoals(res).contains(":enemies"));
        assertNotEquals("", TestUtils.getGoals(res));

        // move player back onto exit
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);

        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
    }
}
