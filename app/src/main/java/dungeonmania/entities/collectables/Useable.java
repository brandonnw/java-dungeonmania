package dungeonmania.entities.collectables;

import dungeonmania.Game;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public abstract class Useable extends InventoryItem {
    private int durability;

    public Useable(Position position, int durability) {
        super(position);
        this.durability = durability;
    }

    public void use(Game game) {
        durability--;
        if (durability <= 0) {
            game.getPlayer().remove(this);
        }
    }

    public int getDurability() {
        return durability;
    }

}
