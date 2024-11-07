package dungeonmania.mvp.task2Tests.switch_tests;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SwitchTests {
    @Test
    @Tag("19-1-1")
    @DisplayName("Lightbulb AND: 1 Adjacent switch")
    public void lightbulbANDAdjacentSwitch() {

        // p b _ L

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/lightbulbs/d_switch_adjacentSwitchANDLightbulb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push boulder lighbulb stays off.
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

    }

    @Test
    @Tag("19-1-2")
    @DisplayName("Lightbulb AND: 2 Adjacent switch")
    public void lightbulbANDTwoAdjacentSwitch() {

        //     b _
        // p b _ L

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/lightbulbs/d_switch_twoAdjacentSwitchANDLightbulb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push first boulder on switch
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push second boulder on switch. light turns on.
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_on"));
    }

    @Test
    @Tag("19-1-3")
    @DisplayName("Lightbulb OR: 1 Adjacent switch")
    public void lightbulbORAdjacentSwitch() {

        // p b _ L

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/lightbulbs/d_switch_adjacentSwitchORLightbulb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push boulder lightbulb on
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_on"));
    }

    @Test
    @Tag("19-1-4")
    @DisplayName("Lightbulb OR: 2 Adjacent switch")
    public void lightbulbORTwoAdjacentSwitch() {

        //     b _
        // p b _ L

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/lightbulbs/d_switch_twoAdjacentSwitchORLightbulb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push first boulder on switch. light turns on
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push second boulder on switch. light stays on.
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_on"));
    }

    @Test
    @Tag("19-1-5")
    @DisplayName("Lightbulb XOR: 1 Adjacent switch")
    public void lightbulbXORAdjacentSwitch() {

        // p b _ L

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/lightbulbs/d_switch_adjacentSwitchXORLightbulb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push boulder lightbulb on
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_on"));

    }

    @Test
    @Tag("19-1-6")
    @DisplayName("Lightbulb XOR: 2 Adjacent switch")
    public void lightbulbXORTwoAdjacentSwitch() {

        //     b _
        // p b _ L

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/lightbulbs/d_switch_twoAdjacentSwitchXORLightbulb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push first boulder on switch. light turns on
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push second boulder on switch. light turns off.
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));
    }

    @Test
    @Tag("19-1-7")
    @DisplayName("Lightbulb CO-AND: 1 Adjacent switch")
    public void lightbulbCOANDAdjacentSwitch() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/lightbulbs/d_switch_adjacentSwitchCOANDLightbulb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push boulder lightbulb off
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));
    }

    @Test
    @Tag("19-1-8")
    @DisplayName("Lightbulb CO-AND: 2 Adjacent switch")
    public void lightbulbCOANDTwoAdjacentSwitch() {

        //     b _
        // p b _ L

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/lightbulbs/d_switch_twoAdjacentSwitchCOANDLightbulb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push first boulder on switch. light stays off
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push second boulder on switch. light stays off.
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));
    }

    @Test
    @Tag("19-1-9")
    @DisplayName("2 Lightbulb OR/CO_AND: 1 Adjacent switch")
    public void lightbulbANDandCOANDAdjacentSwitch() {

        //     L (or)
        // p b _ L (co_and)
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/lightbulbs/d_switch_adjacentSwitchORandCOANDLightbulb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();

        assertEquals(2, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(0, TestUtils.countEntityOfType(entities, "light_bulb_on"));

        // Push boulder, one lightbulb on.
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_off"));
        assertEquals(1, TestUtils.countEntityOfType(entities, "light_bulb_on"));
    }

    @Test
    @Tag("19-2-1")
    @DisplayName("Switch Door AND: 1 Adjacent switch")
    public void switchDoorANDAdjacentSwitch() {

        // p b _ SD

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/switch_door/d_switch_adjacentSwitchANDSwitchDoor",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Walk to door, door should be closed
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(4, 0), getPlayerPos(res));

        // Walk back and push boulder on switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, 1), getPlayerPos(res));

        // Check that door is still closed (AND Switch doors need two conductors)

        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        assertEquals(new Position(4, 0), getPlayerPos(res));

    }

    @Test
    @Tag("19-2-2")
    @DisplayName("Switch Door AND: 2 Adjacent switch")
    public void switchDoorANDTwoAdjacentSwitch() {

        //     b _
        // p b _ SD

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/switch_door/d_switch_twoAdjacentSwitchANDSwitchDoor",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Walk to door, door should be closed
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

        // Walk back and push boulder on switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, 1), getPlayerPos(res));

        // Push second boulder and then check that door opened (AND Switch doors need two conductors)

        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 1), getPlayerPos(res));

    }

    @Test
    @Tag("19-2-3")
    @DisplayName("Switch Door OR: 1 Adjacent switch")
    public void switchDoorORAdjacentSwitch() {

        // p b _ SD

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/switch_door/d_switch_adjacentSwitchORSwitchDoor",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Walk to door, door should be closed
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

        //  Walk back and push boulder. Door should now be open.

        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 1), getPlayerPos(res));

    }

    @Test
    @Tag("19-2-4")
    @DisplayName("Switch Door OR: 2 Adjacent switch")
    public void switchDoorORTwoAdjacentSwitch() {

        //     b _
        // p b _ SD

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/switch_door/d_switch_switchDoorORTwoAdjacentSwitch",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Walk to door, door should be closed
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

        // Walk back and push boulder on switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, 1), getPlayerPos(res));

        // Push second boulder and then check that door opened (AND Switch doors need two conductors)

        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 1), getPlayerPos(res));

    }

    @Test
    @Tag("19-2-5")
    @DisplayName("Switch Door XOR: 1 Adjacent switch")
    public void switchDoorXORAdjacentSwitch() {

        // p b _ SD

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/switch_door/d_adjacentSwitchDoorXORSwitch_switch",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Walk to door, door should be closed
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

        //  Walk back and push boulder. Door should now be open.

        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 1), getPlayerPos(res));

    }

    @Test
    @Tag("19-2-6")
    @DisplayName("Switch Door XOR: 2 Adjacent switch")
    public void switchDoorXORTwoAdjacentSwitch() {

        //     b _
        // p b _ SD

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/switch_door/d_switch_twoAdjacentSwitchXORSwitchDoor",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Walk to door, door should be closed
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

        // Walk back and push boulder on switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, 1), getPlayerPos(res));

        // Push second boulder and then check that door closed (XOR Switch doors need at most one conductor)

        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

    }

    @Test
    @Tag("19-2-7")
    @DisplayName("Switch Door CO-AND: 1 Adjacent switch")
    public void switchDoorCOANDAdjacentSwitch() {

        // p b _ SD

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/switch_door/d_switch_adjacentswitchCOANDSwitchDoor",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Walk to door, door should be closed
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

        //  Walk back and push boulder. Door should still be closed.

        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

    }

    @Test
    @Tag("19-2-8")
    @DisplayName("Switch Door COAND: 2 Adjacent switch")
    public void switchDoorCOANDTwoAdjacentSwitch() {

        //     b _
        // p b _ SD

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/switch_door/d_switch_twoAdjacentSwitchCOANDSwitchDoor",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Walk to door, door should be closed
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

        // Walk back and push boulder on switch
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, 1), getPlayerPos(res));

        // Push second boulder. Then check door closed (COAND Switch doors require all adjacent conductor on same tick)

        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));

    }

    @Test
    @Tag("19-2-9")
    @DisplayName("2 Switch Door OR/CO_AND: 1 Adjacent switch")
    public void switchDoorORandCOANDAdjacentSwitch() {

        //     SD (or)
        // p b _ SD (coand)

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
                "task2Dungeons/switch/switch_door/d_switch_adjacentSwitchORandCOANDSwitchDoor",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "switch_door"));

        // Walk to each door, doors should be closed
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, 0), getPlayerPos(res));

        // Push boulder on switch. Top door open, right door closed
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(4, 2), getPlayerPos(res));
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(3, 0), getPlayerPos(res));

    }

    @Test
    @Tag("19-3-1")
    @DisplayName("Bomb AND: 1 Adjacent switch")
    public void bombANDAdjacentSwitch() {

        // p b _ B

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/bomb/d_switch_adjacentSwitchANDBomb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push boulder on switch, bomb should not explode
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

    }

    @Test
    @Tag("19-3-2")
    @DisplayName("Bomb AND: 2 Adjacent switch")
    public void bombANDTwoAdjacentSwitch() {

        //     b _
        // p b _ B

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/bomb/d_switch_twoAdjacentSwitchANDBomb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push boulder on switch. bomb should not explode.
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push bouler on second switch. Bomb should explode.
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(entities, "bomb"));

    }

    @Test
    @Tag("19-3-3")
    @DisplayName("Bomb OR: 1 Adjacent switch")
    public void bombORAdjacentSwitch() {

        // p b _ B

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/bomb/d_switch_adjacentSwitchORBomb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push boulder on switch. bomb should explode.
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(entities, "bomb"));
    }

    @Test
    @Tag("19-3-4")
    @DisplayName("Bomb XOR: 1 Adjacent switch")
    public void bombXORAdjacentSwitch() {

        // p b _ B

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/bomb/d_switch_adjacentSwitchXORBomb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push boulder to the right, bomb should explode.
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(entities, "bomb"));

    }

    @Test
    @Tag("19-3-5")
    @DisplayName("Bomb CO-AND: 1 Adjacent switch")
    public void bombCOANDAdjacentSwitch() {

        // p b _ B

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/bomb/d_switch_adjacentSwitchCOANDBomb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push boulder on switch. Bomb should not explode.
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

    }

    @Test
    @Tag("19-3-6")
    @DisplayName("Bomb COAND: 2 Adjacent switch")
    public void bombCOANDTwoAdjacentSwitch() {

        //     b _
        // p b _ SD

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("task2Dungeons/switch/bomb/d_switch_twoAdjacentSwitchCOANDBomb",
                "task2Configs/switch/c_switch_defaultConfig");

        List<EntityResponse> entities = res.getEntities();
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push first boulder, bomb should not explode
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

        // Push second boulder, bomb should not explode
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.countEntityOfType(entities, "bomb"));

    }

    private Position getPlayerPos(DungeonResponse res) {
        return TestUtils.getEntities(res, "player").get(0).getPosition();
    }
}
