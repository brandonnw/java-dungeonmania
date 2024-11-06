package dungeonmania.entities.buildables;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.CraftingSystem;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class ShieldRecipe implements CraftingSystem {
    public static final int WOOD_REQ = 2;

    @Override
    public boolean canBuild(Inventory inventory) {
        return inventory.count(Wood.class) >= 2
                && (inventory.count(Treasure.class) >= 1 || inventory.count(Key.class) >= 1);
    }

    @Override
    public void consumeItems(Inventory inventory) {
        for (int i = 0; i < WOOD_REQ; i++) {
            inventory.remove(inventory.getFirst(Wood.class));
        }

        if (inventory.count(Treasure.class) >= 1) {
            inventory.remove(inventory.getFirst(Treasure.class));
        } else if (inventory.count(Key.class) >= 1) {
            inventory.remove(inventory.getFirst(Key.class));
        }
    }

    @Override
    public InventoryItem build(EntityFactory entityFactory) {
        return entityFactory.buildShield();
    }
}
