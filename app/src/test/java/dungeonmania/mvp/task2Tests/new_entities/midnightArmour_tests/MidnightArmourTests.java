package dungeonmania.mvp.task2Tests.new_entities.midnightArmour_tests;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MidnightArmourTests {
    public void assertBattleCalculations(BattleResponse battle, boolean enemyDies, String configFilePath,
            String enemyType) {
        List<RoundResponse> rounds = battle.getRounds();
        double playerHealth = battle.getInitialPlayerHealth(); // Should come from config
        double enemyHealth = battle.getInitialEnemyHealth(); // Should come from config
        double playerAttack = Double.parseDouble(TestUtils.getValueFromConfigFile("player_attack", configFilePath));
        double enemyAttack = Double
                .parseDouble(TestUtils.getValueFromConfigFile(enemyType + "_attack", configFilePath));

        double midnightArmourAttackBonus = Double
                .parseDouble(TestUtils.getValueFromConfigFile("midnight_armour_attack", configFilePath));
        double midnightArmourDefenceBonus = Double
                .parseDouble(TestUtils.getValueFromConfigFile("midnight_armour_defence", configFilePath));

        playerAttack += midnightArmourAttackBonus;
        enemyAttack -= midnightArmourDefenceBonus;

        for (RoundResponse round : rounds) {
            assertEquals(-enemyAttack / 10, round.getDeltaCharacterHealth(), 0.001);
            assertEquals(-playerAttack / 5, round.getDeltaEnemyHealth(), 0.001);
            // Delta health is negative
            enemyHealth += round.getDeltaEnemyHealth();
            playerHealth += round.getDeltaCharacterHealth();
        }

        if (enemyDies) {
            assertTrue(enemyHealth <= 0);
        } else {
            assertTrue(playerHealth <= 0);
        }
    }

    @Test
    @Tag("18-1")
    @DisplayName("Crafting: Player crafts midnight armour with (1 sword + 1 sunstone) if there are no zombies.")
    public void validCraft() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/midnightArmour/d_midnightArmour_successfulCraft",
                "task2Configs/new_entities/midnightArmour/c_midnightArmour_successfulCraft");

        // Pick up Sword
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Build Midnight Armour
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());
        assertEquals(0, TestUtils.getInventory(res, "sword").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
    }

    @Test
    @Tag("18-2")
    @DisplayName("Crafting: If at least one zombie, cannot craft midnight armour, InvalidActionException is thrown.")
    public void invalidCraft() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/midnightArmour/d_midnightArmour_failingCraft",
                "task2Configs/new_entities/midnightArmour/c_midnightArmour_failingCraft");

        // Pick up Sword
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Build Midnight Armour
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        assertThrows(InvalidActionException.class, () -> dmc.build("midnight_armour"));
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
    }

    @Test
    @Tag("18-3")
    @DisplayName("Attributes: Midnight Armour has correct attack and defence bonus")
    public void validAttackDefence() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
                "task2Dungeons/new_entities/midnightArmour/d_midnightArmour_validAttackDefence",
                "task2Configs/new_entities/midnightArmour/c_midnightArmour_validAttackDefence");

        // Pick up Sword
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Build Midnight Armour
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());

        res = dmc.tick(Direction.RIGHT);
        BattleResponse battle = res.getBattles().get(0);

        assertBattleCalculations(battle, true,
                "task2Configs/new_entities/midnightArmour/c_midnightArmour_validAttackDefence", "mercenary");
    }
}
