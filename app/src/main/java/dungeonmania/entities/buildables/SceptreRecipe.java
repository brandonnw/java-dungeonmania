package dungeonmania.entities.buildables;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Arrow;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.CraftingSystem;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class SceptreRecipe implements CraftingSystem {
    public static final int WOOD_REQ = 1;
    public static final int ARROW_REQ = 2;
    public static final int TREASURE_REQ = 1;
    public static final int KEY_REQ = 1;
    public static final int TOTAL_SUN_STONE_REQ = 2;
    public static final int SUN_STONE_REQ = 1;

    @Override
    public boolean canBuild(Inventory inventory) {
        return (inventory.count(Wood.class) >= WOOD_REQ || inventory.count(Arrow.class) >= ARROW_REQ)
                && (inventory.count(Treasure.class) >= TREASURE_REQ || inventory.count(Key.class) >= KEY_REQ
                        || inventory.count(SunStone.class) >= TOTAL_SUN_STONE_REQ)
                && inventory.count(SunStone.class) >= SUN_STONE_REQ;
    }

    @Override
    public void consumeItems(Inventory inventory) {
        // 1 Sun Stone
        inventory.remove(inventory.getFirst(SunStone.class));

        // 1 Wood or 2 Arrow
        if (inventory.count(Arrow.class) >= ARROW_REQ) {
            for (int i = 0; i < 2; i++) {
                inventory.remove(inventory.getFirst(Arrow.class));
            }
        } else if (inventory.count(Wood.class) >= WOOD_REQ) {
            inventory.remove(inventory.getFirst(Wood.class));
        }

        // 1 Treasure or 1 Key or 1 Sun Stone (not consumed)
        if (inventory.count(Treasure.class) >= TREASURE_REQ) {
            inventory.remove(inventory.getFirst(Treasure.class));
        } else if (inventory.count(Key.class) >= KEY_REQ) {
            inventory.remove(inventory.getFirst(Key.class));
        }
    }

    @Override
    public InventoryItem build(EntityFactory entityFactory) {
        return entityFactory.buildSceptre();
    }
}
