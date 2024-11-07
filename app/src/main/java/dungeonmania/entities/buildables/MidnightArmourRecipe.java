package dungeonmania.entities.buildables;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.collectables.Sword;
import dungeonmania.entities.enemies.ZombieToast;
import dungeonmania.entities.inventory.CraftingSystem;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;

public class MidnightArmourRecipe implements CraftingSystem {
    private static final int SWORD_REQ = 1;
    private static final int SUN_STONE_REQ = 1;
    private GameMap map;

    public MidnightArmourRecipe(GameMap map) {
        this.map = map;
    }

    @Override
    public boolean canBuild(Inventory inventory) {
        return inventory.count(Sword.class) >= SWORD_REQ && inventory.count(SunStone.class) >= SUN_STONE_REQ
                && zombiesEmpty();
    }

    private boolean zombiesEmpty() {
        return map.getEntities(ZombieToast.class).isEmpty();
    }

    @Override
    public void consumeItems(Inventory inventory) {
        inventory.remove(inventory.getFirst(Sword.class));
        inventory.remove(inventory.getFirst(SunStone.class));
    }

    @Override
    public InventoryItem build(EntityFactory entityFactory) {
        return entityFactory.buildMidnightArmour();
    }
}
