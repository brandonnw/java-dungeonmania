package dungeonmania.entities.buildables;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Arrow;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.CraftingSystem;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class BowRecipe implements CraftingSystem {
    private static final int ARROW_REQ = 3;

    @Override
    public boolean canBuild(Inventory inventory) {
        return inventory.count(Wood.class) >= 1 && inventory.count(Arrow.class) >= 3;
    }

    @Override
    public void consumeItems(Inventory inventory) {
        for (int i = 0; i < ARROW_REQ; i++) {
            inventory.remove(inventory.getFirst(Arrow.class));
        }
        inventory.remove(inventory.getFirst(Wood.class));
    }

    @Override
    public InventoryItem build(EntityFactory entityFactory) {
        return entityFactory.buildBow();
    }
}
