package dungeonmania.mvp.task2Tests.new_entities.sceptre_tests;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SceptreTests {
    @Test
    @Tag("17-1-1")
    @DisplayName("Crafting Sceptre: Sceptre is crafted using 1 wood + 1 key + 1 sunstone")
    public void buildCaseOne() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/sceptre/d_sceptre_buildCaseOne",
                "task2Configs/new_entities/sceptre/c_sceptre_buildCaseOne");

        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Wood
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Pick up Key
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "key").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Build Sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
    }

    @Test
    @Tag("17-1-2")
    @DisplayName("Crafting Sceptre: Sceptre is crafted using 1 wood + 1 treasure + 1 sunstone")
    public void buildCaseTwo() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/sceptre/d_sceptre_buildCaseTwo",
                "task2Configs/new_entities/sceptre/c_sceptre_buildCaseTwo");

        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Wood
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Pick up Treasure
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Build Sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

    }

    @Test
    @Tag("17-1-3")
    @DisplayName("Crafting Sceptre: Sceptre is crafted using 1 wood + 2 sunstone")
    public void buildCaseThree() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/sceptre/d_sceptre_buildCaseThree",
                "task2Configs/new_entities/sceptre/c_sceptre_buildCaseThree");

        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Wood
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());

        // Build Sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
    }

    @Test
    @Tag("17-1-4")
    @DisplayName("Crafting Sceptre: Sceptre is crafted using 2 arrows + 1 key + 1 sunstone")
    public void buildCaseFour() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/sceptre/d_sceptre_buildCaseFour",
                "task2Configs/new_entities/sceptre/c_sceptre_buildCaseFour");

        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "arrow").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pick up Key
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "key").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Build Sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
    }

    @Test
    @Tag("17-1-5")
    @DisplayName("Crafting Sceptre: Sceptre is crafted using 2 Arrows + 1 treasure + 1 sunstone")
    public void buildCaseFive() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/sceptre/d_sceptre_buildCaseFive",
                "task2Configs/new_entities/sceptre/c_sceptre_buildCaseFive");

        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "arrow").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pick up Treasure
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Build Sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
    }

    @Test
    @Tag("17-1-6")
    @DisplayName("Crafting Sceptre: Sceptre is crafted using 2 Arrows + 2 sunstone")
    public void buildCaseSix() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/sceptre/d_sceptre_buildCaseSix",
                "task2Configs/new_entities/sceptre/c_sceptre_buildCaseSix");

        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "arrow").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());

        // Build Sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

    }

    @Test
    @Tag("17-2")
    @DisplayName("Mind control duration: Use sceptre to mind control a mercenary")
    public void validMindControl() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/sceptre/d_sceptre_successfulMindControl",
                "task2Configs/new_entities/sceptre/c_sceptre_successfulMindControl");

        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "arrow").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());

        // Build Sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // achieve mind control
        res = assertDoesNotThrow(() -> dmc.interact(mercId));
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
    }

    @Test
    @Tag("17-3")
    @DisplayName("Mind control duration: Mind control lasts for set duration")
    public void validDuration() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/sceptre/d_sceptre_validDuration",
                "task2Configs/new_entities/sceptre/c_sceptre_validDuration");

        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "arrow").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());

        // Build Sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
        assertEquals(new Position(5, 1), getPlayerPos(res));
        assertEquals(new Position(6, 1), getMercPos(res));

        // Achieve mind control. Duration 2 (Tick 1)
        res = assertDoesNotThrow(() -> dmc.interact(mercId));
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());

        // Move into mercenary. No battle should occur as they are mind controlled (Tick 2)
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(6, 1), getPlayerPos(res));
        assertEquals(new Position(5, 1), getMercPos(res));

        res = dmc.tick(Direction.LEFT);
        assertFalse(res.getBattles().isEmpty());
    }

    @Test
    @Tag("17-4")
    @DisplayName("Mind control constraints: Player mind controls a mercenary from any distance when using the sceptre.")
    public void anyDistance() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/new_entities/sceptre/d_sceptre_anyDistance",
                "task2Configs/new_entities/sceptre/c_sceptre_anyDistance");

        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

        assertEquals(new Position(100, 100), getMercPos(res));
        assertEquals(new Position(1, 1), getPlayerPos(res));

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "arrow").size());

        // Pick up Arrow
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        // Pick up Sun Stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());

        // Build Sceptre
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // achieve mind control
        res = assertDoesNotThrow(() -> dmc.interact(mercId));
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
    }

    private Position getPlayerPos(DungeonResponse res) {
        return TestUtils.getEntities(res, "player").get(0).getPosition();
    }

    private Position getMercPos(DungeonResponse res) {
        return TestUtils.getEntities(res, "mercenary").get(0).getPosition();
    }
}
