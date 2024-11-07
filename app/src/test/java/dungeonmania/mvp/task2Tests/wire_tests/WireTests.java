package dungeonmania.mvp.task2Tests.wire_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class WireTests {
    @Test
    @Tag("20-1-1")
    @DisplayName("Bomb CO_AND: Example but with bomb")
    public void bombCOANDExample() {

        // p b _ w w w
        //       b _ B
        //   b _ w w w

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/wire/d_wire_bombCOANDExample",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push boulder bomb still there.
        res = dmc.tick(Direction.RIGHT);
        entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push middle switch, bomb still there
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Take off middle boulder
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push top boulder off
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push middle boulder on. Should blow up.
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        entities = res.getEntities();
        assertEquals(0, TestUtils.countEntityOfType(entities, "bomb"));
    }

    @Test
    @Tag("20-1-2")
    @DisplayName("Switch Door CO_AND: Example but with switch door")
    public void switchDoorCOOANDExample() {

        // p b _ w w w
        //       b _ B
        //   b _ w w w

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/wire/d_wire_switchDoorCOANDExample",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Push boulder switch_door unopened.
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(5, 1), getPlayerPos(res));

        // Push middle switch, switch_door still unopened.
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(3, 2), getPlayerPos(res));
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(5, 3), getPlayerPos(res));

        // Take off middle boulder. Switch door still closed
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(4, 2), getPlayerPos(res));

        // Push top boulder off
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(2, 1), getPlayerPos(res));

        // Push middle boulder on. Should open up.
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(5, 2), getPlayerPos(res));

    }

    private Position getPlayerPos(DungeonResponse res) {
        return TestUtils.getEntities(res, "player").get(0).getPosition();
    }
}
