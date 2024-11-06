package dungeonmania.entities.inventory;

import dungeonmania.entities.EntityFactory;

public interface CraftingSystem {
    public boolean canBuild(Inventory inventory);

    public void consumeItems(Inventory inventory);

    public InventoryItem build(EntityFactory entityFactory);
}
