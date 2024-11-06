package dungeonmania.entities.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.buildables.Bow;
import dungeonmania.entities.buildables.BowRecipe;
import dungeonmania.entities.buildables.ShieldRecipe;
import dungeonmania.entities.collectables.Sword;
import dungeonmania.entities.collectables.Useable;

public class Inventory {
    private List<InventoryItem> items = new ArrayList<>();

    public boolean add(InventoryItem item) {
        items.add(item);
        return true;
    }

    public void remove(InventoryItem item) {
        items.remove(item);
    }

    public List<String> getBuildables() {
        List<String> result = new ArrayList<>();
        CraftingSystem bowRecipe = new BowRecipe();
        CraftingSystem shieldRecipe = new ShieldRecipe();

        if (bowRecipe.canBuild(this)) {
            result.add("bow");
        }
        if (shieldRecipe.canBuild(this)) {
            result.add("shield");
        }
        return result;
    }

    public InventoryItem checkBuildCriteria(String entity, EntityFactory entityFactory) {
        switch (entity) {
        case "bow":
            CraftingSystem bowRecipe = new BowRecipe();
            if (bowRecipe.canBuild(this)) {
                bowRecipe.consumeItems(this);
                return bowRecipe.build(entityFactory);
            }
            break;
        case "shield":
            CraftingSystem shieldRecipe = new ShieldRecipe();
            if (shieldRecipe.canBuild(this)) {
                shieldRecipe.consumeItems(this);
                return shieldRecipe.build(entityFactory);
            }
        default:
            break;
        }
        return null;
    }

    public <T extends InventoryItem> T getFirst(Class<T> itemType) {
        for (InventoryItem item : items)
            if (itemType.isInstance(item))
                return itemType.cast(item);
        return null;
    }

    public <T extends InventoryItem> int count(Class<T> itemType) {
        int count = 0;
        for (InventoryItem item : items)
            if (itemType.isInstance(item))
                count++;
        return count;
    }

    public Entity getEntity(String itemUsedId) {
        for (InventoryItem item : items)
            if (((Entity) item).getId().equals(itemUsedId))
                return (Entity) item;
        return null;
    }

    public List<Entity> getEntities() {
        return items.stream().map(Entity.class::cast).collect(Collectors.toList());
    }

    public <T> List<T> getEntities(Class<T> clz) {
        return items.stream().filter(clz::isInstance).map(clz::cast).collect(Collectors.toList());
    }

    public boolean hasWeapon() {
        return getFirst(Sword.class) != null || getFirst(Bow.class) != null;
    }

    public Useable getWeapon() {
        Useable weapon = getFirst(Sword.class);
        if (weapon == null)
            return getFirst(Bow.class);
        return weapon;
    }

}
